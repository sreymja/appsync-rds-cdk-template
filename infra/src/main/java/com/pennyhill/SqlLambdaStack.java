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
public class SqlLambdaStack extends Stack {

    private final Function function;

    public SqlLambdaStack(final Construct scope, final String id, final SqlLambdaStackProps props) {
        super(scope, id, props);

        Bucket s3 = Bucket.Builder.create(this, props.getPrefix() + SQL_SCRIPTS_BUCKET_NAME).build();

        Role lambdaRole = new Role(this, "AppSyncAuroraStackSqlLambdaRole", RoleProps.builder()
                .assumedBy(new ServicePrincipal("lambda.amazonaws.com"))
                .build());
        lambdaRole.addManagedPolicy(ManagedPolicy.fromAwsManagedPolicyName("service-role/AWSLambdaBasicExecutionRole"));
        lambdaRole.addManagedPolicy(ManagedPolicy.fromAwsManagedPolicyName("service-role/AWSLambdaVPCAccessExecutionRole"));
        lambdaRole.addManagedPolicy(ManagedPolicy.fromAwsManagedPolicyName("SecretsManagerReadWrite"));
        PolicyStatement statement3 = PolicyStatement.Builder.create()
                .effect(Effect.ALLOW)
                .actions(List.of("s3:*"))
                .resources(List.of("arn:aws:s3:::" + s3.getBucketName() + "/*")).build();
        lambdaRole.attachInlinePolicy(new Policy(this, "s3-bucket-policy",
                PolicyProps.builder().statements(List.of(new PolicyStatement[]{statement3})).build()));

        s3.addToResourcePolicy(PolicyStatement.Builder.create()
                .effect(Effect.ALLOW)
                .actions(List.of("s3:*"))
                .resources(List.of("arn:aws:s3:::" + s3.getBucketName() + "/*"))
                .principals(List.of(lambdaRole.getGrantPrincipal()))
                .build()
        );

        function = new Function(this, props.getPrefix() + "SqlLambda", FunctionProps.builder()
                .role(lambdaRole)
                .memorySize(1024)
                .vpc(props.getVpc())
                .securityGroups(props.getCluster().getConnections().getSecurityGroups())
                .runtime(Runtime.JAVA_21)
                .environment(Map.of("RDS_SECRET", props.getRdsSecret().getSecretArn(),
                        "CLUSTER_ARN", props.getCluster().getClusterArn(),
                        "DATABASE_NAME", props.getDbName(),
                        "MIGRATIONS_BUCKET", s3.getBucketName())
                )
                .code(Code.fromAsset("../sql-lambda/target/sql-lambda-0.0.1-SNAPSHOT.jar")) // the version number needs to be managed in the real world to ensure the redeploy of new versions
                .handler("com.pennyhill.SqlLambdaHandler::handleRequest")
                .timeout(Duration.seconds(140))
                .build());

        var securityGroups = props.cluster.getConnections().getSecurityGroups();
        securityGroups.forEach(g ->
                g.addIngressRule(g, Port.tcp(5432)));

        props.getCluster().grantDataApiAccess(function);

        LambdaDestination functionDestination = new LambdaDestination(function);
        s3.addEventNotification(OBJECT_CREATED, functionDestination);

        new CfnOutput(this, "SqlScriptsBucket", CfnOutputProps.builder().value(s3.getBucketName()).build());

    }

    @lombok.Builder
    @Setter
    @Getter
    public static class SqlLambdaStackProps implements StackProps {
        private Vpc vpc;
        private Environment env;
        private String prefix;
        private Secret rdsSecret;
        private String dbName;
        private ServerlessCluster cluster;
    }
}
