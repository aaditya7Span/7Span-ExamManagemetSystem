package com.exammanagement.service.implementation;

import com.exammanagement.exception.ExamNotFoundException;
import com.exammanagement.model.Exam;
import com.exammanagement.service.ExamService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamServiceImpl implements ExamService {
    private final List<Exam> examList = new ArrayList<>();
    private final Map<String, Exam> examMap = new HashMap<>();

    @Override
    public void addExam(Exam exam) {
        examList.add(exam);
        examMap.put(exam.getExamId(), exam);
    }

    @Override
    public List<Exam> getAllExams() {
        return new ArrayList<>(examList);
    }

    @Override
    public Exam findExamById(String examId) throws ExamNotFoundException {
        Exam exam = examMap.get(examId);
        if (exam == null) {
            throw new ExamNotFoundException("Exam with ID " + examId + " not found.");
        }
        return exam;
    }

    @Override
    public boolean updateExam(String examId, Exam updatedExam) {
        return false;
    }

    @Override
    public boolean deleteExam(String examId) {
        Exam exam = examMap.remove(examId);
        if (exam != null) {
            examList.remove(exam);
            return true;
        }
        return false;
    }
}
