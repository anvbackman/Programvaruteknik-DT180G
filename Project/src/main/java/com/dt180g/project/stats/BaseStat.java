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

    public String getStatName(String statName) {
        return statName;
    }

    public int getBaseValue() {
        return baseValue;
    }

    public int getModifiedValue() {
        return baseValue;
    }

    public int getTotalModifier() {
        return staticModifier + dynamicModifier;
    }

    public int getStaticModifier() {
        return staticModifier;
    }

    public void adjustStaticModifier(int ad) {}

    public void adjustDynamicModifier(int k) {}

    public void resetDynamicModifier() {}

    @Override
    public String toString() {
        return getStatName(statName) + getModifiedValue() + getTotalModifier();
    }
}
