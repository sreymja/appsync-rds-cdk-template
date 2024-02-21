package com.pennyhill;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awscdk.*;
import software.amazon.awscdk.customresources.Provider;
import software.amazon.awscdk.customresources.ProviderProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.iam.ManagedPolicy;
import software.amazon.awscdk.services.iam.Role;
import software.amazon.awscdk.services.iam.RoleProps;
import software.amazon.awscdk.services.iam.ServicePrincipal;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.rds.ServerlessCluster;
import software.amazon.awscdk.services.secretsmanager.Secret;
import software.constructs.Construct;

import java.util.Map;

@Getter
public class FlywayLambdaStack extends Stack {

    private final Function function;
    public FlywayLambdaStack(final Construct scope, final String id, final FlywayLambdaStackProps props) {
        super(scope, id, props);
        //Lambda function custom resolver

        Role lambdaRole = new Role(this, "AppSyncAuroraStackFlywayLambdaRole", RoleProps.builder()
                .assumedBy(new ServicePrincipal("lambda.amazonaws.com"))
                .build());
        lambdaRole.addManagedPolicy(ManagedPolicy.fromAwsManagedPolicyName("service-role/AWSLambdaBasicExecutionRole"));
        lambdaRole.addManagedPolicy(ManagedPolicy.fromAwsManagedPolicyName("service-role/AWSLambdaVPCAccessExecutionRole"));
        lambdaRole.addManagedPolicy(ManagedPolicy.fromAwsManagedPolicyName("SecretsManagerReadWrite"));

        function = new Function(this, "FlywayLambda", FunctionProps.builder()
                .role(lambdaRole)
                .memorySize(1024)
                .vpc(props.getVpc())
                .securityGroups(props.getCluster().getConnections().getSecurityGroups())
                .runtime(Runtime.JAVA_11)
                .environment(Map.of("RDS_SECRET", props.getRdsSecret().getSecretArn()))
                .code(Code.fromAsset("../flyway-lambda/target/flyway-lambda-0.0.1-SNAPSHOT.jar")) // the version number needs to be managed in the real world to ensure the redeploy of new versions
                .handler("com.pennyhill.FlywayLambdaHandler::handleRequest")
                .timeout(Duration.seconds(140))
                .build());

        props.getCluster().grantDataApiAccess(function);




    }

    @lombok.Builder
    @Setter
    @Getter
    public static class FlywayLambdaStackProps implements StackProps{
        private Vpc vpc;
        private Environment env;
        private Secret rdsSecret;
        private ServerlessCluster cluster;
    }
}
