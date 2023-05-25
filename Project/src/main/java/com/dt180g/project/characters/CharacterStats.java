package com.dt180g.project.characters;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.stats.*;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;

import java.util.*;
public class CharacterStats {

    private Map<String, BaseStat> stats;

    public CharacterStats(List<Integer> attributeValues) {
        this.stats = new HashMap<>();
        List<String> attributeNames = StatsManager.INSTANCE.getAttributeNames();

        for (int i = 0; i < attributeValues.size(); i++) {
            String attributeName = attributeNames.get(i);
            int attributeValue = attributeValues.get(i) * AppConfig.ATTRIBUTE_BASE_VALUE;
            BaseStat attribute = new Attribute(attributeName, attributeValue);
            stats.put(attributeName, attribute);
        }



        // Sets traits with default values
        stats.put(AppConfig.TRAIT_VITALITY, new Trait(AppConfig.TRAIT_VITALITY, AppConfig.TRAIT_VITALITY_BASE_VALUE));
        stats.put(AppConfig.TRAIT_ENERGY, new Trait(AppConfig.TRAIT_ENERGY, AppConfig.TRAIT_ENERGY_BASE_VALUE));
        stats.put(AppConfig.TRAIT_ATTACK_RATE, new Trait(AppConfig.TRAIT_ATTACK_RATE, AppConfig.TRAIT_ATTACK_RATE_BASE_VALUE));
        stats.put(AppConfig.TRAIT_DEFENSE_RATE, new Trait(AppConfig.TRAIT_DEFENSE_RATE, AppConfig.TRAIT_DEFENCE_RATE_BASE_VALUE));


        stats.put(AppConfig.COMBAT_STAT_ACTION_POINTS, new CombatStat(AppConfig.COMBAT_STAT_ACTION_POINTS,
                getStat(AppConfig.ATTRIBUTE_DEXTERITY), getStat(AppConfig.TRAIT_ATTACK_RATE)));

        stats.put(AppConfig.COMBAT_STAT_MAGIC_POWER, new CombatStat(AppConfig.COMBAT_STAT_MAGIC_POWER,
                getStat(AppConfig.ATTRIBUTE_INTELLIGENCE), getStat(AppConfig.TRAIT_ATTACK_RATE)));

        stats.put(AppConfig.COMBAT_STAT_HEALING_POWER, new CombatStat(AppConfig.COMBAT_STAT_HEALING_POWER,
                getStat(AppConfig.ATTRIBUTE_WILLPOWER), getStat(AppConfig.TRAIT_ATTACK_RATE)));

        stats.put(AppConfig.COMBAT_STAT_PHYSICAL_POWER, new CombatStat(AppConfig.COMBAT_STAT_PHYSICAL_POWER,
                getStat(AppConfig.ATTRIBUTE_STRENGTH), getStat(AppConfig.TRAIT_ATTACK_RATE)));








    }

    public BaseStat getStat(String statName) {
        return stats.get(statName);
    }

    public int getStatValue(String statName) {
        BaseStat stat = getStat(statName);
        return stat.getModifiedValue();
    }

    public int getTotalActionPoints() {
        return getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).getBaseValue();
    }

    public int getCurrentActionPoints() {
        return getStatValue(AppConfig.COMBAT_STAT_ACTION_POINTS);
    }

    public int getTotalHitPoints() {
        return getStat(AppConfig.TRAIT_VITALITY).getBaseValue();
    }

    public int getCurrentHitPoints() {
        return getStatValue(AppConfig.TRAIT_VITALITY);
    }

    public int getTotalEnergyLevel() {
        return getStat(AppConfig.TRAIT_ENERGY).getBaseValue();
    }

    public int getCurrentEnergyLevel() {
        return getStatValue(AppConfig.TRAIT_ENERGY);
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

    public void adjustActionPoints(int adjust) {
        BaseStat adjustAP = stats.get(AppConfig.COMBAT_STAT_ACTION_POINTS);
        adjustAP.adjustDynamicModifier(adjust);
    }

    public void adjustHitPoints(int adjust) {
//        adjustStatDynamicModifier(AppConfig.COMBAT_STAT_HEALING_POWER, adjustHP);
        BaseStat adjustHP = stats.get(AppConfig.TRAIT_VITALITY);
        adjustHP.adjustDynamicModifier(adjust);
    }

    public void adjustEnergyLevel(int adjust) {
        BaseStat adjustEnergy = stats.get(AppConfig.TRAIT_ENERGY);
        adjustEnergy.adjustDynamicModifier(adjust);
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
        BaseStat stat = stats.get(AppConfig.COMBAT_STAT_ACTION_POINTS);
        if (stat != null) {
            stat.resetDynamicModifier();
        }

    }

    public void resetHitPoints() {
        BaseStat stat = stats.get(AppConfig.TRAIT_VITALITY);
        if (stat != null) {
            stat.resetDynamicModifier();
        }
    }

    public void resetEnergyLevel() {
        BaseStat stat = stats.get(AppConfig.TRAIT_ENERGY);
        if (stat != null) {
            stat.resetDynamicModifier();
        }
    }
    @Override
    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        List<List<String>> tableData = new ArrayList<>();
//
//        // Add header row
//        List<String> headerRow = Arrays.asList("Stat Name", "Base Value", "Static Modifier", "Dynamic Modifier", "Total Value");
//        tableData.add(headerRow);
//
//        // Iterate over stats and collect information
//        for (Map.Entry<String, BaseStat> entry : stats.entrySet()) {
//            String statName = entry.getKey();
//            BaseStat stat = entry.getValue();
//
//            // Collect stat information
//            int baseValue = stat.getBaseValue();
//            int staticModifier = stat.getStaticModifier();
//            int dynamicModifier = stat.getTotalModifier() - stat.getStaticModifier();
//            int totalValue = baseValue + staticModifier + dynamicModifier;
//
//            // Create a row for the stat
//            List<String> statRow = Arrays.asList(statName, String.valueOf(baseValue),
//                    String.valueOf(staticModifier), String.valueOf(dynamicModifier), String.valueOf(totalValue));
//            tableData.add(statRow);
//        }
//
//        // Format the table data
//        String formattedTable = IOHelper.formatAsTable(tableData);
//
////        sb.append("Character Stats:\n");
//        sb.append(formattedTable);
//
//        return sb.toString();


        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s%s%s%n", AppConfig.ANSI_BLUE, " STATISTICS ", AppConfig.ANSI_RESET));

        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_STRENGTH, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_STRENGTH), AppConfig.ANSI_YELLOW, getStat(AppConfig.ATTRIBUTE_STRENGTH).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.TRAIT_VITALITY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_VITALITY), AppConfig.ANSI_YELLOW, getStat(AppConfig.TRAIT_VITALITY).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_ACTION_POINTS, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_ACTION_POINTS), AppConfig.ANSI_YELLOW, getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%n", AppConfig.ANSI_WHITE + " | " + AppConfig.ANSI_RESET));

        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_DEXTERITY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_DEXTERITY), AppConfig.ANSI_YELLOW, getStat(AppConfig.ATTRIBUTE_DEXTERITY).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.TRAIT_ENERGY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_ENERGY), AppConfig.ANSI_YELLOW, getStat(AppConfig.TRAIT_ENERGY).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_PHYSICAL_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_PHYSICAL_POWER), AppConfig.ANSI_YELLOW, getStat(AppConfig.COMBAT_STAT_PHYSICAL_POWER).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%n", AppConfig.ANSI_WHITE + " | " + AppConfig.ANSI_RESET));

        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_INTELLIGENCE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_INTELLIGENCE), AppConfig.ANSI_YELLOW, getStat(AppConfig.ATTRIBUTE_INTELLIGENCE).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.TRAIT_ATTACK_RATE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_ATTACK_RATE), AppConfig.ANSI_YELLOW, getStat(AppConfig.TRAIT_ATTACK_RATE).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_MAGIC_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_MAGIC_POWER), AppConfig.ANSI_YELLOW, getStat(AppConfig.COMBAT_STAT_MAGIC_POWER).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%n", AppConfig.ANSI_WHITE + " | " + AppConfig.ANSI_RESET));

        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_WILLPOWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_WILLPOWER), AppConfig.ANSI_YELLOW, getStat(AppConfig.ATTRIBUTE_WILLPOWER).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.TRAIT_DEFENSE_RATE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_DEFENSE_RATE), AppConfig.ANSI_YELLOW, getStat(AppConfig.TRAIT_DEFENSE_RATE).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_HEALING_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_HEALING_POWER), AppConfig.ANSI_YELLOW, getStat(AppConfig.COMBAT_STAT_HEALING_POWER).getTotalModifier(), AppConfig.ANSI_RESET));
        sb.append(String.format("%s%n", AppConfig.ANSI_WHITE + " | " + AppConfig.ANSI_RESET));

        return sb.toString();
    }
}
