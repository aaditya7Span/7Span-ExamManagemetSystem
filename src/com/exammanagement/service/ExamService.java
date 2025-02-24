package com.exammanagement.service;

import com.exammanagement.model.Exam;

import java.util.List;

public interface ExamService {
 void addExam(Exam exam);
 List<Exam> getAllExams();
 Exam findExamById(String examId);
 boolean updateExam(String examId,Exam updatedExam);
 boolean deleteExam(String examId);
}
