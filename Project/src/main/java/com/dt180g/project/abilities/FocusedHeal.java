package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;
import com.dt180g.project.support.AppConfig;

public class FocusedHeal extends BaseAbility {
    private String magicalPhrase;

    public FocusedHeal() {
        super(AppConfig.MEDIUM_AP_COST, AppConfig.LOW_ENERGY_COST);
        magicalPhrase = AppConfig.MAGICAL_PHRASE_4;
    }

    public boolean isMagic() {
        return true;
    }

    public boolean isHeal() {
        return true;
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
        return magicalPhrase + ": " + AppConfig.ABILITY_FOCUSED_HEAL;
    }
}
