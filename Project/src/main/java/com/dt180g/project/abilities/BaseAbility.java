package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;

/**
 * The abstract class BaseAbility representing all base abilities for the game.
 * The class provides methods to calculate and retrieve action point and energy point costs.
 * Subclasses of this class will implement all abstract methods
 * @author Andreas Backman
 */
public abstract class BaseAbility {

    // Variables for storing cost of action points and energy
    private int actionPointCost;
    private int energyCost;

    /**
     * Constructor for creating a BaseAbility object using parameters for storing action point and energy cost.
     *
     * @param actionPointCost the cost of action points.
     * @param energyCost the cost of energy.
     */
    protected BaseAbility(int actionPointCost, int energyCost) {
        this.actionPointCost = actionPointCost;
        this.energyCost = energyCost;
    }

    /**
     * Method for performing the ability using the given parameters.
     * @param info the name of the ability.
     * @param amountOfTargets the amount of targets.
     * @param damage the damage or heal made.
     * @param targetEnemies if enemy or hero should be targeted
     * @return the ability information
     */
    protected boolean performAbility(String info, int amountOfTargets, int damage, boolean targetEnemies) {
        String ability = String.format("%s", info + " (-" + actionPointCost + " AP, " + "-" + energyCost + " Energy)");
        if (isHeal()) {
            damage = - damage;
        }
        return GameEngine.INSTANCE.characterAttack(new AbilityInfo(ability, amountOfTargets, damage, targetEnemies, isMagic(), isHeal()));
    }

    /**
     * Method for getting the action point cost.
     * @return the action point cost.
     */
    public int getActionPointCost() {
        return actionPointCost;
    }

    /**
     * Method for getting the energy cost.
     * @return the energy cost.
     */
    public int getEnergyCost() {
        return energyCost;
    }

    /**
     * Method that checks if the ability is magic.
     * @return true if the ability is magic, false otherwise.
     */
    public abstract boolean isMagic();

    /**
     * Method that checks if the ability is a healing ability.
     * @return true if the ability is a healing ability, false otherwise.
     */
    public abstract boolean isHeal();

    /**
     * Method that returns the number of targets.
     * @return the number of targets.
     */
    public abstract int getAmountOfTargets();

    /**
     * Method for executing the ability with the given parameters.
     * @param attackValue the attack value of the ability.
     * @param targets true if the target is an enemy, otherwise false if the target is a hero.
     * @return true if executed, false otherwise.
     */
    public abstract boolean execute(int attackValue, boolean targets);

    /**
     * Method that takes an ability as a String
     * @return a String of the ability
     */
    @Override
    public abstract String toString();
}


