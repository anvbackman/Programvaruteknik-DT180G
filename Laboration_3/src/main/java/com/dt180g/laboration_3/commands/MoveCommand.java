package com.dt180g.laboration_3.commands;

import com.dt180g.laboration_3.receiver.HanoiEngine;
import com.dt180g.laboration_3.support.AppConfig;
import com.dt180g.laboration_3.support.HanoiLogger;

/**
 * MoveCommand class that is used to make the moves the player choses and also redo said move if the player choses
 * to do so.
 * @author Andreas Backman
 */
public class MoveCommand implements CommandInterface {

    // Variables to hold from and to which tower the player would like to move
    int fromTower;
    int toTower;
    // Getting the logger
    HanoiLogger logger = HanoiLogger.getInstance();

    /**
     * Constructor for setting *Tower to this.*Tower
     * @param fromTower the fromTower value
     * @param toTower the toTower value
     */
    public MoveCommand(int fromTower, int toTower) {
        this.fromTower = fromTower;
        this.toTower = toTower;
    }

    /**
     * Method to execute the moves selected and then log the move
     */
    public void execute() {
        HanoiEngine.INSTANCE.performMove(fromTower, toTower, true);
        logger.logInfo(fromTower + " " + toTower);
    }

    /**
     * Method to undo the last move that the player has made by simply reversing the order
     * the method then logs the move using the given symbol
     */
    public void unExecute() {
        HanoiEngine.INSTANCE.performMove(toTower, fromTower, false);
        logger.logInfo(AppConfig.LOG_UNDO_SYMBOL);
    }
}
