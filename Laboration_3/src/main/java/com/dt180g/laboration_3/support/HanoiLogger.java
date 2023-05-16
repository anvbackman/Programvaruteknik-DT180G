package com.dt180g.laboration_3.support;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.*;

/**
 * HanoiLogger class which creates the logger and specifies when it will be used.
 * @author Andreas Backman
 */
public class HanoiLogger {


    // Variables for the HanoiLogger instance and the logger
    private static HanoiLogger instance;
    private Logger logger;


    /**
     * Constructor for getting the instance and creating a new logger if the instance is set to null
     * @return the instance
     */
    public static HanoiLogger getInstance() {
        if (instance == null) {
            instance = new HanoiLogger();
        }
        else if (instance.logger == null) {
            if (AppConfig.shouldUseLog()) {
                instance.initializeLogger();
            }
        }
        return instance;

    }

    /**
     * Method for logging the message when it should be logged and when the logger isn't null
     * @param message the logger text
     */
    public void logInfo(String message) {
        if (AppConfig.shouldUseLog() && logger != null) {
            logger.info((message));
        }
    }

    /**
     * Method to close the logger by iterating over logger.getHandlers() and closing them
     */
    public void closeLogger() {
        for (Handler handler : logger.getHandlers()) {
            handler.close();
            logger.removeHandler(handler);
        }
    }

    /**
     * Method for resetting the logger by checking if the logger should be used, closing it and then initializing it.
     */
    public void resetLogger() {
        if (AppConfig.shouldUseLog()) {
            closeLogger();
            initializeLogger();
        }
    }

    /**
     * Method for initializing the logger by setting the logger, creating a filehandler using the specified path
     * setting the logging level and formatting the log record. The method is also made to catch an erryr when
     * initializing the logger.
     */
    private void initializeLogger() {
        try {
            logger = Logger.getLogger(HanoiLogger.class.getName());
            FileHandler filehandler = new FileHandler(AppConfig.getLogFilePath());
            filehandler.setLevel(Level.INFO);
            logger.setUseParentHandlers(false);

            SimpleFormatter formatter = new SimpleFormatter() {
              @Override
              public synchronized String format(LogRecord logRecord) {
                  return String.format("%s%n", logRecord.getMessage());
              }
            };

            filehandler.setFormatter(formatter);

            logger.addHandler(filehandler);
        }

        catch(URISyntaxException | IOException e) {
            System.err.println("Error initializing the logger" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }



    }

    /**
     * Constructor for initialize the logger if the logger should be used.
     */
    private HanoiLogger() {

        if (AppConfig.shouldUseLog()) {
            initializeLogger();
        }
        else {
            logger = null;
        }
    }



}
