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
import software.amazon.awssdk.services.s3.model.*;

import java.nio.charset.StandardCharsets;
import java.util.List;


public class SqlLambdaHandler implements RequestHandler<CloudFormationCustomResourceEvent, String> {

    private static final Logger logger = LogManager.getLogger(SqlLambdaHandler.class);

    private static void runSqlFromS3() {

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

        List<String> files = listFiles(s3, migrationsBucketName);
        logger.info("Bucket scanned :: " + files.size() + " files found");
        files.forEach(f -> {
            logger.info("Found file :: " + f);
            var bytes = getObjectBytes(s3, migrationsBucketName, f);
            String sqlStmt = new String(bytes, StandardCharsets.UTF_8);
            logger.info("Start running SQL :: " + sqlStmt);
            var executionResult = client.forSql(sqlStmt).withContinueAfterTimeout().execute();
            logger.info("SQL result :: " + executionResult.toString());
            logger.info("Finished running SQL :: " + sqlStmt);
        });


        s3.close();
    }

    private static List<String> listFiles(S3Client s3, String migrationsBucketName) {
        ListObjectsRequest req = ListObjectsRequest.builder()
                .bucket(migrationsBucketName)
                .build();
        ListObjectsResponse resp = s3.listObjects(req);
        return resp.contents().stream().map(S3Object::key).toList();
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


}