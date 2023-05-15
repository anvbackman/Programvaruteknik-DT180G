package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;

import java.util.*;
public class StatsManager {

    private static StatsManager INSTANCE = new StatsManager();

    private List<String> attributeNames;
    private List<String> traitNames;
    private List<String> combatStatNames;



    private StatsManager() {}

    public static StatsManager getInstance() {
        return INSTANCE;
    }

    public void getRandomAttributeName(String attributeNames) {
        Randomizer.
    }

    public void getRandomTraitName(String traitNames) {
//        this.traitNames = traitNames;
    }

    public void getAttributeNames(List<String> attributeNames) {
        this.attributeNames = attributeNames;
    }

    public void getTraitNames(List<String> traitNames) {
        this.traitNames = traitNames;
    }

    public void getCombatStatNames(List<String> combatStatNames) {
        this.combatStatNames = combatStatNames;
    }
}
