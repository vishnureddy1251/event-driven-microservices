package com.eventdrivenmicroservices.api.service;

import ch.qos.logback.core.CoreConstants;
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

        System.out.println("\n SUBMITTING GRADE");
        System.out.println("_____________");
        System.out.println(" Student:" + name);
        System.out.println(" Subject:" + subject);
        System.out.println("Score" + score);

        Student student = new Student(nextId++, name, subject, score);
        students.add(student);

        System.out.println("Grade:" + student.getGrade());
        System.out.println("Student record created!");

        GradeSubmittedEvent event = new GradeSubmittedEvent(
                student.getName(),
                student.getSubject(),
                student.getScore(),
                student.getGrade(),
                getCurrentTimestamp()
        );

        eventPublisher.publishEvent(event);

        return student;

    }

}
