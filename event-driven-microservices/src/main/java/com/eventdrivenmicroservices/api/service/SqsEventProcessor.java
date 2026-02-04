package com.eventdrivenmicroservices.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

@Service
public class SqsEventProcessor {

    private final SqsClient sqsClient;
    private final EventProcessor eventProcessor;
    private final ObjectMapper objectMapper;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    public SqsEventProcessor(SqsClient sqsClient, EventProcessor eventProcessor) {
        this.sqsClient = sqsClient;
        this.eventProcessor = eventProcessor;
        this.objectMapper = new ObjectMapper();
    }
}
