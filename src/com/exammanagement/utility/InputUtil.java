package com.exammanagement.utility;

import com.exammanagement.exception.DateTimeParseException;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUtil {
    //to prevent these class from getting instantiated
    InputUtil() {
        //the exception is thrown if someone tries to make the object of the method using different methods
        throw new UnsupportedOperationException("Utility class - cannot be instantiated.");
    }

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Pattern dateTimePattern = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})$");

    /*Method overloading for two separate method usage to change the existing exam data and
                          second is to add a new exam data. */

    //to read any   [string value,Date and Time value,int value]   input in the program
    public static String readString(String roleOfTask, String existingData) {
        while (true) {
            System.out.print(existingData == null ? roleOfTask : roleOfTask + " (" + existingData + "): ");
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Invalid input. Please enter a valid value.");
        }
    }

    public static LocalDateTime readDateAndTime(String roleOfTask, String existingData) {
        while (true) {
            System.out.print(existingData == null ? roleOfTask : roleOfTask + " (" + existingData + ")");
            String input = scanner.nextLine().trim();

            // If input is empty and existingData is available, return existingData after validation
            if (input.isEmpty() && existingData != null) {
                try {
                    return LocalDateTime.parse(existingData, FORMATTER);
                } catch (DateTimeParseException e) {
                    System.out.println("Error: Invalid existing date format. Please re-enter.");
                }
                continue;  // Continue only if existingData is invalid
            }

            // Validate date-time format and values
            LocalDateTime validDateTime = validateAndParseDateTime(input);
            if (validDateTime != null) {
                return validDateTime;
            }
        }
    }

    private static LocalDateTime validateAndParseDateTime(String input) {
        Matcher matcher = dateTimePattern.matcher(input);
        if (!matcher.matches()) {
            System.out.println("Invalid format. Please enter in 'YYYY-MM-DD HH:MM' format.");
            return null;
        }

        try {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));
            int hour = Integer.parseInt(matcher.group(4));
            int minute = Integer.parseInt(matcher.group(5));

            // Validate month, day, hour, and minute values
            if (month < 1 || month > 12 || hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                System.out.println("Invalid Month, Hour, or Minute Value. Please enter a valid date and time.");
                return null;
            }

            int maxDaysInMonth = YearMonth.of(year, month).lengthOfMonth();
            if (day < 1 || day > maxDaysInMonth) {
                System.out.println("Invalid day! " + year + "-" + month + " has " + maxDaysInMonth + " days.");
                return null;
            }
            // Parse and validate date-time
            LocalDateTime dateTime = LocalDateTime.parse(input, FORMATTER);
            if (dateTime.isBefore(LocalDateTime.now())) {
                System.out.println("Invalid date-time! Must be in the present or future.");
                return null;
            }
            return dateTime;
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("Error: Unable to parse date-time. Please ensure the format is 'YYYY-MM-DD HH:MM'.");
            return null;
        }
    }

    public static int readInt(String roleOfTask, Integer existingData) {
        while (true) {
            System.out.print(existingData == null ? roleOfTask : roleOfTask + " (" + existingData + "): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty. Please enter a valid number.");
                continue;
            }

            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Error: Please enter a number greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
            }
        }
    }


    public static int readSemester(String roleOfTask, Integer existingData) {
        while (true) {
            System.out.print(existingData == null ? roleOfTask : roleOfTask + " (" + existingData + "): ");
            int semester = InputUtil.readInt("");

            if (semester >= 1 && semester <= 8) {
                return semester;
            }
            System.out.println("Invalid semester! Please enter a value between 1 and 8.");
        }
    }

    public static int readPassingMarks(String roleOfTask, Integer totalMarks, Integer existingPassingMarks) {
        while (true) {
            System.out.print(existingPassingMarks == null ? roleOfTask : roleOfTask + " (" + existingPassingMarks + "): ");
            int passingMarks = InputUtil.readInt("");

            if (passingMarks > 0 && passingMarks < totalMarks) {
                return passingMarks;
            }
            System.out.println("Invalid passing marks! It must be greater than 0 and less than total marks (" + totalMarks + ").");
        }
    }

    // overloaded method for when existingData is not provided
    public static String readString(String prompt) {
        return readString(prompt, null);
    }

    public static LocalDateTime readDateAndTime(String roleOfTask) {
        return readDateAndTime(roleOfTask, null);
    }

    public static int readInt(String roleOfTask) {
        return readInt(roleOfTask, null);
    }

    public static int readSemester(String roleOfTask) {
        return readSemester(roleOfTask, null);
    }

    public static int readPassingMarks(String roleOfTask, Integer totalMarks) {
        return readPassingMarks(roleOfTask, totalMarks, null);
    }
}