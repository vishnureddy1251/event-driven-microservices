package com.eventdrivenmicroservices.api.service;

import com.eventdrivenmicroservices.api.event.GradeSubmittedEvent;
import com.eventdrivenmicroservices.api.model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    private final List<Student> students = new ArrayList<>();
    private Long nextId = 1L;

    private String getCurrentTimestamp(){
        return LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss" )
        );
    }

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

    public List<Student> getAllStudents(){
        return new ArrayList<>(students);
    }

    public Student getStudentById(Long id){
        return students.stream()
                .filter(s-> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public double getAverageScore(){
        if (students.isEmpty()){
            return 0.0;
        }
        return students.stream()
                .mapToInt(Student::getScore)
                .average()
                .orElse(0.0);
    }

    public List<Student> getFailingStudents(){
        return students.stream()
                .filter(s-> s.getGrade().equals("F"))
                .collect(Collectors.toList());
    }

    public GradeStatistics getStatistics(){
        GradeStatistics stats = new GradeStatistics();
        stats.setTotalStudents(students.size());
        stats.setAverageScore(getAverageScore());
        stats.setTopStudents(getTopStudents().size());
        stats.setFailingStudents(getFailingStudents().size());
        stats.setQueueSize(eventPublisher.getQueueSize());

        return stats;
    }

    public List<Student> getTopStudents(){
        return students.stream()
                .filter(Student::isHonorStudent)
                .collect(Collectors.toList());
    }

    public List<Student> searchByName(String name){
        String searchTerm = name.toLowerCase();
        return students.stream()
                .filter(s-> s.getName().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    public List<Student> searchBySubject(String subject){
        return students.stream()
                .filter(s-> s.getSubject().equalsIgnoreCase(subject))
                .collect(Collectors.toList());
    }

    public void deleteAllStudents(){
        students.clear();
        nextId = 1L;
        System.out.println("All student records deleted");
    }

    public static class GradeStatistics{
        private int totalStudents;
        private double averageScore;
        private int topStudents;
        private int failStudents;
        private int queueSize;

        public int getTotalStudents(){
            return totalStudents;
        }
        public double getAverageScore(){
            return averageScore;
        }
        public int getTopStudents(){
            return topStudents;
        }
        public int getFailingStudents(){
            return failingStudents;
        }
        public int getQueueSize(){
            return queueSize;
        }

        public void setTotalStudents(int totalStudents){
            this.totalStudents = totalStudents;
        }
        public void setAverageScore(double averageScore){
            this.averageScore = averageScore;
        }
        public void setTopStudents(int topStudents){
            this.topStudents = topStudents;
        }
        public void setFailStudents(int failingStudents){
            this.failingStudents = failingStudents;
        }
        public void setQueueSize(int queueSize){
            this.queueSize = queueSize;
        }
    }

}
