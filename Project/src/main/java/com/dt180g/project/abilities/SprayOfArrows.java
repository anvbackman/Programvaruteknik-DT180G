package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The Class SprayOfArrows representing the ability spray of arrows.
 * The class provides methods to calculate and retrieve action point and energy point costs.
 * The class extends the BaseAbility class and provides implementation for its abstract methods.
 * The ability is a non-healing and non-magical ranged ability that attacks multiple characters.
 * @author Andreas Backman
 */
public class SprayOfArrows extends BaseAbility {

    /**
     * Constructor that creates a SprayOfArrows object.
     * The ability cost of action points and energy are set
     * to the values defined in the AppConfig class
     */
    public SprayOfArrows() {
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
    }

    /**
     * Method for specifying if the ability is magical.
     *
     * @return false indicating that the ability is not magical
     */
    @Override
    public boolean isMagic() {
        return false;
    }

    /**
     * Method for specifying if the ability is a healing ability.
     *
     * @return fals indicating that the ability is not a healing ability.
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
     * Method for returning a String including ability type.
     *
     * @return the string.
     */
    @Override
    public String toString() {
        return AppConfig.ABILITY_SPRAY_OF_ARROWS;
    }
}
