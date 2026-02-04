package com.eventdrivenmicroservices.api.controller;

import com.eventdrivenmicroservices.api.event.GradeSubmittedEvent;
import com.eventdrivenmicroservices.api.model.Student;
import com.eventdrivenmicroservices.api.service.EventProcessor;
import com.eventdrivenmicroservices.api.service.EventPublisher;
import com.eventdrivenmicroservices.api.service.GradeService;
import com.eventdrivenmicroservices.api.service.SqsEventProcessor;
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
    private final SqsEventProcessor sqsEventProcessor;

    public GradeController(GradeService gradeService,
                           EventPublisher eventPublisher,
                           EventProcessor eventProcessor,
                           SqsEventProcessor sqsEventProcessor){
        this.gradeService = gradeService;
        this.eventPublisher = eventPublisher;
        this.eventProcessor = eventProcessor;
        this.sqsEventProcessor = sqsEventProcessor;

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
    public Map<String, Object> processAllEvents() {
        Map<String, Object> response = new HashMap<>();

        // Process messages from SQS
        int processed = sqsEventProcessor.processMessages();

        response.put("status", "success");
        response.put("eventsProcessed", processed);
        response.put("message", processed + " event(s) processed from SQS");
        return response;
    }

    @GetMapping("queue")
    public Map<String, Object> getQueueStatus(){
        Map<String, Object> status =  new HashMap<>();
        status.put("queueSize", eventPublisher.getQueueSize());
        status.put("isEmpty", eventPublisher.isQueueEmpty());
        return status;
    }

    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String name){
        return gradeService.searchByName(name);
    }

    @GetMapping("/top")
    public List<Student> getTopStudent(){
        return gradeService.getTopStudents();
    }

    @GetMapping("/failing")
    public List<Student> getFailingStudents(){
        return gradeService.getFailingStudents();
    }

    @GetMapping("/events")
    public List<GradeSubmittedEvent> getAllEvents(){
        return eventPublisher.getAllEvents();
    }

    @DeleteMapping("/queue")
    public Map<String, String> clearQueue(){
        eventPublisher.clearQueue();
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "queue cleared");
        return response;
    }
}
