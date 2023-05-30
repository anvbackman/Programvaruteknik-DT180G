package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;
import com.dt180g.project.support.AppConfig;

public class ElementalBolt extends BaseAbility {

    private String magicalPhrase;
    private String element;


    public ElementalBolt(String element) {
        super(AppConfig.MEDIUM_AP_COST, AppConfig.LOW_ENERGY_COST);
        this.element = element;
        magicalPhrase = AppConfig.MAGICAL_PHRASE_2;
    }

    public boolean isMagic() {
        return true;
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
        int damage = attackValue;
        return performAbility(info, amountOfTargets, damage, targets);


    }

    @Override
    public String toString() {
        return magicalPhrase + ": " + element + " " + AppConfig.ABILITY_ELEMENTAL_BOLT;
    }
}
