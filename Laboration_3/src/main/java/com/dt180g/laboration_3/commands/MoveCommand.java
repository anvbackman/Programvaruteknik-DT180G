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
    private int fromTower;
    private int toTower;

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
    @Override
    public void execute() {
        HanoiEngine.INSTANCE.performMove(fromTower, toTower, true);
        HanoiLogger.getInstance().logInfo(fromTower + " " + toTower);
    }

    /**
     * Method to undo the last move that the player has made by simply reversing the order
     * the method then logs the move using the given symbol
     */
    public void unExecute() {
        HanoiEngine.INSTANCE.performMove(toTower, fromTower, false);
        HanoiLogger.getInstance().logInfo(AppConfig.LOG_UNDO_SYMBOL);
    }
}
