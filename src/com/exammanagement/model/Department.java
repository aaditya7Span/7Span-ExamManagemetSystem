package com.exammanagement.model;

import java.util.UUID;

public class Department {
    private final String departmentId;
    private String departmentName;

    public Department(String name) {
        this.departmentId = UUID.randomUUID().toString();
        this.departmentName=name;
    }

    public String getDepartmentId() {return departmentId;}

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String name) {
        this.departmentName = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
