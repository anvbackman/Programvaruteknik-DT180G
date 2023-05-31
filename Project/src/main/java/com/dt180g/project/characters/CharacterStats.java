package com.dt180g.project.characters;

import com.dt180g.project.stats.*;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;
import java.util.*;

/**
 * The CharacterStats class represents the character stats.
 * The class stores and manages the attributes, traits and combat stats of the characters.
 * @author Andreas Backman
 */
public class CharacterStats {

    // Variable for the stats
    private Map<String, BaseStat> stats;

    /**
     * Constructor for creating a CharacterStats object with a parameter for attribute values.
     * The attributes are stored by getting the attribute names, iterating through them,
     * multiplying them with the attribute base value and adding them to stats.
     * The Traits and combat stats are added to stats manually since traits has different base values and
     * combat stats are built up of different attributes.
     *
     * @param attributeValues the characters attribute values as a list
     */
    public CharacterStats(List<Integer> attributeValues) {

        this.stats = new HashMap<>();

        List<String> attributeNames = StatsManager.INSTANCE.getAttributeNames();

        for (int i = 0; i < attributeValues.size(); i++) {
            String attributeName = attributeNames.get(i);
            int attributeValue = attributeValues.get(i) * AppConfig.ATTRIBUTE_BASE_VALUE;
            BaseStat attribute = new Attribute(attributeName, attributeValue);
            stats.put(attributeName, attribute);
        }

        stats.put(AppConfig.TRAIT_VITALITY, new Trait(AppConfig.TRAIT_VITALITY,
                AppConfig.TRAIT_VITALITY_BASE_VALUE));

        stats.put(AppConfig.TRAIT_ENERGY, new Trait(AppConfig.TRAIT_ENERGY,
                AppConfig.TRAIT_ENERGY_BASE_VALUE));

        stats.put(AppConfig.TRAIT_ATTACK_RATE, new Trait(AppConfig.TRAIT_ATTACK_RATE,
                AppConfig.TRAIT_ATTACK_RATE_BASE_VALUE));

        stats.put(AppConfig.TRAIT_DEFENSE_RATE, new Trait(AppConfig.TRAIT_DEFENSE_RATE,
                AppConfig.TRAIT_DEFENCE_RATE_BASE_VALUE));

        stats.put(AppConfig.COMBAT_STAT_ACTION_POINTS, new CombatStat(AppConfig.COMBAT_STAT_ACTION_POINTS,
                getStat(AppConfig.ATTRIBUTE_DEXTERITY), getStat(AppConfig.TRAIT_ATTACK_RATE)));

        stats.put(AppConfig.COMBAT_STAT_MAGIC_POWER, new CombatStat(AppConfig.COMBAT_STAT_MAGIC_POWER,
                getStat(AppConfig.ATTRIBUTE_INTELLIGENCE), getStat(AppConfig.TRAIT_ATTACK_RATE)));

        stats.put(AppConfig.COMBAT_STAT_HEALING_POWER, new CombatStat(AppConfig.COMBAT_STAT_HEALING_POWER,
                getStat(AppConfig.ATTRIBUTE_WILLPOWER), getStat(AppConfig.TRAIT_ATTACK_RATE)));

        stats.put(AppConfig.COMBAT_STAT_PHYSICAL_POWER, new CombatStat(AppConfig.COMBAT_STAT_PHYSICAL_POWER,
                getStat(AppConfig.ATTRIBUTE_STRENGTH), getStat(AppConfig.TRAIT_ATTACK_RATE)));
    }

    /**
     * Method for returning the stat name.
     *
     * @param statName the stat name
     * @return the stats name
     */
    public BaseStat getStat(String statName) {
        return stats.get(statName);
    }

    /**
     * Method for returning the stat value using the stats name.
     *
     * @param statName the stat name
     * @return the stats value
     */
    public int getStatValue(String statName) {
        BaseStat stat = getStat(statName);
        return stat.getModifiedValue();
    }

    /**
     * Method for returning the total amount of action points for the character.
     *
     * @return the total amount of action points
     */
    public int getTotalActionPoints() {
        return getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).getBaseValue();
    }

    /**
     * Method for returning the current amount of action points for the character.
     *
     * @return the current amount of action points
     */
    public int getCurrentActionPoints() {
        return getStatValue(AppConfig.COMBAT_STAT_ACTION_POINTS);
    }

    /**
     * Method for returning the total amount of hit points for the character.
     *
     * @return the total amount of hit points
     */
    public int getTotalHitPoints() {
        return getStat(AppConfig.TRAIT_VITALITY).getBaseValue();
    }

    /**
     * Method for returning the current amount of hit points for the character.
     *
     * @return the current amount of hit points
     */
    public int getCurrentHitPoints() {
        return getStatValue(AppConfig.TRAIT_VITALITY);
    }

    /**
     * Method for returning the total amount of energy for the character.
     *
     * @return the total amount of energy
     */
    public int getTotalEnergyLevel() {
        return getStat(AppConfig.TRAIT_ENERGY).getBaseValue();
    }

    /**
     * Method for returning the current amount of energy for the character.
     *
     * @return the current amount of energy
     */
    public int getCurrentEnergyLevel() {
        return getStatValue(AppConfig.TRAIT_ENERGY);
    }

    /**
     * Method for returning the defence rate of the character.
     *
     * @return the defence rate of the character
     */
    public int getDefenceRate() {
        return getStatValue(AppConfig.TRAIT_DEFENSE_RATE);
    }

    /**
     * Method for returning the attack rate of the character.
     *
     * @return the attack rate of the character
     */
    public int getAttackRate() {
        return getStatValue(AppConfig.TRAIT_ATTACK_RATE);
    }

    /**
     * Method for returning the physical power of the character.
     *
     * @return the physical power of the character
     */
    public int getPhysicalPower() {
        return getStatValue(AppConfig.COMBAT_STAT_PHYSICAL_POWER);
    }

    /**
     * Method for returning the magical power of the character.
     *
     * @return the magical power of the character
     */
    public int getMagicPower() {
        return getStatValue(AppConfig.COMBAT_STAT_MAGIC_POWER);
    }

    /**
     * Method for returning the healing power of the character.
     *
     * @return the healing power of the character
     */
    public int getHealingPower() {
        return getStatValue(AppConfig.COMBAT_STAT_HEALING_POWER);
    }

    /**
     * Method for adjusting the characters action points using the adjustStatDynamicModifier() method.
     *
     * @param adjust the action points
     */
    public void adjustActionPoints(int adjust) {
        adjustStatDynamicModifier(AppConfig.COMBAT_STAT_ACTION_POINTS, adjust);
    }

    /**
     * Method for adjusting the characters hit points using the adjustStatDynamicModifier() method.
     *
     * @param adjust the hit points
     */
    public void adjustHitPoints(int adjust) {
        adjustStatDynamicModifier(AppConfig.TRAIT_VITALITY, adjust);
    }

    /**
     * Method for adjusting the characters energy level using the adjustStatDynamicModifier() method.
     *
     * @param adjust the energy level
     */
    public void adjustEnergyLevel(int adjust) {
        adjustStatDynamicModifier(AppConfig.TRAIT_ENERGY, adjust);
    }

    /**
     * Method for adjusting the characters static stats by taking the stat name as a parameter
     * to get the correct stat to modify and then modifies ut using the specified value in
     * the adjust parameter.
     *
     * @param adjust the action points
     */
    public void adjustStatStaticModifier(String statName, int adjust) {

        BaseStat stat = stats.get(statName);

        if (stat != null) {
            stat.adjustStaticModifier(adjust);
        }
    }

    /**
     * Method for adjusting the characters static stats by taking the stat name as a parameter
     * to get the correct stat to modify and then modifies ut using the specified value in
     * the adjust parameter.
     *
     * @param adjust the action points
     */
    public void adjustStatDynamicModifier(String statName, int adjust) {

        BaseStat stat = stats.get(statName);

        if (stat != null) {
            stat.adjustDynamicModifier(adjust);
        }
    }

    /**
     * Method for resetting action points by getting the BaseStat object associated with the stats COMBAT_STAT key
     * and then resets its value.
     */
    public void resetActionPoints() {

        BaseStat stat = stats.get(AppConfig.COMBAT_STAT_ACTION_POINTS);

        if (stat != null) {
            stat.resetDynamicModifier();
        }
    }

    /**
     * Method for resetting hit points by getting the BaseStat object associated with the stats TRAIT key
     * and then resets its value.
     */
    public void resetHitPoints() {

        BaseStat stat = stats.get(AppConfig.TRAIT_VITALITY);

        if (stat != null) {
            stat.resetDynamicModifier();
        }
    }

    /**
     * Method for resetting energy level by getting the BaseStat object associated with the stats TRAIT key
     * and then resets its value.
     */
    public void resetEnergyLevel() {

        BaseStat stat = stats.get(AppConfig.TRAIT_ENERGY);

        if (stat != null) {
            stat.resetDynamicModifier();
        }
    }

    /**
     * Method for returning a formatted String of the characters stats.
     * It does so by splitting each row to be shown into different sections and then adding them
     * to the main format list.
     *
     * @return the header together with the format list via the class IOHelpers formatter
     */
    @Override
    public String toString() {

        List<List<String>> formatList = new ArrayList<>();

        List<String> formatListSection1 = new ArrayList<>();
        List<String> formatListSection2 = new ArrayList<>();
        List<String> formatListSection3 = new ArrayList<>();
        List<String> formatListSection4 = new ArrayList<>();

        String header = String.format("%s%s%n", AppConfig.ANSI_BLUE, "STATISTICS");

        // String variables to allow for easy modification of the formatting
        String formatStringBreak = "%s%-15s%s%3s%s%4s%-7s|";
        String formatStringNoBreak = "%s%-15s%s%3s%s%4s";


        formatListSection1.add(String.format(formatStringBreak, AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_STRENGTH, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_STRENGTH), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.ATTRIBUTE_STRENGTH).getTotalModifier(), AppConfig.ANSI_WHITE));
        formatListSection1.add(String.format(formatStringBreak, AppConfig.ANSI_GREEN, AppConfig.TRAIT_VITALITY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_VITALITY), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.TRAIT_VITALITY).getTotalModifier(), AppConfig.ANSI_WHITE));
        formatListSection1.add(String.format(formatStringNoBreak, AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_ACTION_POINTS, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_ACTION_POINTS), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).getTotalModifier()));

        formatList.add(formatListSection1);


        formatListSection2.add(String.format(formatStringBreak, AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_DEXTERITY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_DEXTERITY), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.ATTRIBUTE_DEXTERITY).getTotalModifier(), AppConfig.ANSI_WHITE));
        formatListSection2.add(String.format(formatStringBreak, AppConfig.ANSI_GREEN, AppConfig.TRAIT_ENERGY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_ENERGY), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.TRAIT_ENERGY).getTotalModifier(), AppConfig.ANSI_WHITE));
        formatListSection2.add(String.format(formatStringNoBreak, AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_PHYSICAL_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_PHYSICAL_POWER), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.COMBAT_STAT_PHYSICAL_POWER).getTotalModifier(), AppConfig.ANSI_WHITE));

        formatList.add(formatListSection2);


        formatListSection3.add(String.format(formatStringBreak, AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_INTELLIGENCE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_INTELLIGENCE), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.ATTRIBUTE_INTELLIGENCE).getTotalModifier(), AppConfig.ANSI_WHITE));
        formatListSection3.add(String.format(formatStringBreak, AppConfig.ANSI_GREEN, AppConfig.TRAIT_ATTACK_RATE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_ATTACK_RATE), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.TRAIT_ATTACK_RATE).getTotalModifier(), AppConfig.ANSI_WHITE));
        formatListSection3.add(String.format(formatStringNoBreak, AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_MAGIC_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_MAGIC_POWER), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.COMBAT_STAT_MAGIC_POWER).getTotalModifier(), AppConfig.ANSI_WHITE));

        formatList.add(formatListSection3);


        formatListSection4.add(String.format(formatStringBreak, AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_WILLPOWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_WILLPOWER), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.ATTRIBUTE_WILLPOWER).getTotalModifier(), AppConfig.ANSI_WHITE));
        formatListSection4.add(String.format(formatStringBreak, AppConfig.ANSI_GREEN, AppConfig.TRAIT_DEFENSE_RATE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_DEFENSE_RATE), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.TRAIT_DEFENSE_RATE).getTotalModifier(), AppConfig.ANSI_WHITE));
        formatListSection4.add(String.format(formatStringNoBreak + "\n", AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_HEALING_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_HEALING_POWER), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.COMBAT_STAT_HEALING_POWER).getTotalModifier(), AppConfig.ANSI_WHITE));

        formatList.add(formatListSection4);

        return header + IOHelper.formatAsTable(formatList);
    }
}
