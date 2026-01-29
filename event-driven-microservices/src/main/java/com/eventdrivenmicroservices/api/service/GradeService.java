package com.eventdrivenmicroservices.api.service;

import com.eventdrivenmicroservices.api.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {

    private final List<Student> students = new ArrayList<>();
    private Long nextId = 1L;

    private final EventPublisher eventPublisher;

    public GradeService(EventPublisher eventPublisher){
        this.eventPublisher = eventPublisher;

    }

    public Student submitGrade(String name, String subject, int score){

        eventPublisher.publsihEvent(event);

        return Student;

    }

}
