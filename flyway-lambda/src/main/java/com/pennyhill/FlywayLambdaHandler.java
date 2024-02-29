package com.pennyhill;

import com.amazon.rdsdata.client.RdsData;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CloudFormationCustomResourceEvent;
import com.google.gson.JsonObject;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import software.amazon.awssdk.services.rdsdata.RdsDataClient;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;


public class FlywayLambdaHandler implements RequestHandler<CloudFormationCustomResourceEvent, String> {

    private static final Logger logger = LogManager.getLogger(FlywayLambdaHandler.class);
    private static final int MAX_RETRIES = 2;

    @Override
    public String handleRequest(CloudFormationCustomResourceEvent event, Context context) {
        String requestType = event.getRequestType();

        logger.info("Request Type :: " + event.getRequestType());
        logger.info("Properties :: " + event.getResourceProperties());

        migrate();

        JsonObject retJson = new JsonObject();
        if (requestType != null) {
            retJson.addProperty("RequestType", requestType);
        }

        if ("Update".equals(requestType) || "Delete".equals(requestType)) {
            retJson.addProperty("PhysicalResourceId", event.getPhysicalResourceId());
        }

        retJson.addProperty("migrated", Boolean.TRUE.toString());
        logger.info("RETURN :: " + retJson);
        return retJson.toString();
    }

    private void migrate() {
        String secretArn = System.getenv("RDS_SECRET");
        String clusterArn = System.getenv("CLUSTER_ARN");
        String databaseName = System.getenv("DATABASE_NAME");

        RdsData client = RdsData.builder()
                .sdkClient(RdsDataClient.builder().build())
                .database(databaseName)
                .resourceArn(clusterArn)
                .secretArn(secretArn)
                .build();

        // Connect to the db to wake it up??!!
        var sqlStmt = "select * from information_schema.tables;";
        logger.info("Start running SQL :: " + sqlStmt);
        var executionResult = client.forSql(sqlStmt).withContinueAfterTimeout().execute();
        logger.info("SQL result :: " + executionResult.toString());
        logger.info("Finished running SQL :: " + sqlStmt);

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(secretArn);

        hikariConfig.setUsername(secretArn);
        hikariConfig.setDriverClassName("com.amazonaws.secretsmanager.sql.AWSSecretsManagerPostgreSQLDriver");
        hikariConfig.setConnectionTimeout(140000);

        var ds = new HikariDataSource(hikariConfig);

        var flyway = Flyway.configure().dataSource(ds).load();

        int i = 0;
        while (i < MAX_RETRIES ) {
            try {
                var result = flyway.migrate();
                if(result.success) i = MAX_RETRIES;
                else i++;
            } catch (HikariPool.PoolInitializationException poolInitializationException) {
                i++;
                logger.info("retry in 30 secs");
                try {
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    logger.error(e);
                }
            } catch (Exception e) {
                logger.error(e);
                throw e;
            }

        }
    }


}
