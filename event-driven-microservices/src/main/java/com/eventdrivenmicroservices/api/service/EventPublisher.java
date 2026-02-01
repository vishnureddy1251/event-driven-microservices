package com.eventdrivenmicroservices.api.service;

import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    private void publishEvent(GradeSubmittedEvent event){

        System.out.println("\n PUBLISHING EVENT");
        System.out.println("Event ID: " + event.getEventId());
        System.out.println("Type: " + event.getEventType());
        System.out.println("Student: " + event.getStudentName());
        System.out.println("Grade: " + event.getGrade());

        eventQueue.offer(event);

        System.out.println("EVent published Successfully!");
        System.out.println("Queue Size: " + eventQueue.size());
        System.out.println("____________________________\n");
    }

    public GradeSubmittedEvent getNextEvent(){
        GradeSubmittedEvent event = eventQueue.poll();

        if (event == null){
            System.out.println("Queue is empty - no events to process");
            return null;
        }

        System.out.println("\n RETRIEVED EVENT FROM QUEUE");
        System.out.println("Event ID: " + event.getEventId());
        System.out.println(" Remaining in Queue:" + eventQueue.size());

        return event;
    }
}
