package com.eventdrivenmicroservices.api.service;

import org.springframework.stereotype.Service;

@Service
public class EventProcessor {

    private int totalProcessed = 0;

    public void processEvent(GradeSubmittedEvent event){
        System.out.println("\n PROCESSING EVENT");
        System.out.println("═══════════════════════════════");
        System.out.println(" Event ID: " + event.getEventId());
        System.out.println(" Student: " + event.getStudentName());
        System.out.println(" Subject: " + event.getSubject());
        System.out.println(" Score: " + event.getScore() + "/100");
        System.out.println(" Grade: " + event.getGrade());
        System.out.println(" Time: " + event.getTimestamp());
        System.out.println("───────────────────────────────");

        simulateProcessingDelay();

        handleGradeAction(event);

        totalProcessed++;

        System.out.println(" Event processed successfully!");
        System.out.println(" Total events processed: " + totalProcessed);
        System.out.println("═══════════════════════════════\n");

    }
}
