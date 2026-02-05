package com.eventdrivenmicroservices.api.event;

public class GradeSubmittedEvent {

    private String studentName;
    private String subject;
    private int score;
    private String grade;
    private String timestamp;
    private String eventId;
    private String eventType;
    private boolean highScore;

    public GradeSubmittedEvent() {
        this.eventType = "GRADE_SUBMITTED";
        this.eventId = generateEventId();
    }

    public GradeSubmittedEvent(String studentName, String subject, int score,
                               String grade, String timestamp) {
        this.studentName = studentName;
        this.subject = subject;
        this.score = score;
        this.grade = grade;
        this.timestamp = timestamp;
        this.eventType = "GRADE_SUBMITTED";
        this.eventId = generateEventId();
        this.highScore = isHighScore();
    }

    private String generateEventId() {
        return "EVT-" + System.currentTimeMillis() + "-" +
                (int)(Math.random() * 1000);
    }

    public String getStudentName() {
        return studentName;
    }

    public String getSubject() {
        return subject;
    }

    public int getScore() {
        return score;
    }

    public String getGrade() {
        return grade;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public boolean getHighScore(){
        return highScore;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setHighScore(boolean highScore){
        this.highScore = highScore;
    }

    @Override
    public String toString() {
        return "GradeSubmittedEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", studentName='" + studentName + '\'' +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                ", grade='" + grade + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public boolean isHighScore() {
        return grade != null && (grade.equals("A") || grade.equals("B"));
    }

    public boolean needsAttention() {
        return grade != null && (grade.equals("D") || grade.equals("F"));
    }
}