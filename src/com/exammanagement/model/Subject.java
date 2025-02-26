package com.exammanagement.model;

import java.util.UUID;

public class Subject {

    //Data fields for department class
    private final String subjectId;
    private String subjectName;
    private Department department;
    private int semester;


    //constructor to initialize the object values
    public Subject(String subjectName, Department department, int semester) {
        this.subjectId = UUID.randomUUID().toString();
        this.subjectName = subjectName;
        this.department = department;
        this.semester = semester;
    }


    //Getter and setter of the variable
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

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }


    //to print the object with all its values
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
