# **Exam Management System**

## **📌 Project Overview**

The **Exam Management System** is a Java-based application that helps educational institutions efficiently manage and
organize exams. It allows administrators to create, update, and manage exams, including scheduling, duration, subjects,
and grading criteria.

## **🚀 Features**

- 📅 **Exam Scheduling** – Set exam dates, duration, and details.
- 👨‍💼 **Exam Validation** - Validation to Set exam is done like passingmarks must be less than totalmarks and greater
  than 0, Date of Exam should be later than the present date and many more.
- 🏫 **Department & Subject Management** – Assign exams to specific departments and subjects.
- 📊 **Marks & Grading** – Define total marks, passing marks, and duration of Exam.
- 🔍 **Exam Search & View** – Retrieve exams using ID.
- 🔄 **Update & Delete Exams** – Modify existing exam details or remove exams.
- 🛠 **Exception Handling** – Custom error handling for invalid exam operations.

## **🛠 Technologies Used**

- **Java** – Core programming language.

## **📂 Project Structure**

```
ExamManagement/
│── src/
│   ├── com.exammanagement/
│   │   ├── controller/   # Contains ExamController
│   │   ├── exception/    # Custom exception handling  
│   │   ├── model/        # Contains Exam, Department, Subject classes  
│   │   ├── service/      # Business logic for managing exams  
│   │   ├── utility/      # Custom user user input fields  
│── .gitignore  
│── README.md  
│── 7Span ExamManagement.iml (to be removed from Git)  
```
