package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;
import com.dt180g.project.support.AppConfig;

public class GroupHeal extends BaseAbility {

    private String magicalPhrase;
    public GroupHeal() {
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
        magicalPhrase = AppConfig.MAGICAL_PHRASE_3;
    }

    public boolean isMagic() {
        return true;
    }

    public boolean isHeal() {
        return true;
    }

    public int getAmountOfTargets() {
        return AppConfig.ABILITY_GROUP_TARGET;
    }

    @Override
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
        return magicalPhrase + AppConfig.ABILITY_GROUP_HEAL + " (" + getActionPointCost() + getEnergyCost() + ")";
    }
}
