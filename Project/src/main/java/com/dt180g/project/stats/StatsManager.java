package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.*;
public class StatsManager {

    public static StatsManager INSTANCE = new StatsManager();

    private final List<String> attributeNames;

    private final List<String> traitNames;

    private final List<String> combatStatNames;



    private StatsManager() {

        attributeNames = Arrays.asList(
                AppConfig.ATTRIBUTE_STRENGTH, AppConfig.ATTRIBUTE_DEXTERITY,
                AppConfig.ATTRIBUTE_INTELLIGENCE, AppConfig.ATTRIBUTE_WILLPOWER);

        traitNames = Arrays.asList(
                AppConfig.TRAIT_VITALITY, AppConfig.TRAIT_ENERGY,
                AppConfig.TRAIT_ATTACK_RATE, AppConfig.TRAIT_DEFENSE_RATE);

        combatStatNames = Arrays.asList(
                AppConfig.COMBAT_STAT_ACTION_POINTS, AppConfig.COMBAT_STAT_PHYSICAL_POWER,
                AppConfig.COMBAT_STAT_MAGIC_POWER, AppConfig.COMBAT_STAT_HEALING_POWER);

    }


    public String getRandomAttributeName() {
//        return attributeNames.get(Randomizer.INSTANCE.getRandomValue(attributeNames.size()));
        int randomAttribute = Randomizer.INSTANCE.getRandomValue(attributeNames.size() - 1);
        return attributeNames.get(randomAttribute);
    }

    public String getRandomTraitName() {
//        return traitNames.get(Randomizer.INSTANCE.getRandomValue(traitNames.size()));
        int randomTrait = Randomizer.INSTANCE.getRandomValue(attributeNames.size() - 1);
        return traitNames.get(randomTrait);
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
