package com.pennyhill;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awscdk.*;
import software.amazon.awscdk.services.ec2.SubnetSelection;
import software.amazon.awscdk.services.ec2.SubnetType;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.rds.*;
import software.amazon.awscdk.services.secretsmanager.Secret;
import software.amazon.awscdk.services.secretsmanager.SecretProps;
import software.amazon.awscdk.services.secretsmanager.SecretStringGenerator;
import software.constructs.Construct;

@Getter
public class DatabaseStack extends Stack {

    private final String dbName;
    private final Secret rdsSecret;
    private final ServerlessCluster cluster;

    public DatabaseStack(final Construct scope, final String id, final DatabaseStackProps props) {
        super(scope, id, props);

        dbName = props.getPrefix() + "AppSyncDb";
        var secretId = props.getPrefix() + "AppSyncPassword";
        var secretName = props.getPrefix() + "AppsyncDbCredentials";
        var dbUserName = props.getPrefix() + "AppSyncAdmin";
        var groupId = props.getPrefix() + "AppSyncParameterGroup";
        var clusterId = props.getPrefix() + "AppSyncCluster";

        rdsSecret = new Secret(this, secretId, SecretProps.builder()
                .secretName(secretName)
                .generateSecretString(SecretStringGenerator.builder()
                        .excludePunctuation(true)
                        .passwordLength(16)
                        .generateStringKey("password")
                        .secretStringTemplate("{\"username\": \"" + dbUserName + "\"}")
                        .build())
                .build());

        var engine = DatabaseClusterEngine.auroraPostgres (
                AuroraPostgresClusterEngineProps.builder()
                        .version(AuroraPostgresEngineVersion.VER_13_12)
                        .build()
        );
        var group = new ParameterGroup(this, groupId,
                ParameterGroupProps
                        .builder()
                        .engine(engine)
                        .build()
        );

        cluster = new ServerlessCluster(this, clusterId, ServerlessClusterProps.builder()
                .engine(engine)
                .parameterGroup(group)
                .vpc(props.getVpc())
                .vpcSubnets(SubnetSelection.builder()
                        .subnetType(SubnetType.PRIVATE_WITH_EGRESS)
                        .build())
                .defaultDatabaseName(dbName)
                .credentials(Credentials.fromSecret(rdsSecret))
                .build());

        new CfnOutput(this, "ClusterArn", CfnOutputProps.builder().value(cluster.getClusterArn()).build());
        new CfnOutput(this, "SecretArn", CfnOutputProps.builder().value(rdsSecret.getSecretArn()).build());
        new CfnOutput(this, "DbName", CfnOutputProps.builder().value(dbName).build());
    }

    @lombok.Builder
    @Setter
    @Getter
    public static class DatabaseStackProps implements StackProps {
        private Vpc vpc;
        private Environment env;
        private String prefix;
    }
}
