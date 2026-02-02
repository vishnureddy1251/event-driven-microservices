package com.eventdrivenmicroservices.api.controller;

import com.eventdrivenmicroservices.api.event.GradeSubmittedEvent;
import com.eventdrivenmicroservices.api.model.Student;
import com.eventdrivenmicroservices.api.service.EventProcessor;
import com.eventdrivenmicroservices.api.service.EventPublisher;
import com.eventdrivenmicroservices.api.service.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;
    private final EventPublisher eventPublisher;
    private final EventProcessor eventProcessor;

    public GradeController(GradeService gradeService,
                           EventPublisher eventPublisher,
                           EventProcessor eventProcessor){
        this.gradeService = gradeService;
        this.eventPublisher = eventPublisher;
        this.eventProcessor = eventProcessor;

    }

    @GetMapping
    public Map<String, String> home(){
        Map<String, String> response = new HashMap<>();
        response.put("message", " Welcome to Event-Driven Microservices!");
        response.put("version", "1.0");
        response.put("status", "running");
        response.put("type", "Event-Driven Architecture");
        return response;
    }

    @PostMapping("/submit")
    public Student submitGrade(@RequestBody Map<String, Object> request){
        String name = (String) request.get("name");
        String subject = (String) request.get("subject");
        int score = (Integer) request.get("score");

        System.out.println("\n API: Received grade submission");

        return gradeService.submitGrade (name, subject, score);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return gradeService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Long id){
        Student student = gradeService.getStudentById(id);
        if (student == null){
            throw new RuntimeException("Student not found with id:" + id);
        }
        return student;
    }

    @GetMapping("/stats")
    public GradeService.GradeStatistics getStatistics(){
        return gradeService.getStatistics();
    }

    @PostMapping("/process-event")
    public Map<String, Object> processNextEvent() {
        Map<String, Object> response = new HashMap<>();

        GradeSubmittedEvent event = eventPublisher.getNextEvent();

        if (event == null){
            response.put("status", "no_events");
            response.put("message", "queue is empty - no events to process");
            return response;
        }

        eventProcessor.processEvent(event);

        response.put("status", "success");
        response.put("message", "Event processed successfully");
        response.put("eventId", event.getEventId());
        response.put("remainingEvents", eventPublisher.getQueueSize());
        return response;
    }

    @PostMapping("/process-all")
    public Map<String, Object> processAllEvents(){
        Map<String, Object> response = new HashMap<>();
        int processed = 0;

        GradeSubmittedEvent event;
        while ((event = eventPublisher.getNextEvent()) !=null){
            eventProcessor.processEvent(event);
            processed++;
        }

        response.put("status", "success");
        response.put("eventsProcessed", processed);
        response.put("message", processed + " event(s) processed");
        return response;
    }
}
