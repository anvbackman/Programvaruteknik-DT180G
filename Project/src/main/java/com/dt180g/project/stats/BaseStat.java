package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;

public abstract class BaseStat {
    private String statName;
    private int baseValue;
    private int staticModifier;
    private int dynamicModifier;

    protected BaseStat(String statName, int baseValue) {
        this.statName = statName;
        this.baseValue = baseValue;
        this.staticModifier = 0;
        this.dynamicModifier = 0;
    }

    public String getStatName() {
        return statName;
    }

    public int getBaseValue() {

        return baseValue;
    }

    public int getModifiedValue() {
        return getBaseValue() + staticModifier + dynamicModifier;
    }

    public int getTotalModifier() {
        return staticModifier + dynamicModifier;
    }

    public int getStaticModifier() {
        return staticModifier;
    }

    public void adjustStaticModifier(int staticModifier) {
        this.staticModifier += staticModifier;
    }

    public void adjustDynamicModifier(int dynamicModifier) {
        this.dynamicModifier += dynamicModifier;
    }

    public void resetDynamicModifier() {
        this.dynamicModifier = 0;
    }

    @Override
    public String toString() {
        return getStatName() + getModifiedValue() + getTotalModifier();
    }
}
