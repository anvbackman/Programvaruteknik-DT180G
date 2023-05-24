package com.dt180g.project.characters;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.stats.Attribute;
import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;

import java.util.*;
public class CharacterStats {

    private Map<String, BaseStat> stats;

    public CharacterStats(List<Integer> attributeValues) {
        this.stats = new HashMap<>();
        List<String> attributeNames = Arrays.asList(AppConfig.ATTRIBUTE_STRENGTH, AppConfig.ATTRIBUTE_DEXTERITY,
                AppConfig.ATTRIBUTE_INTELLIGENCE, AppConfig.ATTRIBUTE_WILLPOWER);

        for (int i = 0; i < attributeValues.size(); i++) {
            String attributeName = attributeNames.get(i);
            int attributeValue = attributeValues.get(i);
            BaseStat attribute = new Attribute(attributeName, attributeValue * AppConfig.ATTRIBUTE_BASE_VALUE);
            stats.put(attributeName, attribute);
        }

//        stats.put(AppConfig.ATTRIBUTE_STRENGTH, new Attribute(AppConfig.ATTRIBUTE_STRENGTH, AppConfig.ATTRIBUTE_BASE_VALUE * 8));
//        stats.put(AppConfig.ATTRIBUTE_DEXTERITY, new Attribute(AppConfig.ATTRIBUTE_DEXTERITY, AppConfig.ATTRIBUTE_BASE_VALUE * 8));
//        stats.put(AppConfig.ATTRIBUTE_INTELLIGENCE, new Attribute(AppConfig.ATTRIBUTE_INTELLIGENCE, AppConfig.ATTRIBUTE_BASE_VALUE * 8));
//        stats.put(AppConfig.ATTRIBUTE_WILLPOWER, new Attribute(AppConfig.ATTRIBUTE_WILLPOWER, AppConfig.ATTRIBUTE_BASE_VALUE * 8));

        // Sets traits with default values
        stats.put(AppConfig.TRAIT_VITALITY, new Attribute(AppConfig.TRAIT_VITALITY, AppConfig.TRAIT_VITALITY_BASE_VALUE));
        stats.put(AppConfig.TRAIT_ENERGY, new Attribute(AppConfig.TRAIT_ENERGY, AppConfig.TRAIT_ENERGY_BASE_VALUE));

        // Sets combat stats based on attributes and traits
//        stats.put(AppConfig.COMBAT_STAT_PHYSICAL_POWER, new Attribute(AppConfig.COMBAT_STAT_PHYSICAL_POWER));
        stats.put(AppConfig.TRAIT_DEFENSE_RATE, new Attribute(AppConfig.TRAIT_DEFENSE_RATE, AppConfig.TRAIT_DEFENCE_RATE_BASE_VALUE));
        stats.put(AppConfig.TRAIT_ATTACK_RATE, new Attribute(AppConfig.TRAIT_ATTACK_RATE, AppConfig.TRAIT_ATTACK_RATE_BASE_VALUE));
        stats.put(AppConfig.COMBAT_STAT_MAGIC_POWER, new Attribute(AppConfig.COMBAT_STAT_MAGIC_POWER, AppConfig.ATTRIBUTE_BASE_VALUE));








    }

    public BaseStat getStat(String statName) {
        return stats.get(statName);
    }

    public int getStatValue(String statName) {
        BaseStat stat = getStat(statName);
        if (stat == null) {
            throw new IllegalArgumentException("Test" + statName);
        }

        return stat.getModifiedValue();
    }

    public int getTotalActionPoints() {
        return getStatValue(AppConfig.COMBAT_STAT_ACTION_POINTS);
    }

    public int getCurrentActionPoints() {
        return getStatValue(AppConfig.COMBAT_STAT_ACTION_POINTS);
    }

    public int getTotalHitPoints() {
        return getStatValue(AppConfig.ATTRIBUTE_WILLPOWER);
    }

    public int getCurrentHitPoints() {
        return getTotalHitPoints();
    }

    public int getTotalEnergyLevel() {
        return AppConfig.TRAIT_ENERGY_BASE_VALUE;
    }

    public int getCurrentEnergyLevel() {


        BaseStat energyStat = stats.get(AppConfig.TRAIT_ENERGY);


        return energyStat.getModifiedValue();

    }

    public int getDefenceRate() {
        return getStatValue(AppConfig.TRAIT_DEFENSE_RATE);
    }

    public int getAttackRate() {
        return getStatValue(AppConfig.TRAIT_ATTACK_RATE);
    }

    public int getPhysicalPower() {
        return getStatValue(AppConfig.COMBAT_STAT_PHYSICAL_POWER);
    }

    public int getMagicPower() {
        return getStatValue(AppConfig.COMBAT_STAT_MAGIC_POWER);
    }

    public int getHealingPower() {
        return getStatValue(AppConfig.COMBAT_STAT_HEALING_POWER);
    }

    public void adjustActionPoints(int adjustAP) {
        adjustStatDynamicModifier(AppConfig.COMBAT_STAT_ACTION_POINTS, adjustAP);
    }

    public void adjustHitPoints(int adjustHP) {
        adjustStatDynamicModifier(AppConfig.COMBAT_STAT_HEALING_POWER, adjustHP);
    }

    public void adjustEnergyLevel(int adjustEnergy) {
        adjustStatDynamicModifier(AppConfig.TRAIT_ENERGY, adjustEnergy);
    }

    public void adjustStatStaticModifier(String statName, int adjust) {
        BaseStat stat = stats.get(statName);
        if (stat != null) {
            stat.adjustStaticModifier(adjust);
        }
    }

    public void adjustStatDynamicModifier(String statName, int adjust) {
        BaseStat stat = stats.get(statName);
        if (stat != null) {
            stat.adjustDynamicModifier(adjust);
        }
    }

    public void resetActionPoints() {
        BaseStat stat = stats.get("Action Points");
        if (stat != null) {
        stat.resetDynamicModifier();
        }

    }

    public void resetHitPoints() {
        BaseStat stat = stats.get("Hit Points");
        if (stat != null) {
            stat.resetDynamicModifier();
        }
    }

    public void resetEnergyLevel() {
            BaseStat stat = stats.get("Energy Level");
            if (stat != null) {
                stat.resetDynamicModifier();
            }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<List<String>> tableData = new ArrayList<>();

        // Add header row
        List<String> headerRow = Arrays.asList("Stat Name", "Base Value", "Static Modifier", "Dynamic Modifier", "Total Value");
        tableData.add(headerRow);

        // Iterate over stats and collect information
        for (Map.Entry<String, BaseStat> entry : stats.entrySet()) {
            String statName = entry.getKey();
            BaseStat stat = entry.getValue();

            // Collect stat information
            int baseValue = stat.getBaseValue();
            int staticModifier = stat.getStaticModifier();
            int dynamicModifier = stat.getTotalModifier() - stat.getStaticModifier();
            int totalValue = baseValue + staticModifier + dynamicModifier;

            // Create a row for the stat
            List<String> statRow = Arrays.asList(statName, String.valueOf(baseValue),
                    String.valueOf(staticModifier), String.valueOf(dynamicModifier), String.valueOf(totalValue));
            tableData.add(statRow);
        }

        // Format the table data
        String formattedTable = IOHelper.formatAsTable(tableData);

        sb.append("Character Stats:\n");
        sb.append(formattedTable);

        return sb.toString();
    }
}
