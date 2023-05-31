package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The Class GroupHeal representing the ability group heal.
 * The class provides methods to calculate and retrieve action point and energy point costs.
 * The class extends the BaseAbility class and provides implementation for its abstract methods.
 * The ability is a healing and magical ability that heals multiple characters.
 * @author Andreas Backman
 */
public class GroupHeal extends BaseAbility {

    // Variable for magical phrase.
    private String magicalPhrase;

    /**
     * Constructor that creates a GroupHeal object.
     * The ability magical phrase and the cost of action points and energy are set
     * to the values defined in the AppConfig class
     */
    public GroupHeal() {
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
        magicalPhrase = AppConfig.MAGICAL_PHRASE_3;
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
        return AppConfig.ABILITY_GROUP_TARGET;
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
        return magicalPhrase + ": " + AppConfig.ABILITY_GROUP_HEAL;
    }
}
