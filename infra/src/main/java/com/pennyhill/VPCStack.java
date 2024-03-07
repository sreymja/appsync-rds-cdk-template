package com.pennyhill;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ec2.VpcProps;
import software.constructs.Construct;

@Getter
public class VPCStack extends Stack {

    private final Vpc vpc;

    public VPCStack(final Construct scope, final String id, final VPCStackProps props) {
        super(scope, id, props);

        this.vpc = new Vpc(this, props.getPrefix() + "TheVPC", VpcProps.builder()
                .maxAzs(2)
                .build());
    }

    @lombok.Builder
    @Setter
    @Getter
    public static class VPCStackProps implements StackProps {
        private Environment env;
        private String prefix;
    }
}
