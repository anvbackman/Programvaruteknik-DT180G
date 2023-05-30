package com.dt180g.project.support;

import com.dt180g.project.abilities.AbilityInfo;
import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.characters.BaseCharacter;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;
;

public class ActivityLogger {

    public static ActivityLogger INSTANCE = new ActivityLogger();
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
        logger.setUseParentHandlers(false);
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
        performLog(String.format("%s%s%s%n", AppConfig.ANSI_PURPLE, round, AppConfig.ANSI_RESET));
    }

    public void logTurnInfo(String turn) {
        performLog(turn);
    }

    public void logAttack(String attack) {
        performLog(String.format("%9s%s%s", AppConfig.ANSI_GREEN, attack, AppConfig.ANSI_RESET));
    }

    public void logDamage(String damage) {
        performLog(String.format("%13s%s%s", AppConfig.ANSI_YELLOW, damage, AppConfig.ANSI_RESET));
    }

    public void logDeath(String death) {
        performLog(String.format("%13s%s%s", AppConfig.ANSI_RED, death, AppConfig.ANSI_RESET));
    }

    public void logHealing(String heal) {
        performLog(String.format("%13s%s%s", AppConfig.ANSI_CYAN, heal, AppConfig.ANSI_RESET));
    }
}
