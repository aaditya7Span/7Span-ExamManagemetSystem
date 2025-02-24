package com.exammanagement.controller;

import com.exammanagement.exception.ExamNotFoundException;
import com.exammanagement.model.Department;
import com.exammanagement.model.Exam;
import com.exammanagement.model.Subject;
import com.exammanagement.service.ExamService;
import com.exammanagement.service.implementation.ExamServiceImpl;
import com.exammanagement.utility.InputUtil;
import com.exammanagement.utility.LoggerUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExamController {
 private final ExamService examService=new ExamServiceImpl();
    private static final String DATE_AND_TIME_FORMAT="yyyy-MM-dd HH:mm";

   public ExamController(){
        //temp value added
        Department department1 = new Department("computer");
        Subject subject1 = new Subject("digital forensics",department1,8);
        String tempDateTime = "2025-01-01 12:40";
        DateTimeFormatter tempFormatter = DateTimeFormatter.ofPattern(DATE_AND_TIME_FORMAT);
        LocalDateTime tempDateTime1 = LocalDateTime.parse(tempDateTime, tempFormatter);
        Exam exam1 = new Exam("winter-2022",tempDateTime1,120,70,23,department1,4,subject1);

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
            System.out.println("Choose an option: ");

            int choice = InputUtil.readInt();
            switch (choice) {
                case 1 -> addExam();
                case 2 -> updateExam();
                case 3 -> deleteExam();
                case 4 -> viewExamById();
                case 5 -> viewAllExams();
                case 6 -> {
                    LoggerUtil.logInfo("Exiting Exam Management System...");
                    return;
                }
                default -> LoggerUtil.logError("Invalid option. Please try again");
            }
        }
    }

    private void viewAllExams() {
        List<Exam> exams = examService.getAllExams();
        if (exams.isEmpty()) {
           LoggerUtil.logError("❌ No exams available.");
            return;
        }

        System.out.println("\n===== Exam List =====");
        System.out.println("ExamID  ExamName  ExamDateTime");
        for (Exam exam : exams) {
            System.out.println("📌 [" + exam.getExamId() + "] " + exam.getExamName() + " - " + exam.getDateTime());
        }
    }

    private void viewExamById() {
        System.out.println("Enter Exam ID to view: ");
        String examId = InputUtil.readString();

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
            LoggerUtil.logError("❌ " + e.getMessage());
        }
    }

    private void deleteExam() {
        System.out.println("Enter Exam ID to delete: ");
        String examId = InputUtil.readString();

        if (examService.deleteExam(examId)) {
            System.out.println("✅ Exam deleted successfully!");
        } else {
            LoggerUtil.logError("❌ Exam not found.");
        }
    }

    private void updateExam() {
        System.out.println("Enter Exam ID to update: ");
        String examId = InputUtil.readString();

        try {
            Exam existingExam = examService.findExamById(examId);
            Subject existingSubject=existingExam.getSubject();
            Department existingdepartment=existingExam.getDepartment();

            System.out.println("Enter New Exam Name (" + existingExam.getExamName() + "): ");
            String examName = InputUtil.readString();
            if (!examName.isEmpty()) existingExam.setExamName(examName);

            System.out.println("Enter New department (" + existingdepartment.getDepartmentName() + "): ");
            String examDepartment = InputUtil.readString();
            if (!examDepartment.isEmpty()) {
                existingdepartment.setDepartmentName(examDepartment);
            }

            System.out.println("Enter New semester (" + existingSubject.getSemester() + "): ");
            int subjectSemester = InputUtil.readInt();
            if (subjectSemester>0) {
                existingExam.setSemester(subjectSemester);
                existingSubject.setSemester(subjectSemester);
            }

            System.out.println("Enter New Subject Name (" + existingSubject.getSubjectName() + "): ");
            String subjectName = InputUtil.readString();
            if (!subjectName.isEmpty()) existingSubject.setSubjectName(subjectName);

            System.out.println("Enter New Total Marks (" + existingExam.getTotalMarks() + "): ");
            int totalMarks = InputUtil.readInt();
            if (totalMarks > 0) existingExam.setTotalMarks(totalMarks);

            System.out.println("Enter New Passing Marks (" + existingExam.getPassingMarks() + "): ");
            int passingMarks = InputUtil.readInt();
            if (passingMarks > 0) existingExam.setPassingMarks(passingMarks);

            System.out.println("Enter New Exam DateTime (YYYY-MM-DD HH:MM) [" + existingExam.getDateTime() + "]: ");
            String newDateTime = InputUtil.readString();
            if (!newDateTime.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_AND_TIME_FORMAT);
                existingExam.setDateTime(LocalDateTime.parse(newDateTime,formatter));
            }

            System.out.println("Enter New Duration (" + existingExam.getDuration() + " minutes): ");
            int duration = InputUtil.readInt();
            if (duration > 0) existingExam.setDuration(duration);

            examService.updateExam(examId, existingExam);
            System.out.println("✅ Exam updated successfully!");

        } catch (ExamNotFoundException e) {
            LoggerUtil.logError("❌ " + e.getMessage());
        }
    }

    private void addExam(){

        System.out.println("Enter Exam Name: ");
        String examName = InputUtil.readString();

        System.out.println("Enter Department Name: ");
        String departmentName = InputUtil.readString();
        Department department = new Department(departmentName);

        System.out.println("Enter Semester: ");
        int semester = InputUtil.readInt();

        System.out.println("Enter Subject Name: ");
        String subjectName = InputUtil.readString();
        Subject subject = new Subject(subjectName,department,semester);

        System.out.println("Enter Total Marks: ");
        int totalMarks = InputUtil.readInt();

        System.out.println("Enter Passing Marks: ");
        int passingMarks = InputUtil.readInt();

        System.out.println("Enter Exam DateTime (YYYY-MM-DD HH:MM): ");
        String  tempDateTime = InputUtil.readString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_AND_TIME_FORMAT);
        LocalDateTime dateTime = LocalDateTime.parse(tempDateTime, formatter);

        System.out.println("Enter Duration (minutes): ");
        int duration = InputUtil.readInt();

        Exam exam = new Exam(examName,dateTime,duration,totalMarks,passingMarks,department,semester,subject);
        examService.addExam(exam);

        System.out.println("✅ Exam added successfully!");
    }
}
