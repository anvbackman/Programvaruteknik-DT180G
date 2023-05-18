package com.dt180g.project.characters;

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
            BaseStat attribute = new Attribute(attributeName, attributeValue);
            stats.put(attributeName, attribute);
        }









    }

    public BaseStat getStat(String statName) {
        return stats.get(statName);
    }

    public int getStatValue(String statValue) {
        
    }

    public int getTotalActionPoints() {

    }

    public int getCurrentActionPoints() {

    }

    public int getTotalHitPoints() {

    }

    public int getCurrentHitPoints() {

    }

    public int getTotalEnergyLevel() {

    }

    public int getCurrentEnergyLevel() {

    }

    public int getDefenceRate() {
        return getStatValue("Defence Rate");
    }

    public int getAttackRate() {
        return getStatValue("Attack Rate");
    }

    public int getPhysicalPower() {
        return getStatValue("Physical Power");
    }

    public int getMagicPower() {
        return getStatValue("Magic Power");
    }

    public int getHealingPower() {
        return getStatValue("Healing Power");
    }

    public void adjustActionPoints(int adjustAP) {
        adjustStatDynamicModifier("Action Points", adjustAP);
    }

    public void adjustHitPoints(int adjustHP) {
        adjustStatDynamicModifier("Hit Points", adjustHP);
    }

    public void adjustEnergyLevel(int adjustEnergy) {
        adjustStatDynamicModifier("Energy Level", adjustEnergy);
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

    }
}
