package com.pennyhill;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;

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

        VPCStack vpcStack = new VPCStack(app, prefix + "vpc-stack", VPCStack.VPCStackProps.builder()
                .env(environment)
                .prefix(prefix)
                .build());

        DatabaseStack databaseStack = new DatabaseStack(app, prefix + "database-stack", DatabaseStack.DatabaseStackProps.builder()
                .env(environment)
                .prefix(prefix)
                .vpc(vpcStack.getVpc())
                .build());

        new SqlLambdaStack(app, prefix + "sql-lambda-stack", SqlLambdaStack.SqlLambdaStackProps.builder()
                .env(environment)
                .prefix(prefix)
                .vpc(vpcStack.getVpc())
                .cluster(databaseStack.getCluster())
                .rdsSecret(databaseStack.getRdsSecret())
                .dbName(databaseStack.getDbName())
                .build());

        new AppSyncStack(app, prefix + "app-sync-stack", AppSyncStack.AppSyncStackProps.builder()
                .env(environment)
                .prefix(prefix)
                .vpc(vpcStack.getVpc())
                .cluster(databaseStack.getCluster())
                .rdsSecret(databaseStack.getRdsSecret())
                .dbName(databaseStack.getDbName())
                .build());

        app.synth();
    }
}

