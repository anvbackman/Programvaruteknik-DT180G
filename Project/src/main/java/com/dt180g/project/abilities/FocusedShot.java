package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;
import com.dt180g.project.support.AppConfig;

public class FocusedShot extends BaseAbility {

    public FocusedShot() {
        super(AppConfig.MEDIUM_AP_COST, AppConfig.LOW_ENERGY_COST);
    }

    public boolean isMagic() {
        return false;
    }

    public boolean isHeal() {
        return false;
    }

    public int getAmountOfTargets() {
        return AppConfig.ABILITY_SINGLE_TARGET;
    }

    @Override
    public boolean execute(int attackValue, boolean targets) {
        String info = toString();
        int amountOfTargets = getAmountOfTargets();
        int damage = attackValue * AppConfig.SINGLE_TARGET_ABILITY_MULTIPLIER;
        boolean targetEnemies = targets;
        AbilityInfo abilityInfo = new AbilityInfo(info, amountOfTargets, damage, targetEnemies, isMagic(), isHeal());
        return GameEngine.INSTANCE.characterAttack(abilityInfo);

    }

    @Override
    public String toString() {
        return AppConfig.ABILITY_FOCUSED_SHOT + " (" + getActionPointCost() + getEnergyCost() + ")";
    }
}
