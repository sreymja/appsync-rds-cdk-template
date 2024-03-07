package com.pennyhill;

import com.amazon.rdsdata.client.RdsData;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CloudFormationCustomResourceEvent;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rdsdata.RdsDataClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.nio.charset.StandardCharsets;


public class FlywayLambdaHandler implements RequestHandler<CloudFormationCustomResourceEvent, String> {

    private static final Logger logger = LogManager.getLogger(FlywayLambdaHandler.class);
    @Override
    public String handleRequest(CloudFormationCustomResourceEvent event, Context context) {
        String requestType = event.getRequestType();

        logger.info("Request Type :: " + event.getRequestType());
        logger.info("Properties :: " + event.getResourceProperties());

        runSqlFromS3();

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

    private static void runSqlFromS3(){

        String secretArn = System.getenv("RDS_SECRET");
        String clusterArn = System.getenv("CLUSTER_ARN");
        String databaseName = System.getenv("DATABASE_NAME");
        String migrationsBucketName = System.getenv("MIGRATIONS_BUCKET");

        RdsData client = RdsData.builder()
                .sdkClient(RdsDataClient.builder().build())
                .database(databaseName)
                .resourceArn(clusterArn)
                .secretArn(secretArn)
                .build();

        S3Client s3 = S3Client.builder()
                .region(Region.US_EAST_1)
                .build();

        var bytes = getObjectBytes(s3, migrationsBucketName, "V1__Create_first_tables.sql");
        String sqlStmt = new String(bytes, StandardCharsets.UTF_8);
        logger.info("Start running SQL :: " + sqlStmt);
        var executionResult = client.forSql(sqlStmt).withContinueAfterTimeout().execute();
        logger.info("SQL result :: " + executionResult.toString());
        logger.info("Finished running SQL :: " + sqlStmt);

        s3.close();
    }

    public static byte[] getObjectBytes(S3Client s3, String bucketName, String keyName) {

            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(keyName)
                    .bucket(bucketName)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            return objectBytes.asByteArray();

    }



}