package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;

public class CombatStat extends BaseStat {

    private BaseStat attributeReliance;
    private BaseStat traitReliance;

    public CombatStat(String statName, BaseStat attributeReliance, BaseStat traitReliance) {
        super(statName, 0); // Set baseValue to 0 initially
        this.attributeReliance = attributeReliance;
        this.traitReliance = traitReliance;
    }

    public int getBaseValue() {
        double value = Math.round(attributeReliance.getModifiedValue() * AppConfig.COMBAT_STAT_MULTIPLIER + traitReliance.getModifiedValue() * AppConfig.COMBAT_STAT_MULTIPLIER);
        return (int) value;
    }
}
