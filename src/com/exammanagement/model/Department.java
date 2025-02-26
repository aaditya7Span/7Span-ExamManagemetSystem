package com.exammanagement.model;

import java.util.UUID;

public class Department {

    //Data fields for department class
    private final String departmentId;
    private String departmentName;


    //constructor to initialize the object values
    public Department(String name) {
        this.departmentId = UUID.randomUUID().toString();
        this.departmentName = name;
    }


    //Getter and setter of the variable
    public String getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String name) {
        this.departmentName = name;
    }


    //to print the object with all its values
    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
