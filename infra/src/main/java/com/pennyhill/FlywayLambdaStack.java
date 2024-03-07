package com.pennyhill;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awscdk.*;
import software.amazon.awscdk.services.ec2.Port;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.iam.*;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.rds.ServerlessCluster;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.notifications.LambdaDestination;
import software.amazon.awscdk.services.secretsmanager.Secret;
import software.constructs.Construct;

import java.util.List;
import java.util.Map;

import static com.pennyhill.Constants.SQL_SCRIPTS_BUCKET_NAME;
import static software.amazon.awscdk.services.s3.EventType.OBJECT_CREATED;

@Getter
public class FlywayLambdaStack extends Stack {

    private final Function function;
    public FlywayLambdaStack(final Construct scope, final String id, final FlywayLambdaStackProps props) {
        super(scope, id, props);

        Bucket s3 = Bucket.Builder.create(this, props.getPrefix() + SQL_SCRIPTS_BUCKET_NAME).build();

        Role lambdaRole = new Role(this, "AppSyncAuroraStackFlywayLambdaRole", RoleProps.builder()
                .assumedBy(new ServicePrincipal("lambda.amazonaws.com"))
                .build());
        lambdaRole.addManagedPolicy(ManagedPolicy.fromAwsManagedPolicyName("service-role/AWSLambdaBasicExecutionRole"));
        lambdaRole.addManagedPolicy(ManagedPolicy.fromAwsManagedPolicyName("service-role/AWSLambdaVPCAccessExecutionRole"));
        lambdaRole.addManagedPolicy(ManagedPolicy.fromAwsManagedPolicyName("SecretsManagerReadWrite"));
        PolicyStatement statement3 = PolicyStatement.Builder.create()
                .effect(Effect.ALLOW)
                .actions(List.of("s3:*"))
                .resources(List.of("arn:aws:s3:::"+s3.getBucketName()+"/*")).build();
        lambdaRole.attachInlinePolicy(new Policy(this,"s3-bucket-policy",
                PolicyProps.builder().statements(List.of( new PolicyStatement[]{statement3})).build()));

        function = new Function(this, props.getPrefix() + "FlywayLambda", FunctionProps.builder()
                .role(lambdaRole)
                .memorySize(1024)
                .vpc(props.getVpc())
                .securityGroups(props.getCluster().getConnections().getSecurityGroups())
                .runtime(Runtime.JAVA_11)
                .environment(Map.of("RDS_SECRET", props.getRdsSecret().getSecretArn(),
                        "CLUSTER_ARN", props.getCluster().getClusterArn(),
                        "DATABASE_NAME", props.getDbName(),
                        "MIGRATIONS_BUCKET", s3.getBucketName())
                )
                .code(Code.fromAsset("../flyway-lambda/target/flyway-lambda-0.0.1-SNAPSHOT.jar")) // the version number needs to be managed in the real world to ensure the redeploy of new versions
                .handler("com.pennyhill.FlywayLambdaHandler::handleRequest")
                .timeout(Duration.seconds(140))
                .build());

        var securityGroups = props.cluster.getConnections().getSecurityGroups();
        securityGroups.forEach(g ->
                g.addIngressRule(g, Port.tcp(5432)));

        props.getCluster().grantDataApiAccess(function);

        LambdaDestination functionDestination = new LambdaDestination(function);
        s3.addEventNotification(OBJECT_CREATED,functionDestination);

        new CfnOutput(this, "SqlScriptsBucket", CfnOutputProps.builder().value(s3.getBucketName()).build());

    }

    @lombok.Builder
    @Setter
    @Getter
    public static class FlywayLambdaStackProps implements StackProps{
        private Vpc vpc;
        private Environment env;
        private String prefix;
        private Secret rdsSecret;
        private String dbName;
        private ServerlessCluster cluster;
    }
}
