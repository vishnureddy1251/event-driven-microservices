package com.eventdrivenmicroservices.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

@Service
public class SqsEventPublisher {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    @Value("{aws.sqs.queue-url}")
    private String queueUrl;

    public SqsEventPublisher(SqsClient sqsClient){
        this.sqsClient = sqsClient;
        this.objectMapper = new ObjectMapper();
    }
}
