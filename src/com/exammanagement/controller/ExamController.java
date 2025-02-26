package com.exammanagement.controller;

import com.exammanagement.exception.ExamNotFoundException;
import com.exammanagement.model.Department;
import com.exammanagement.model.Exam;
import com.exammanagement.model.Subject;
import com.exammanagement.service.ExamService;
import com.exammanagement.service.implementation.ExamServiceImpl;
import com.exammanagement.utility.InputUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExamController {
    private final ExamService examService = new ExamServiceImpl();

    public ExamController() {
        Department department1 = new Department("computer");
        Subject subject1 = new Subject("digital forensics", department1, 4);
        String tempDateTime = "2025-01-01 12:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime tempDateTime1 = LocalDateTime.parse(tempDateTime, formatter);
        Exam exam1 = new Exam("winter-2022", tempDateTime1, 120, 70, 23, department1, 4, subject1);
        examService.addExam(exam1);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n===== Exam Management System =====");
            System.out.println("1. Add Exam");
            System.out.println("2. Update Exam");
            System.out.println("3. Delete Exam");
            System.out.println("4. View Exam by ID");
            System.out.println("5. View All Exams");
            System.out.println("6. Exit");
            System.out.print("Choose an option:");

            int choice = InputUtil.readInt("");
            switch (choice) {
                case 1 -> addExam();
                case 2 -> updateExam();
                case 3 -> deleteExam();
                case 4 -> viewExamById();
                case 5 -> viewAllExams();
                case 6 -> {
                    System.out.println("Exiting Exam Management System...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again");
            }
        }
    }

    private void viewAllExams() {
        List<Exam> exams = examService.getAllExams();
        if (exams.isEmpty()) {
            System.out.println("No exams available.");
            return;
        }

        System.out.println("\n===== Exam List =====");
        System.out.println("ExamID  ExamName  ExamDateTime");
        for (Exam exam : exams) {
            System.out.println("[" + exam.getExamId() + "] " + exam.getExamName() + " - " + exam.getDateTime());
        }
    }

    private void viewExamById() {
        String examId = InputUtil.readString("Enter Exam ID to view: ");

        try {
            Exam exam = examService.findExamById(examId);
            System.out.println("\n===== Exam Details =====");
            System.out.println("Exam ID: " + exam.getExamId());
            System.out.println("Name: " + exam.getExamName());
            System.out.println("Date & Time: " + exam.getDateTime());
            System.out.println("Duration: " + exam.getDuration() + " minutes");
            System.out.println("Total Marks: " + exam.getTotalMarks());
            System.out.println("Passing Marks: " + exam.getPassingMarks());
            System.out.println("Department: " + exam.getDepartment().getDepartmentName());
            System.out.println("Semester: " + exam.getSemester());
            System.out.println("Subject: " + exam.getSubject().getSubjectName());

        } catch (ExamNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteExam() {
        String examId = InputUtil.readString("Enter Exam ID to delete: ");

        if (examService.deleteExam(examId)) {
            System.out.println("Exam deleted successfully!");
        } else {
            System.out.println("Exam not found.");
        }
    }

    private void updateExam() {
        String examId = InputUtil.readString("Enter Exam ID to update: ");

        try {
            Exam existingExam = examService.findExamById(examId);
            Subject existingSubject = existingExam.getSubject();
            Department existingdepartment = existingExam.getDepartment();

            String examName = InputUtil.readString("Enter New Exam Name", existingExam.getExamName());
            if (!examName.isEmpty()) existingExam.setExamName(examName);

            String examDepartment = InputUtil.readString("Enter New department", existingdepartment.getDepartmentName());
            if (!examDepartment.isEmpty()) existingdepartment.setDepartmentName(examDepartment);

            int subjectSemester = InputUtil.readSemester("Enter New semester", existingExam.getSemester());
            if (subjectSemester > 0) {
                existingExam.setSemester(subjectSemester);
                existingSubject.setSemester(subjectSemester);
            }

            String subjectName = InputUtil.readString("Enter New Subject Name", existingSubject.getSubjectName());
            if (!subjectName.isEmpty()) existingSubject.setSubjectName(subjectName);

            int totalMarks = InputUtil.readInt("Enter New Total Marks", existingExam.getTotalMarks());
            if (totalMarks > 0) existingExam.setTotalMarks(totalMarks);

            int passingMarks = InputUtil.readPassingMarks("Enter New Passing Marks", existingExam.getTotalMarks(), existingExam.getPassingMarks());
            if (passingMarks > 0) existingExam.setPassingMarks(passingMarks);

            LocalDateTime newDateTime = InputUtil.readDateAndTime("Enter New Exam DateTime (YYYY-MM-DD HH:MM)", existingExam.getDateTime().toString());


            int duration = InputUtil.readInt("Enter New Duration", existingExam.getDuration());
            if (duration > 0) existingExam.setDuration(duration);

            examService.updateExam(examId, existingExam);
            System.out.println(existingExam);
            System.out.println("Exam updated successfully!");

        } catch (ExamNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addExam() {

        String examName = InputUtil.readString("Enter Exam Name: ");
        String departmentName = InputUtil.readString("Enter Department Name: ");

        Department department = new Department(departmentName);

        int semester = InputUtil.readSemester("Enter Semester: ");
        String subjectName = InputUtil.readString("Enter Subject Name: ");

        Subject subject = new Subject(subjectName, department, semester);

        int totalMarks = InputUtil.readInt("Enter Total Marks: ");
        int passingMarks = InputUtil.readPassingMarks("Enter Passing Marks: ", totalMarks);

        LocalDateTime dateTime = InputUtil.readDateAndTime("Enter Exam DateTime (YYYY-MM-DD HH:MM): ");
        int duration = InputUtil.readInt("Enter Duration (minutes): ");

        Exam exam = new Exam(examName, dateTime, duration, totalMarks, passingMarks, department, semester, subject);
        examService.addExam(exam);

        System.out.println("Exam added successfully!");
    }
}