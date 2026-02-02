package com.eventdrivenmicroservices.api.controller;

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
}
