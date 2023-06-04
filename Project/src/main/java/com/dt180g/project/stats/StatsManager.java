package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;
import java.util.*;

/**
 * The class StatsManager manages the stats attributes, traits and combat stats.
 * It provides methods to retrieve these values but also retrieve random attributes and traits.
 * @author Andreas Backman
 */
public class StatsManager {

    public static StatsManager INSTANCE = new StatsManager();

    // Variables for the attribute, trait and combat stat names
    private final List<String> attributeNames;

    private final List<String> traitNames;

    private final List<String> combatStatNames;

    /**
     * Private constructor that creates a StatsManager object that initializes
     * the attribute, trait and combat stat names as lists.
     */
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

    /**
     * Method for returning a random attribute name with the help of the Randomizer class.
     *
     * @return the random attribute name
     */
    public String getRandomAttributeName() {

        int randomAttribute = Randomizer.INSTANCE.getRandomValue(attributeNames.size() - 1);
        return attributeNames.get(randomAttribute);
    }

    /**
     * Method for returning a random trait name with the help of the Randomizer class.
     *
     * @return the random trait name
     */
    public String getRandomTraitName() {

        int randomTrait = Randomizer.INSTANCE.getRandomValue(traitNames.size() - 1);
        return traitNames.get(randomTrait);
    }

    /**
     * Method for returning a list of attribute names.
     *
     * @return the list of attribute names
     */
    public List<String> getAttributeNames() {
        return attributeNames;
    }

    /**
     * Method for returning a list of trait names.
     *
     * @return the list of trait names
     */
    public List<String> getTraitNames() {
        return traitNames;
    }

    /**
     * Method for returning a list of combat stat names.
     *
     * @return the list of combat stat names
     */
    public List<String> getCombatStatNames() {
        return combatStatNames;
    }
}
