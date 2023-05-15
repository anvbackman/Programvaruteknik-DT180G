package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;

public abstract class BaseAbility {

    private int actionPointCost;
    private int energyCost;

    protected BaseAbility(int actionPointCost, int energyCost) {
        this.actionPointCost = actionPointCost;
        this.energyCost = energyCost;
    }

    protected boolean performAbility(String info, int amountOfTargets, int damage, boolean targetEnemies) {
        return performAbility(info, amountOfTargets, damage, targetEnemies);
    }

    public int getActionPointCost() {
        return actionPointCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public abstract boolean isMagic();

    public abstract boolean isHeal();

    public abstract int getAmountOfTargets();

    public abstract boolean execute(int attackValue, boolean targetEnemies);

    @Override
    public abstract String toString();

}


