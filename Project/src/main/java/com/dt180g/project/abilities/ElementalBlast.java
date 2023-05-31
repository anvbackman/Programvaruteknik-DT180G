package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The Class ElementalBlast representing the ability elemental blast.
 * The class provides methods to calculate and retrieve action point and energy point costs.
 * The class extends the BaseAbility class.
 * @author Andreas Backman
 */
public class ElementalBlast extends BaseAbility {

    // Variables for magical phrase and ability element.
    private String magicalPhrase;
    private String element;

    /**
     * Constructor that creates an ElementalBlast object with the specified element.
     * The abilities magical phrase and the cost of action points and energy are set
     * to the values defined in the AppConfig class
     *
     * @param element the element chosen for the ability.
     */
    public ElementalBlast(String element) {
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
        this.element = element;
        magicalPhrase = AppConfig.MAGICAL_PHRASE_1;
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
     * @return false indicating that the ability is not a healing ability.
     */
    @Override
    public boolean isHeal() {
        return false;
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
     * Method for returning a String of the ability including the magical phrase, the element and the ability type.
     * @return the string.
     */
    @Override
    public String toString() {
        return magicalPhrase + ": " + element + " " + AppConfig.ABILITY_ELEMENTAL_BLAST;
    }
}
