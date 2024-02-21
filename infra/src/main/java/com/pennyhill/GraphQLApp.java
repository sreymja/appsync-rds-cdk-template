package com.pennyhill;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

import java.util.Optional;

public class GraphQLApp {
    public static void main(final String[] args) {
        App app = new App();
        String prefix = Optional.ofNullable(app.getNode().tryGetContext("prefix"))
                .map(Object::toString).orElse("");

        Environment environment = Environment.builder()
                .account(System.getenv("CDK_DEFAULT_ACCOUNT"))
                .region(System.getenv("CDK_DEFAULT_REGION"))
                .build();

        VPCStack vpcStack = new VPCStack(app, prefix + "graphql-vpc-stack", StackProps.builder()
                .env(environment)
                .build());

        AppsyncAuroraStack auroraStack = new AppsyncAuroraStack(app, prefix + "appsync-aurora-stack", AppsyncAuroraStack.AppsyncAuroraStackProps.builder()
                .env(environment)
                .vpc(vpcStack.getVpc())
                .build());

        FlywayLambdaStack lambdaStack = new FlywayLambdaStack(app, prefix + "flyway-lambda-stack", FlywayLambdaStack.FlywayLambdaStackProps.builder()
                .env(environment)
                .vpc(vpcStack.getVpc())
                .cluster(auroraStack.getCluster())
                .rdsSecret(auroraStack.getRdsSecret())
                .build());

        new FlywayProviderStack(app, prefix + "flyway-provider-stack", FlywayProviderStack.FlywayProviderStackProps.builder()
                .env(environment)
                .function(lambdaStack.getFunction())
                .cluster(auroraStack.getCluster())
                .build());

        app.synth();
    }
}

