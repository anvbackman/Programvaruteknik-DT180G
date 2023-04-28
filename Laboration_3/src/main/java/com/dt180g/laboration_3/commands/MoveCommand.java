package com.dt180g.laboration_3.commands;

import com.dt180g.laboration_3.receiver.HanoiEngine;

public class MoveCommand implements CommandInterface {
    int fromTower;
    int toTower;

    public MoveCommand(int fromTower, int toTower) {
        this.fromTower = fromTower;
        this.toTower = toTower;
    }

    public void execute() {

    }

    public void unExecute() {

    }
}
