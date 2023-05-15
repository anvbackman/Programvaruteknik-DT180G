package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.*;
public class StatsManager {

    private static StatsManager INSTANCE = new StatsManager();

    private final List<String> attributeNames = Arrays.asList(
            AppConfig.ATTRIBUTE_STRENGTH, AppConfig.ATTRIBUTE_DEXTERITY,
            AppConfig.ATTRIBUTE_INTELLIGENCE, AppConfig.ATTRIBUTE_WILLPOWER);

    private final List<String> traitNames = Arrays.asList(
            AppConfig.TRAIT_VITALITY, AppConfig.TRAIT_ENERGY,
            AppConfig.TRAIT_ATTACK_RATE, AppConfig.TRAIT_DEFENSE_RATE);

    private final List<String> combatStatNames = Arrays.asList(
            AppConfig.COMBAT_STAT_ACTION_POINTS, AppConfig.COMBAT_STAT_PHYSICAL_POWER,
            AppConfig.COMBAT_STAT_MAGIC_POWER, AppConfig.COMBAT_STAT_HEALING_POWER);



    private StatsManager() {

    }

    public static StatsManager getInstance() {
        return INSTANCE;
    }

    public String getRandomAttributeName() {
        return attributeNames.get(Randomizer.INSTANCE.getRandomValue(attributeNames.size() - 1));
    }

    public String getRandomTraitName() {
        return traitNames.get(Randomizer.INSTANCE.getRandomValue(traitNames.size() - 1));
    }

    public List<String> getAttributeNames() {
        return attributeNames;
    }

    public List<String> getTraitNames() {
        return traitNames;
    }

    public List<String> getCombatStatNames() {
        return combatStatNames;
    }
}
