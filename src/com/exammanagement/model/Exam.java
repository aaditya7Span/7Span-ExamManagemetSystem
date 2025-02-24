package com.exammanagement.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Exam {
    private final String examId;
    private String examName;
    private LocalDateTime dateTime;
    private int duration;
    private int totalMarks;
    private int passingMarks;
    private  Department department;
    private  int semester;
    private  Subject subject;

    public Exam(String examName, LocalDateTime dateTime, int duration, int totalMarks, int passingMarks, Department department, int semester, Subject subject) {
        this.examId = UUID.randomUUID().toString();
        this.examName = examName;
        this.dateTime = dateTime;
        this.duration = duration;
        this.totalMarks = totalMarks;
        this.passingMarks = passingMarks;
        this.department = department;
        this.semester = semester;
        this.subject = subject;
    }

    public String getExamId() {
        return examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getPassingMarks() {
        return passingMarks;
    }

    public void setPassingMarks(int passingMarks) {
        this.passingMarks = passingMarks;
    }

    public Department getDepartment() {
        return department;
    }

    public int getSemester() {
        return semester;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId='" + examId + '\'' +
                ", examName='" + examName + '\'' +
                ", dateTime=" + dateTime +
                ", duration=" + duration +
                ", totalMarks=" + totalMarks +
                ", passingMarks=" + passingMarks +
                ", department=" + department +
                ", semester=" + semester +
                ", subject=" + subject +
                '}';
    }
}
