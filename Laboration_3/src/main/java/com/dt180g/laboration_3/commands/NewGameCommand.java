package com.dt180g.laboration_3.commands;

import com.dt180g.laboration_3.receiver.HanoiEngine;
import com.dt180g.laboration_3.support.AppConfig;
import com.dt180g.laboration_3.support.HanoiLogger;
import com.dt180g.laboration_3.validation.InvalidInputException;
import com.dt180g.laboration_3.validation.InvalidMoveException;

/**
 * NewGameCommand class that specifies how many discs will be used, check that the disc amount used is within the
 * allowed range (2 - 7) and resets the logger.
 * @author Andreas Backman
 */
public class NewGameCommand implements CommandInterface {
    // Variable for holding the selected disc amount
    private int discAmount;

    /**
     * Constructor for setting this.discAmount to discAmount*
     * @param discAmount the discAmount
     */
    public NewGameCommand(int discAmount) {
        this.discAmount = discAmount;
    }

    /**
     * Method to execute the new game by checking that the used discAmount is within the allowed range and then
     * initializes the game and resets the logger.
     */
    @Override
    public void execute() {

        if (discAmount < AppConfig.DISC_AMOUNT_MINIMUM) { discAmount = 2; }

        if (discAmount > AppConfig.DISC_AMOUNT_MAXIMUM) { discAmount = 7; }

        HanoiEngine.INSTANCE.resetGame(discAmount);
        HanoiLogger.getInstance().resetLogger();
        HanoiLogger.getInstance().logInfo(String.valueOf(discAmount));

    }
}
