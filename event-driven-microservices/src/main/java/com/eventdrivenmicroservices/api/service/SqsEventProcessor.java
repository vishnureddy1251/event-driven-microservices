package com.eventdrivenmicroservices.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

@Service
public class SqsEventProcessor {

    private final SqsClient sqsClient;
    private final EventProcessor eventProcessor;
    private final ObjectMapper objectMapper;
}
