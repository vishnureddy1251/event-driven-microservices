package com.eventdrivenmicroservices.api.service;

import com.eventdrivenmicroservices.api.event.GradeSubmittedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;
import java.util.List;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

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

    public int processMessages(){
        try{
            System.out.println("\n RECEIVING MESSAGES FROM SQS");
            System.out.println("   Queue: " + queueUrl);

            ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .maxNumberOfMessages(10)
                    .waitTimeSeconds(5)
                    .build();

            ReceiveMessageResponse response = sqsClient.receiveMessage(request);
            List<Message> messages = response.messages();

            if (messages.isEmpty()) {
                System.out.println("No messages in queue");
                return 0;
        }
            System.out.println("📬 Received " + messages.size() + " message(s)");

            int processed = 0;

            for (Message message : messages) {
                try {
                    GradeSubmittedEvent event = objectMapper.readValue(
                            message.body(),
                            GradeSubmittedEvent.class
                    );

                    eventProcessor.processEvent(event);

                    DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                            .queueUrl(queueUrl)
                            .receiptHandle(message.receiptHandle())
                            .build();

                    sqsClient.deleteMessage(deleteRequest);

                    processed++;

                } catch (Exception e) {
                    System.err.println("Error processing message: " + e.getMessage());
                }
            }

            System.out.println("Processed " + processed + " message(s)\n");
            return processed;

        } catch (Exception e) {
            System.err.println("Error receiving messages: " + e.getMessage());
            return 0;
        }
    }
}
