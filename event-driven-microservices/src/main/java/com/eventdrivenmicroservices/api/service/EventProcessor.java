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

    private void handleGradeAction(GradeSubmittedEvent event){
        String grade = event.getGrade();
        String studentName = event.getStudentName();

        switch (grade) {
            case "A":
                System.out.println("EXCELLENT WORK!");
                System.out.println("Sending congratulations email to " + studentName);
                System.out.println("Adding to honor roll");
                System.out.println("Notifying parent: Great job!");
                break;

            case "B":
                System.out.println("GREAT JOB!");
                System.out.println("Sending positive feedback email to " + studentName);
                System.out.println("Notifying parent: Well done!");
                break;

            case "C":
                System.out.println("GOOD EFFORT!");
                System.out.println("Sending grade report to " + studentName);
                System.out.println("Notifying parent: Satisfactory progress");
                break;

            case "D":
                System.out.println("NEEDS IMPROVEMENT");
                System.out.println("Sending improvement plan to " + studentName);
                System.out.println("Scheduling tutoring session");
                System.out.println("Notifying parent: Extra help recommended");
                break;

            case "F":
                System.out.println("IMMEDIATE ATTENTION NEEDED");
                System.out.println("Sending support resources to " + studentName);
                System.out.println("Notifying teacher for intervention");
                System.out.println("Enrolling in remedial program");
                System.out.println("Notifying parent: Urgent - contact school");
                break;

            default:
                System.out.println("Sending grade notification");
        }
    }
}
