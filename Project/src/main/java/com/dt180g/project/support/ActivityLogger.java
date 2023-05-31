package com.dt180g.project.support;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;


/**
 * The class ActivityLogger represents the games logging functionality during gameplay.
 * It does this by logging actions as a message to the console and is also responsible
 * for some formatting of said messages.
 * @author Andreas Backman
 */
public class ActivityLogger {

    public static ActivityLogger INSTANCE = new ActivityLogger();
    private Logger logger;

    /**
     * Constructor creating an ActivityLogger instance.
     * It does this by initializing the logger and by adding the console handler
     * and by disabling the parent handlers the messages are only printed to the console.
     */
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
        logger.setUseParentHandlers(false);
    }

    /**
     * Method for returning the ActivityLoggers instance.
     *
     * @return the instance
     */
    public static ActivityLogger getInstance() {
        return INSTANCE;
    }

    /**
     * Method for delaying the execution
     */
    private void delayExecution() {
        try {
            Thread.sleep(AppConfig.SLEEP_DELAY);
        }
        catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * Method for performing the log using the parameter message.
     * First it checks to see if a delay should happen and if not, the message is logged.
     *
     * @param message the message to be logged.
     */
    private void performLog(String message) {
        if (AppConfig.USE_SLEEP_DELAY) {
            delayExecution();
        }
        logger.info(message);
    }

    /**
     * Method for logging the round information.
     * It also formats the message.
     *
     * @param round the round information
     */
    public void logRoundInfo(String round) {
        performLog(String.format("%s%s%s%n", AppConfig.ANSI_PURPLE, round, AppConfig.ANSI_RESET));
    }

    /**
     * Method for logging the turn information.
     *
     * @param turn the turn information
     */
    public void logTurnInfo(String turn) {
        performLog(turn);
    }

    /**
     * Method for logging the attack information.
     * It also formats the message.
     *
     * @param attack the attack information
     */
    public void logAttack(String attack) {
        performLog(String.format("%9s%s%s", AppConfig.ANSI_GREEN, attack, AppConfig.ANSI_RESET));
    }

    /**
     * Method for logging the damage information.
     * It also formats the message.
     *
     * @param damage the damage information
     */
    public void logDamage(String damage) {
        performLog(String.format("%13s%s%s", AppConfig.ANSI_YELLOW, damage, AppConfig.ANSI_RESET));
    }

    /**
     * Method for logging the death information.
     * It also formats the message.
     *
     * @param death the death information
     */
    public void logDeath(String death) {
        performLog(String.format("%13s%s%s", AppConfig.ANSI_RED, death, AppConfig.ANSI_RESET));
    }

    /**
     * Method for logging the healing information.
     * It also formats the message.
     *
     * @param heal the healing information
     */
    public void logHealing(String heal) {
        performLog(String.format("%13s%s%s", AppConfig.ANSI_CYAN, heal, AppConfig.ANSI_RESET));
    }
}
