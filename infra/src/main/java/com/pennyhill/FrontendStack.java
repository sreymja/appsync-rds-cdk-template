package com.pennyhill;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awscdk.*;
import software.amazon.awscdk.services.cloudfront.*;
import software.amazon.awscdk.services.s3.*;
import software.amazon.awscdk.services.ec2.Vpc;
import software.constructs.Construct;

import java.util.List;
import java.util.UUID;

public class FrontendStack extends Stack {

    public FrontendStack(final Construct scope, final String id, final FrontendStackProps props){
        super(scope, id, props);
        var uuid = UUID.randomUUID().toString();

        var distId = props.getPrefix() + "FrontendDistribution";
        var bucketId = props.getPrefix() + uuid + "-frontend-bucket";

        var bucket = new Bucket(this, bucketId, BucketProps.builder()
                .bucketName(bucketId)
                .accessControl(BucketAccessControl.PRIVATE)
                .cors(List.of(
                        CorsRule.builder()
                                .allowedHeaders(List.of("*"))
                                .allowedMethods(List.of(HttpMethods.GET))
                                .allowedOrigins(List.of("*"))
                                .exposedHeaders(List.of("ETag"))
                                .maxAge(3000)
                                .build()
                ))
                .build());
        var loggingBucket = new Bucket(this, bucketId + "-logging", BucketProps.builder()
                .bucketName(bucketId + "-logging")
                .accessControl(BucketAccessControl.PRIVATE)
                .objectOwnership(ObjectOwnership.BUCKET_OWNER_PREFERRED)
                .build());

        var identityResource = new OriginAccessIdentity(this, "OAI", OriginAccessIdentityProps.builder()
                .comment("Cloudfront Identity for frontend example")
                .build());

        var dist = new CloudFrontWebDistribution(this, distId, CloudFrontWebDistributionProps.builder()
                .originConfigs(List.of(SourceConfiguration
                        .builder().s3OriginSource(
                                S3OriginConfig.builder()
                                        .s3BucketSource(bucket)
                                        .originAccessIdentity(identityResource)
                                        .build())
                        .behaviors(List.of(
                                Behavior.builder()
                                        .allowedMethods(CloudFrontAllowedMethods.GET_HEAD_OPTIONS)
                                        .cachedMethods(CloudFrontAllowedCachedMethods.GET_HEAD)
                                        .isDefaultBehavior(true)
                                        .minTtl(Duration.seconds(0))
                                        .defaultTtl(Duration.seconds(3600))
                                        .maxTtl(Duration.seconds(86400))
                                        .compress(true)
//                                        .viewerProtocolPolicy(ViewerProtocolPolicy.REDIRECT_TO_HTTPS)
                                        .build()
                        ))
                        .build()))
                .loggingConfig(
                    LoggingConfiguration.builder()
                            .includeCookies(true)
                            .bucket(loggingBucket)
                            .prefix("frontend-logging")
                            .build()
                )
                .build());

        new CfnOutput(this, "DistributionDomainName", CfnOutputProps.builder().value(dist.getDistributionDomainName()).build());
        new CfnOutput(this, "BucketName", CfnOutputProps.builder().value(bucket.getBucketName()).build());

    }

    @lombok.Builder
    @Setter
    @Getter
    public static class FrontendStackProps implements StackProps {
        private Environment env;
        private String prefix;
    }
}
