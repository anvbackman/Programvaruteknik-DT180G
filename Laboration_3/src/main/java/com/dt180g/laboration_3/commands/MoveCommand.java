package com.dt180g.laboration_3.commands;

import com.dt180g.laboration_3.receiver.HanoiEngine;
import com.dt180g.laboration_3.support.AppConfig;
import com.dt180g.laboration_3.support.HanoiLogger;

public class MoveCommand implements CommandInterface {
    int fromTower;
    int toTower;
    HanoiLogger logger = HanoiLogger.getInstance();

    public MoveCommand(int fromTower, int toTower) {
        this.fromTower = fromTower;
        this.toTower = toTower;
    }

    public void execute() {
        HanoiEngine.INSTANCE.performMove(fromTower, toTower, true);
        logger.logInfo(fromTower + " " + toTower);
    }

    public void unExecute() {
        HanoiEngine.INSTANCE.performMove(toTower, fromTower, false);
        logger.logInfo(AppConfig.LOG_UNDO_SYMBOL);
    }
}
