package com.pennyhill;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awscdk.*;
import software.amazon.awscdk.customresources.Provider;
import software.amazon.awscdk.customresources.ProviderProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.rds.ServerlessCluster;
import software.amazon.awscdk.services.secretsmanager.Secret;
import software.constructs.Construct;

public class FlywayProviderStack extends Stack {
    public FlywayProviderStack(final Construct scope, final String id, final FlywayProviderStackProps props){
        super(scope, id, props);

        Provider flywayProvider = new Provider(this, "AppSyncAuroraStackFlywayProvider", ProviderProps.builder()
                .onEventHandler(props.getFunction())
                .build());

        CustomResource cr = new CustomResource(this, "AppSyncAuroraStackFlywayCustomResource", CustomResourceProps.builder()
                .serviceToken(flywayProvider.getServiceToken())
                .resourceType("Custom::FlywayProvider")
                .build());

        cr.getNode().addDependency(props.getCluster());
    }

    @lombok.Builder
    @Setter
    @Getter
    public static class FlywayProviderStackProps implements StackProps {
        private Environment env;
        private Function function;
        private ServerlessCluster cluster;
    }
}
