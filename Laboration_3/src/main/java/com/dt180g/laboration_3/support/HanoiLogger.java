package com.dt180g.laboration_3.support;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.*;


public class HanoiLogger {



    private static HanoiLogger instance;
    private Logger logger;



    public static HanoiLogger getInstance() {
        if (instance == null) {
            instance = new HanoiLogger();
        }

        return instance;
    }


    public void logInfo(String message) {
        if (AppConfig.shouldUseLog() && logger != null) {
            logger.info((message));
        }
    }

    public void closeLogger() {
        for (Handler handler : logger.getHandlers()) {
            handler.close();
            logger.removeHandler(handler);
        }
    }

    public void resetLogger() {
        if (AppConfig.shouldUseLog()) {
            closeLogger();
            initializeLogger();
        }
    }

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
            logger.log(Level.INFO, "Error initializing the logger", e);
        }



    }

    private HanoiLogger() {

        if (AppConfig.shouldUseLog()) {
            initializeLogger();
        }
    }




//    private Logger logger = Logger.getLogger(HanoiLogger.class.getName());
//
//

//
//    public void closeLogger() {
//        LogManager.getLogManager().reset();
//    }
//
//    public void logInfo(String message) {
//        logger.info(message);
//    }
//
//    private void initializeLogger() {
//
//    }
//
//    private HanoiLogger() { initializeLogger(); }


}
