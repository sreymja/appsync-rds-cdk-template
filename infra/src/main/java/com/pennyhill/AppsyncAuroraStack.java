package com.pennyhill;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awscdk.*;
import software.amazon.awscdk.services.appsync.*;
import software.amazon.awscdk.services.ec2.Port;
import software.amazon.awscdk.services.ec2.SubnetSelection;
import software.amazon.awscdk.services.ec2.SubnetType;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.rds.Credentials;
import software.amazon.awscdk.services.rds.DatabaseClusterEngine;
import software.amazon.awscdk.services.rds.ServerlessCluster;
import software.amazon.awscdk.services.rds.ServerlessClusterProps;
import software.amazon.awscdk.services.rds.ParameterGroup;
import software.amazon.awscdk.services.secretsmanager.Secret;
import software.amazon.awscdk.services.secretsmanager.SecretProps;
import software.amazon.awscdk.services.secretsmanager.SecretStringGenerator;
import software.constructs.Construct;

import java.util.Properties;

import static com.pennyhill.resolver.Resolvers.getResolvers;

@Getter
public class AppsyncAuroraStack extends Stack {

    private final String dbName;
    private final Secret rdsSecret;
    private final ServerlessCluster cluster;
    public AppsyncAuroraStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public AppsyncAuroraStack(final Construct scope, final String id, final AppsyncAuroraStackProps props) {
        super(scope, id, props);

        PropertyLoader propertyLoader = new PropertyLoader();
        Properties appConfig = propertyLoader.loadProperties("app.properties");

        String DB_USER_NAME = appConfig.getProperty("appsync.db.username");
        dbName = appConfig.getProperty("appsync.db.name");

        rdsSecret = new Secret(this, "GraphQLAuroraRDSPassword", SecretProps.builder()
                .secretName("graphql-aurora-rds-cred")
                .generateSecretString(SecretStringGenerator.builder()
                        .excludePunctuation(true)
                        .passwordLength(16)
                        .generateStringKey("password")
                        .secretStringTemplate("{\"username\": \"" + DB_USER_NAME + "\"}")
                        .build())
                .build());

        cluster = new ServerlessCluster(this, "GraphQLAuroraCluster", ServerlessClusterProps.builder()
                .engine(DatabaseClusterEngine.AURORA_POSTGRESQL)
                .parameterGroup(ParameterGroup.fromParameterGroupName(this, "ParameterGroup", "default.aurora-postgresql11"))
                .vpc(props.getVpc())
                .enableDataApi(true)
                .vpcSubnets(SubnetSelection.builder()
                        .subnetType(SubnetType.PRIVATE_WITH_EGRESS)
                        .build())
                .defaultDatabaseName(dbName)
                .credentials(Credentials.fromSecret(rdsSecret))
                .build());

        cluster.getConnections().getSecurityGroups().forEach(g ->
                g.addIngressRule(g, Port.tcp(5432)));

        GraphqlApi api = new GraphqlApi(this, "AppSyncRDSResolver", GraphqlApiProps.builder()
                .name("AppsyncRDSResolverAPI")
                .definition(Definition.fromFile("src/main/resources/schema.graphql"))
//                .schema(Schema.fromAsset("src/main/resources/schema.graphql"))
                .authorizationConfig(AuthorizationConfig.builder()
                        .defaultAuthorization(AuthorizationMode.builder()
                                .authorizationType(AuthorizationType.API_KEY)
                                .apiKeyConfig(ApiKeyConfig.builder()
                                        .expires(Expiration.after(Duration.days(365)))
                                        .build())
                                .build())
                        .build())
                .build());

        RdsDataSource ds = api.addRdsDataSource("AuroraDS", cluster, rdsSecret, dbName);

        getResolvers().forEach(
                resolver -> ds.createResolver(resolver.getProps().getFieldName() + resolver.getProps().getTypeName(), resolver.getProps())
        );

        CfnOutput output = new CfnOutput(this, "AppSyncAuroraAPIURL", CfnOutputProps.builder()
                .value(api.getGraphqlUrl())
                .build());

    }

    @lombok.Builder
    @Setter
    @Getter
    public static class AppsyncAuroraStackProps implements StackProps {
        private Vpc vpc;
        private Environment env;
    }


}
