package com.exammanagement.model;

import java.util.UUID;

public class Subject {
    private final String subjectId;
    private String subjectName;
    private Department department;
    private  int semester;

    public Subject(String subjectName, Department department, int semester) {
        this.subjectId = UUID.randomUUID().toString();
        this.subjectName = subjectName;
        this.department = department;
        this.semester = semester;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Department getDepartment() {
        return department;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", department=" + department +
                ", semester=" + semester +
                '}';
    }
}
