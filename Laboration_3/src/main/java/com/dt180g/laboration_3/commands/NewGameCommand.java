package com.dt180g.laboration_3.commands;

import com.dt180g.laboration_3.receiver.HanoiEngine;
import com.dt180g.laboration_3.support.HanoiLogger;

public class NewGameCommand implements CommandInterface {
    int discAmount;
    HanoiLogger logger = HanoiLogger.getInstance();

    public NewGameCommand(int discAmount) {
        this.discAmount = discAmount;
    }

    public void execute() {
        HanoiEngine.INSTANCE.resetGame(discAmount);
        logger.resetLogger();
        logger.logInfo(String.valueOf(discAmount));
    }

}
