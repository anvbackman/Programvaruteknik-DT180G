package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;

/**
 * The class CombatStat extends the BaseStat class and represents combat stat values in the game.
 * It inherits the methods and properties of the BaseStat class.
 * @author Andreas Backman
 */
public class CombatStat extends BaseStat {

    // Variables for attribute and trait reliance
    private BaseStat attributeReliance;
    private BaseStat traitReliance;

    /**
     * Constructor for creating a CombatStat object with parameters for the stat name and base stats
     * for the attributes and traits to set the stat reliance.
     *
     * @param statName the name of the stat
     * @param attribute the attribute base value
     * @param trait the trait base value
     */
    public CombatStat(String statName, BaseStat attribute, BaseStat trait) {
        super(statName, 0);
        attributeReliance = attribute;
        traitReliance = trait;
    }

    /**
     * Method for returning the base value of the combat stat that has been calculated
     * using the stat reliance values by multiplying them with the constant COMBAT_STAT_MULTIPLIER.
     *
     * @return the base value of combat stat
     */
    @Override
    public int getBaseValue() {
        return (int) Math.round(attributeReliance.getModifiedValue() * AppConfig.COMBAT_STAT_MULTIPLIER +
                traitReliance.getModifiedValue() * AppConfig.COMBAT_STAT_MULTIPLIER);
    }
}
