package com.eventdrivenmicroservices.api.controller;

import com.eventdrivenmicroservices.api.model.Student;
import com.eventdrivenmicroservices.api.service.EventProcessor;
import com.eventdrivenmicroservices.api.service.EventPublisher;
import com.eventdrivenmicroservices.api.service.GradeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/submit")
    public Student submitGrade(@RequestBody Map<String, Object> request){
        String name = (String) request.get("name");
        String subject = (String) request.get("subject");
        int score = (Integer) request.get("score");
        return gradeService.submitGrade (name, subject, score);
    }
}
