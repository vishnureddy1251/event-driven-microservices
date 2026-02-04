package com.eventdrivenmicroservices.api.service;

import com.eventdrivenmicroservices.api.event.GradeSubmittedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Service
public class SqsEventPublisher {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    public SqsEventPublisher(SqsClient sqsClient){
        this.sqsClient = sqsClient;
        this.objectMapper = new ObjectMapper();
    }

    public void publishEvent(GradeSubmittedEvent event) {
        try {
            // Convert event to JSON
            String messageBody = objectMapper.writeValueAsString(event);

            System.out.println("\n PUBLISHING EVENT TO SQS");
            System.out.println("═══════════════════════════════");
            System.out.println("   Queue: " + queueUrl);
            System.out.println("   Event ID: " + event.getEventId());
            System.out.println("   Student: " + event.getStudentName());
            System.out.println("   Grade: " + event.getGrade());

            // Send to SQS
            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(messageBody)
                    .build();

            SendMessageResponse response = sqsClient.sendMessage(request);

            System.out.println("Event published to SQS!");
            System.out.println("   Message ID: " + response.messageId());
            System.out.println("═══════════════════════════════\n");

        } catch (Exception e) {
            System.err.println("Error publishing event: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getQueueUrl() {
        return queueUrl;
    }
}
