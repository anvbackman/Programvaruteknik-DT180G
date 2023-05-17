package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;
import com.dt180g.project.support.AppConfig;

public class ElementalBlast extends BaseAbility {

    private String magicalPhrase;
    private String element;

    public ElementalBlast(String element) {
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
        this.element = element;
        magicalPhrase = AppConfig.MAGICAL_PHRASE_1;
    }

    public boolean isMagic() {
        return true;
    }

    public boolean isHeal() {
        return false;
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
        return magicalPhrase + element + AppConfig.ABILITY_ELEMENTAL_BLAST + " (" + getActionPointCost() + getEnergyCost() + ")";
    }
}
