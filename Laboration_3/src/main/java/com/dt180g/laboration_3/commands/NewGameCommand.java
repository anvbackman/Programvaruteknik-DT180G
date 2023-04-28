package com.dt180g.laboration_3.commands;

import com.dt180g.laboration_3.receiver.HanoiEngine;

public class NewGameCommand implements CommandInterface {
    int discAmount;

    public NewGameCommand(int discAmount) {
        this.discAmount = discAmount;
    }

    public void execute() {
        HanoiEngine.INSTANCE.resetGame(discAmount);
    }

}
