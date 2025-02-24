package com.exammanagement.utility;

import java.util.Scanner;

public class InputUtil {

    private InputUtil() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated.");
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString() {
        return scanner.nextLine().trim();
    }

    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                LoggerUtil.logError("❌ Invalid input. Please enter a number: ");
            }
        }
    }
}
