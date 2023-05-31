package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The Class ElementalBolt representing the ability elemental bolt.
 * The class provides methods to calculate and retrieve action point and energy point costs.
 * The class extends the BaseAbility class and provides implementation for its abstract methods.
 * The ability is a non-healing magical ability that targets a single character
 * and the ability comes with different elements.
 * @author Andreas Backman
 */
public class ElementalBolt extends BaseAbility {

    // Variables for magical phrase and ability element.
    private String magicalPhrase;
    private String element;

    /**
     * Constructor that creates an ElementalBolt object with the specified element.
     * The ability magical phrase and the cost of action points and energy are set
     * to the values defined in the AppConfig class
     *
     * @param element the element chosen for the ability.
     */
    public ElementalBolt(String element) {
        super(AppConfig.MEDIUM_AP_COST, AppConfig.LOW_ENERGY_COST);
        this.element = element;
        magicalPhrase = AppConfig.MAGICAL_PHRASE_2;
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
     * Method for returning a String of the ability including the magical phrase, the element and the ability type.
     *
     * @return the string.
     */
    @Override
    public String toString() {
        return magicalPhrase + ": " + element + " " + AppConfig.ABILITY_ELEMENTAL_BOLT;
    }
}
