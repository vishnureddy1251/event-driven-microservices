package com.eventdrivenmicroservices.api.controller;

import com.eventdrivenmicroservices.api.service.EventProcessor;
import com.eventdrivenmicroservices.api.service.EventPublisher;
import com.eventdrivenmicroservices.api.service.GradeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;
    private final EventPublisher eventPublisher;
    private final EventProcessor eventProcessor;
}
