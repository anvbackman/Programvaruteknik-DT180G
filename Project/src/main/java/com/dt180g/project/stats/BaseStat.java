package com.dt180g.project.stats;

public abstract class BaseStat {
    private String statName;
    private int baseValue;
    private int staticModifier;
    private int dynamicModifier;

    protected BaseStat(String statName, int baseValue) {
        this.statName = statName;
        this.baseValue = baseValue;
    }

    public void getStatName(String statName) {

    }

    public void getBaseValue(int baseValue) {
        baseValue = baseValue + staticModifier + dynamicModifier;

    }

    public int getModifiedValue() {
        return baseValue;
    }

    public int getTotalModifier() {
        return staticModifier + dynamicModifier;
    }

    public void getStaticModifier(int staticModifier) {}

    public void adjustStaticModifier(int ad) {}

    public void adjustDynamicModifier(int k) {}

    public void resetDynamicModifier() {}

    public String toString() {
        return statName;
    }
}
