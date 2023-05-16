package com.dt180g.project.support;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;
;

public class ActivityLogger {

    public static ActivityLogger INSTANCE;
    private Logger logger;

    private ActivityLogger() {
        logger = Logger.getLogger(ActivityLogger.class.getName());
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + System.lineSeparator();
            }
        });
        logger.addHandler(consoleHandler);
    }

    public static ActivityLogger getInstance() {
        return INSTANCE;
    }

    private void delayExecution() {
        try {
            Thread.sleep(AppConfig.SLEEP_DELAY);
        }
        catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }

    private void performLog(String message) {
        if (AppConfig.USE_SLEEP_DELAY) {
            delayExecution();
        }
        logger.info(message);
    }

    public void logRoundInfo(String round) {
        performLog(round);
    }

    public void logTurnInfo(String turn) {
        performLog(turn);
    }

    public void logAttack(String attack) {
        performLog(attack);
    }

    public void logDamage(String damage) {
        performLog(damage);
    }

    public void logDeath(String death) {
        performLog(death);
    }

    public void logHealing(String heal) {
        performLog(heal);
    }
}
