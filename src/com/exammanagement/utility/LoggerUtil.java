package com.exammanagement.utility;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    private LoggerUtil() {
        throw new UnsupportedOperationException("LoggerUtil is a utility class and should not be instantiated.");
    }

    static {
        try {
            // Create a FileHandler to log messages into a file
            FileHandler fileHandler = new FileHandler("exam_management.log", true);
            fileHandler.setFormatter(new SimpleFormatter()); // Simple text format
            logger.addHandler(fileHandler);

            // Set logging level (INFO, WARNING, SEVERE, etc.)
            logger.setLevel(Level.ALL);

            // Prevent logging messages from appearing twice in console
            logger.setUseParentHandlers(false);

            // Console Handler (optional, if you want logs in the console too)
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);

        } catch (IOException e) {
            LoggerUtil.logError("Failed to initialize logger: " + e.getMessage());
        }
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarning(String message) {
        logger.warning(message);
    }

    public static void logError(String message) {
        logger.severe(message);
    }

    public static void logDebug(String message) {
        logger.fine(message);  // DEBUG-level logging
    }
}
