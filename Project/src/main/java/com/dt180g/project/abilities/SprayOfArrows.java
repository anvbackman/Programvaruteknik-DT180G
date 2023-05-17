package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;
import com.dt180g.project.support.AppConfig;

public class SprayOfArrows extends BaseAbility {

    public SprayOfArrows() {
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
    }

    public boolean isMagic() {
        return false;
    }

    public boolean isHeal() {
        return false;
    }

    public int getAmountOfTargets() {
        return AppConfig.ABILITY_GROUP_TARGET;
    }

    public boolean execute(int attackValue, boolean targets) {
        String info = toString();
        int amountOfTargets = getAmountOfTargets();
        int damage = attackValue;
        boolean targetEnemies = targets;
        AbilityInfo abilityInfo = new AbilityInfo(info, amountOfTargets, damage, targetEnemies, isMagic(), isHeal());
        return GameEngine.INSTANCE.characterAttack(abilityInfo);

    }

    @Override
    public String toString() {
        return AppConfig.ABILITY_SPRAY_OF_ARROWS + " (" + getActionPointCost() + getEnergyCost() + ")";
    }
}
