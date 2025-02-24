package com.exammanagement;

import com.exammanagement.controller.ExamController;

public class Main {
    public static void main(String[] args) {
        ExamController controller = new ExamController();
        controller.showMenu();
    }
}