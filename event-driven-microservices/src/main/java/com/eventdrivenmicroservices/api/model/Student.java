package com.eventdrivenmicroservices.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

/**
 * 📚 STUDENT ENTITY
 * @author Vishnu
 * @version 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    private Long id;
    private String name;
    private String subject;
    private int score;
    private String grade;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Student(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Student(Long id, String name, String subject, int score){
        this();
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.score = score;
        this.grade = calculateGrade(score);
    }

    private String calculateGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public boolean isPassing(){
        return score >= 60;
    }

    public boolean isHonorStudent(){
        return score >=80;
    }

    public boolean needsAttention() {
        return score < 70;
    }

    public String getPerformanceLevel() {
        return switch (grade) {
            case "A" -> "Excellent";
            case "B" -> "Good";
            case "C" -> "Average";
            case "D" -> "Below Average";
            case "F" -> "Failing";
            default -> "Unknown";
        };
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        this.grade = calculateGrade(score);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString(){
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", score='" + score + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

}
