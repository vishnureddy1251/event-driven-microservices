package com.eventdrivenmicroservices.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("{aws.endpoint}")
    private String awsEndpoint;

    @Value("{aws.region}")
    private String awsRegion;

    @Value("${aws.access-key-id}")
    private String accessKeyId;

    @Value("${aws.secret-access-key}")
    private String secretAccessKey;
}
