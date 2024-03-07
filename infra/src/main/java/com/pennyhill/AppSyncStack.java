package com.pennyhill;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awscdk.*;
import software.amazon.awscdk.services.appsync.*;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.rds.ServerlessCluster;
import software.amazon.awscdk.services.secretsmanager.Secret;
import software.constructs.Construct;

import static com.pennyhill.gen.Resolvers.getResolverProps;
import static com.pennyhill.CustomResolvers.getCustomResolverProps;

public class AppSyncStack extends Stack {
    public AppSyncStack(final Construct scope, final String id, final AppSyncStackProps props){
        super(scope, id, props);

        var apiId = props.getPrefix() + "GraphqlApi";
        var dsId = props.getPrefix() + "RdsDataSource";

        GraphqlApi api = new GraphqlApi(this, apiId, GraphqlApiProps.builder()
                .name(apiId)
                .definition(Definition.fromFile("src/main/resources/merged/schema.graphql"))
                .authorizationConfig(AuthorizationConfig.builder()
                        .defaultAuthorization(AuthorizationMode.builder()
                                .authorizationType(AuthorizationType.API_KEY)
                                .apiKeyConfig(ApiKeyConfig.builder()
                                        .expires(Expiration.after(Duration.days(365)))
                                        .build())
                                .build())
                        .build())
                .build());

        RdsDataSource ds = api.addRdsDataSource(dsId, props.getCluster(), props.getRdsSecret(), props.getDbName());

        getResolverProps().forEach(
                resolverProps -> ds.createResolver(props.getPrefix() + resolverProps.getFieldName() + resolverProps.getTypeName(), resolverProps)
        );

        getCustomResolverProps().forEach(
                resolverProps -> ds.createResolver(props.getPrefix() + resolverProps.getFieldName() + resolverProps.getTypeName(), resolverProps)
        );

        new CfnOutput(this, "ApiKey", CfnOutputProps.builder().value(api.getApiKey()).build());
        new CfnOutput(this, "GraphqlUrl", CfnOutputProps.builder().value(api.getGraphqlUrl()).build());
    }

    @lombok.Builder
    @Setter
    @Getter
    public static class AppSyncStackProps implements StackProps {
        private Vpc vpc;
        private Environment env;
        private String prefix;
        private Secret rdsSecret;
        private String dbName;
        private ServerlessCluster cluster;
    }
}
