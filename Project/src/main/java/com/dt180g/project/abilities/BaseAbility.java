package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;
import com.dt180g.project.support.AppConfig;

public abstract class BaseAbility {

    private int actionPointCost;
    private int energyCost;

    protected BaseAbility(int actionPointCost, int energyCost) {
        this.actionPointCost = actionPointCost;
        this.energyCost = energyCost;
    }

    protected boolean performAbility(String info, int amountOfTargets, int damage, boolean targetEnemies) {
        String ability = String.format("%s", info + " (-" + actionPointCost + " AP, " + "-" + energyCost + " Energy)");
        if (isHeal()) {
            damage = -damage;
            return GameEngine.INSTANCE.characterAttack(new AbilityInfo(ability, amountOfTargets, damage, targetEnemies, isMagic(), isHeal()));
        }
        else {
            return GameEngine.INSTANCE.characterAttack(new AbilityInfo(ability, amountOfTargets, damage, targetEnemies, isMagic(), isHeal()));
        }
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

    public abstract boolean execute(int attackValue, boolean targets); // I alla abilities execute kan det behövas if (targets) else return false;


    @Override
    public abstract String toString();

}


