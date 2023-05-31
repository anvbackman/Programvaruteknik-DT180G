package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The Class FocusedHeal representing the ability focused heal.
 * The class provides methods to calculate and retrieve action point and energy point costs.
 * The class extends the BaseAbility class and provides implementation for its abstract methods.
 * The ability is a healing and magical ability that heals a single character.
 * @author Andreas Backman
 */
public class FocusedHeal extends BaseAbility {

    // Variable for magical phrase.
    private String magicalPhrase;

    /**
     * Constructor that creates a FocusedHeal object.
     * The ability magical phrase and the cost of action points and energy are set
     * to the values defined in the AppConfig class
     */
    public FocusedHeal() {
        super(AppConfig.MEDIUM_AP_COST, AppConfig.LOW_ENERGY_COST);
        magicalPhrase = AppConfig.MAGICAL_PHRASE_4;
    }

    /**
     * Method for specifying if the ability is magical.
     *
     * @return true indicating that the ability is magical
     */
    @Override
    public boolean isMagic() {
        return true;
    }

    /**
     * Method for specifying if the ability is a healing ability.
     *
     * @return true indicating that the ability is a healing ability.
     */
    @Override
    public boolean isHeal() {
        return true;
    }

    /**
     * Method for getting amount of targets allowed for the ability from the AppConfig class.
     *
     * @return the allowed amount of targets.
     */
    @Override
    public int getAmountOfTargets() {
        return AppConfig.ABILITY_SINGLE_TARGET;
    }

    /**
     * Method for executing the ability using the attack value and the amount of target to be targeted.
     *
     * @param attackValue the attack value of the ability.
     * @param targets true if the target is an enemy, otherwise false if the target is a hero.
     * @return true if the ability was executed successfully, otherwise false.
     */
    @Override
    public boolean execute(int attackValue, boolean targets) {
        String info = toString();
        int amountOfTargets = getAmountOfTargets();
        int damage = attackValue;
        return performAbility(info, amountOfTargets, damage, targets);
    }

    /**
     * Method for returning a String of the ability including the magical phrase and the ability type.
     *
     * @return the string.
     */
    @Override
    public String toString() {
        return magicalPhrase + ": " + AppConfig.ABILITY_FOCUSED_HEAL;
    }
}
