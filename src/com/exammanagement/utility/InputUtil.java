package com.exammanagement.utility;

import com.exammanagement.exception.DateTimeParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputUtil {
    private InputUtil() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated.");
    }

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String readString(String roleOfTask) {
        while (true) {
            System.out.print(roleOfTask);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                LoggerUtil.logError("Invalid input. Please enter a valid value: ");
                continue;
            }
            try {
                return input;
            } catch (NullPointerException e) {
                LoggerUtil.logError("Please enter a value: ");
            }
        }
    }

    public static String readString(String roleOfTask, String existingData) {
        while (true) {
            System.out.print(roleOfTask + "(" + existingData + "):");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                LoggerUtil.logError("Invalid input. Please enter a valid value: ");
                continue;
            }
            try {
                return input;
            } catch (NullPointerException e) {
                LoggerUtil.logError("Please enter a value: ");
            }
        }
    }

    public static int readInt(String roleOfTask) {
        while (true) {
            System.out.print(roleOfTask);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                LoggerUtil.logError("Invalid input. Please enter a number: ");
            }
        }
    }

    public static int readInt(String roleOfTask, int existingData) {
        while (true) {
            System.out.print(roleOfTask + "(" + existingData + "):");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                LoggerUtil.logError("Invalid input. Please enter a number: ");
            }
        }
    }

    public static LocalDateTime readDateAndTime(String roleOfTask) {
        Pattern dateTimePattern = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})$");

        while (true) {
            System.out.print(roleOfTask);
            String input = scanner.nextLine().trim();
            Matcher matcher = dateTimePattern.matcher(input);

            if (!matcher.matches()) {
                System.out.println("Invalid format. Please enter in 'YYYY-MM-DD HH:MM' format.");
                continue; // Single continue statement to restart loop
            }

            try {
                LocalDateTime dateTime = LocalDateTime.parse(input, FORMATTER);
                if (dateTime.getYear() < LocalDateTime.now().getYear()) {
                    System.out.println("Invalid year! Must be the current year or later.");
                } else {
                    return dateTime; // Valid input, return the parsed date-time
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date-time value. Try again.");
            }
        }
    }

    public static LocalDateTime readDateAndTime(String roleOfTask, String existingData) {
        Pattern dateTimePattern = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})$");

        while (true) {
            System.out.print(roleOfTask + "(" + existingData + "):");
            String input = scanner.nextLine().trim();
            Matcher matcher = dateTimePattern.matcher(input);

            if (!matcher.matches()) {
                System.out.println("Invalid format. Please enter in 'YYYY-MM-DD HH:MM' format.");
                continue; // Single continue statement to restart loop
            }

            try {
                LocalDateTime dateTime = LocalDateTime.parse(input, FORMATTER);
                if (dateTime.getYear() < LocalDateTime.now().getYear()) {
                    System.out.println("Invalid year! Must be the current year or later.");
                } else {
                    return dateTime; // Valid input, return the parsed date-time
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date-time value. Try again.");
            }
        }
    }
}