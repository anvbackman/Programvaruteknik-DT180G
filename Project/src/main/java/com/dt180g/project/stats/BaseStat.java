package com.dt180g.project.stats;

/**
 * The abstract class BaseStat represents the base stats and modifiers of these stats.
 * It provides methods to retrieve and modify these values.
 * @author Andreas Backman
 */
public abstract class BaseStat {

    // Variables for the stat name, its base value and the static and dynamic modifiers
    private final String statName;
    private final int baseValue;
    private int staticModifier;
    private int dynamicModifier;

    /**
     * Constructor for creating a BaseStat object using parameters for the stats name and its base value
     * and initializes the modifiers.
     *
     * @param statName the name of the stat
     * @param baseValue the base value of the stat
     */
    protected BaseStat(String statName, int baseValue) {
        this.statName = statName;
        this.baseValue = baseValue;
        this.staticModifier = 0;
        this.dynamicModifier = 0;
    }

    /**
     * Method for getting the stat name.
     *
     * @return the name of the stat
     */
    public String getStatName() {
        return statName;
    }

    /**
     * Method for getting the stat base value.
     *
     * @return the base value of the stat
     */
    public int getBaseValue() {
        return baseValue;
    }

    /**
     * Method for getting the stat modified value by adding the base value with the modifiers.
     *
     * @return the modified value of the stat
     */
    public int getModifiedValue() {
        return getBaseValue() + staticModifier + dynamicModifier;
    }

    /**
     * Method for getting the stat total modifier value by adding the static and dynamic modifier.
     *
     * @return the total modified value of the stat
     */
    public int getTotalModifier() {
        return getStaticModifier() + dynamicModifier;
    }

    /**
     * Method for getting the static modified value.
     *
     * @return the static modifier value of the stat
     */
    public int getStaticModifier() {
        return staticModifier;
    }

    /**
     * Method to adjust the static modifier value.
     *
     * @param staticModifier the static modifier
     */
    public void adjustStaticModifier(int staticModifier) {
        this.staticModifier += staticModifier;
    }

    /**
     * Method to adjust the dynamic modifier value.
     *
     * @param dynamicModifier the dynamic modifier
     */
    public void adjustDynamicModifier(int dynamicModifier) {
        this.dynamicModifier += dynamicModifier;
    }

    /**
     * Method to reset the dynamic modifier to 0.
     */
    public void resetDynamicModifier() {
        this.dynamicModifier = 0;
    }

    /**
     * Method to return a String of the stat information including the name of the stat, the
     * modified value and the total modified value.
     *
     * @return the String of the stat information
     */
    @Override
    public String toString() {
        return getStatName().toString() + getModifiedValue() + getTotalModifier();
    }
}
