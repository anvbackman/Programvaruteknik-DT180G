package com.dt180g.project.characters;

import com.dt180g.project.GameEngine;
import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.characters.enemies.*;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;
import com.dt180g.project.support.Randomizer;
import java.util.*;

/**
 * The abstract BaseCharacter class represents a base character in the game.
 * The class provides methods and functionality for the stats, equipment and abilities that
 * the characters will use and will perform actions during the game.
 * @author Andreas Backman
 */
public abstract class BaseCharacter {

    // Variables for character stats, equipment and abilities
    private CharacterStats characterStats;
    private CharacterEquipment equipment;
    private List<BaseAbility> abilities;

    /**
     * Constructor for creating a BaseCharacter object with a parameter for character stats.
     *
     * @param characterStats the stats for the character
     */
    protected BaseCharacter(CharacterStats characterStats) {
        this.characterStats = characterStats;
        this.equipment = new CharacterEquipment();
        this.abilities = new ArrayList<>();
    }

    /**
     * Method for returning the name of the character.
     *
     * @return the name of the character
     */
    public String getCharacterName() {
        return GameEngine.INSTANCE.getAllCharacters().toString();
    }

    /**
     * Method for returning the stats of the character.
     *
     * @return the stats of the character
     */
    public CharacterStats getCharacterStats() {
        return characterStats;
    }

    /**
     * Method for returning the equipment of the character.
     *
     * @return the equipment of the character
     */
    public CharacterEquipment getEquipment() {
        return equipment;
    }

    /**
     * Method for returning the current action points of the character.
     *
     * @return the current action points of the character
     */
    public int getActionPoints() {
        return characterStats.getCurrentActionPoints();
    }

    /**
     * Method for returning the hit points of the character.
     *
     * @return the hit points of the character
     */
    public int getHitPoints() {
        return characterStats.getStatValue(AppConfig.TRAIT_VITALITY);
    }

    /**
     * Method for returning the current energy level of the character.
     *
     * @return the current energy level of the character
     */
    public int getEnergyLevel() {
        return characterStats.getCurrentEnergyLevel();
    }

    /**
     * Method for returning the abilities of the character.
     *
     * @return the abilities of the character
     */
    public List<BaseAbility> getAbilities() {
        return abilities;
    }

    /**
     * Method for getting the turn information for the character.
     *
     * @param characterTurn the character who has the current turn
     * @return a formatted String depending on which characters turn it is currently
     */
    protected String getTurnInformation(String characterTurn) {

        String formatString = "%s[%s%s%s%s%s%s%s%s%s";

        if (this instanceof SkeletonArcher || this instanceof SkeletonWarrior || this instanceof SkeletonMage) {
            return String.format(formatString, AppConfig.ANSI_RED, AppConfig.CHARACTER_TYPE_ENEMY + " TURN] ", characterTurn, " | ", getActionPoints() + " AP ", " | ", getHitPoints() + " HP ", " | ", getEnergyLevel() + " Energy ", AppConfig.ANSI_RESET);
        }
        if (this instanceof LichLord) {
            return String.format(formatString, AppConfig.ANSI_RED, AppConfig.CHARACTER_TYPE_ENEMY + " BOSS TURN] ", characterTurn, " | ", getActionPoints() + " AP ", " | ", getHitPoints() + " HP ", " | ", getEnergyLevel() + " Energy ", AppConfig.ANSI_RESET);
        }
        else {
            return String.format(formatString, AppConfig.ANSI_BLUE, AppConfig.CHARACTER_TYPE_HERO + " TURN] ", characterTurn, " | ", getActionPoints() + " AP ", " | ", getHitPoints() + " HP ", " | ", getEnergyLevel() + " Energy ", AppConfig.ANSI_RESET);
        }
    }

    /**
     * Method for checking if the character is dead or alive by checking if the characters
     * current hit points is less or equal to 0.
     *
     * @return true if dead, false if alive
     */
    public boolean isDead() {
        return getHitPoints() <= 0;
    }

    /**
     * Method for adding abilities to the character.
     *
     * @param abilities the abilities to add to the character
     */
    protected void addAbilities(List<BaseAbility> abilities) {
        this.abilities = abilities;
    }

    /**
     * Method for executing the characters actions.
     * As long as the selected abilities is not empty and available action points is more or equal to the lowest
     * action point cost, the ability will continue to be executed
     * and as long as the amount of available action points and energy is more that the cost of the ability,
     * the ability may be executed.
     * The method then checks what type of ability that should be executed. Magical, healing or physical.
     *
     * @param targets true if the actions target enemies, otherwise if the actions target heroes it returns false.
     */
    protected void executeActions(boolean targets) {

        Deque<BaseAbility> selectedAbilities = determineActions();
        int magicDamage = getCharacterStats().getMagicPower();
        int physicalDamage = getCharacterStats().getPhysicalPower();
        int healingPower = getCharacterStats().getHealingPower();
        int availableActionPoints = getActionPoints();
        int availableEnergy = getEnergyLevel();

        while (!selectedAbilities.isEmpty() && availableActionPoints >= AppConfig.LOWEST_AP_COST) {
            BaseAbility baseAbility = selectedAbilities.pop();
            int actionPointCost = baseAbility.getActionPointCost();
            int energyCost = baseAbility.getEnergyCost();

            if (availableActionPoints >= actionPointCost && availableEnergy >= energyCost) {
                availableActionPoints -= actionPointCost;
                availableEnergy -= energyCost;
                characterStats.adjustActionPoints(- actionPointCost);
                characterStats.adjustEnergyLevel(- energyCost);

                if (baseAbility.isMagic() && !baseAbility.isHeal()) {
                    baseAbility.execute(magicDamage + energyCost, targets);
                }

                if (!baseAbility.isMagic() && !baseAbility.isHeal()) {
                    baseAbility.execute(physicalDamage + energyCost, targets);
                }

                if (baseAbility.isHeal()) {
                    baseAbility.execute(healingPower + energyCost, !targets);
                }
            }
        }
    }

    /**
     * Method for determine a random ability for the turn by iterating over them and adding the abilities
     * to the selectedAbilities Deque.
     *
     * @return a Deque of the turns selected abilities
     */
    private Deque<BaseAbility> determineActions() {

        Deque<BaseAbility> selectedAbilities = new ArrayDeque<>();
        List<BaseAbility> availableAbilitiesList = getAbilities();
        int actionsPerTurn = AppConfig.ACTIONS_PER_TURN;
        int availableAbilities = availableAbilitiesList.size();

        for (int i = 0; i < actionsPerTurn; i++) {
            int randomAbility = Randomizer.INSTANCE.getRandomValue(0, availableAbilities - 1);
            BaseAbility ability = availableAbilitiesList.get(randomAbility);
            selectedAbilities.add(ability);
        }

        return selectedAbilities;
    }

    /**
     * Method for calculating the damaged to be received depending on if the damage received is
     * magical or not.
     * It does this by using the characters amount of armor protection and defence rate.
     *
     * @param damage the damage received
     * @param isMagic true if the damage made is magical, false if it is physical
     * @return a list of the amount of mitigated damage and the actual damage taken
     */
    public List<Integer> registerDamage(int damage, boolean isMagic) {

        int defenceRate = getCharacterStats().getDefenceRate();
        int armorProtection = getEquipment().getTotalArmorProtection();
        int mitigateAmount;
        int actualDamage;

        if (isMagic) {
            actualDamage = Math.max(0, (damage - defenceRate));
        }
        else {
            int resist = damage - defenceRate;
            resist -= armorProtection;
            actualDamage = Math.max(0, resist);
        }

        mitigateAmount = damage - actualDamage;
        characterStats.adjustHitPoints(- actualDamage);

        return Arrays.asList(mitigateAmount, actualDamage);
    }

    /**
     * Method for registering the healing received.
     * It does this by checking how much hit points the character currently has.
     * If the amount of hit points + the healing amount exceeds the maximum amount of hit points
     * then the characters hit points is simply reset to max, to not exceed the maximum amount of hit points
     * allowed.
     * Else the character is healed with the amount of heal.
     *
     * @param heal the amount of healing
     * @return the current amount of hit points after receiving the healing.
     */
    public int registerHealing(int heal) {

        int currentHP = getHitPoints();
        int maxHP = characterStats.getTotalHitPoints();
        if (currentHP + heal > maxHP) {
            characterStats.resetHitPoints();
        }
        else {
            characterStats.adjustHitPoints(heal);
        }
        return getHitPoints();
    }

    /**
     * Method for resetting the characters action points and energy level at the start of a round.
     * It does this by deciding if the current amount + the round reset amount is larger that the maximum amount or not.
     * If it is, it simply resets the amount to max to not exceed the maximum amount allowed,
     * else it resets it using the round reset constant.
     */
    public void roundReset() {

        int actionPoints = AppConfig.ROUND_RESET_AP;
        int energyLevel = AppConfig.ROUND_RESET_ENERGY;
        int currentActionPoints = getActionPoints();
        int currentEnergyLevel = getEnergyLevel();
        int maxActionPoints = characterStats.getTotalActionPoints();
        int maxEnergyLevel = characterStats.getTotalEnergyLevel();

        if (currentActionPoints + actionPoints > maxActionPoints) {
            characterStats.resetActionPoints();
        }
        else {
            characterStats.adjustActionPoints(actionPoints);
        }

        if (currentEnergyLevel + energyLevel > maxEnergyLevel) {
            characterStats.resetEnergyLevel();
        }
        else {
            characterStats.adjustEnergyLevel(energyLevel);
        }
    }

    /**
     * Abstract method for performing the characters turn.
     */
    public abstract void doTurn();

    /**
     * Method for returning a String of the characters name, stats and equipment information.
     * @return the String
     */
    public String toString() {

        String header = String.format("%s****************************************\n%28s\n****************************************\n", AppConfig.ANSI_WHITE, getCharacterName());
        List<List<String>> character = new ArrayList<>();

        List<String> stats = new ArrayList<>();
        stats.add(getCharacterStats().toString());
        character.add(stats);

        List<String> equipment = new ArrayList<>();
        equipment.add(getEquipment().toString());
        character.add(equipment);

        return header + IOHelper.formatAsTable(character);
    }
}
