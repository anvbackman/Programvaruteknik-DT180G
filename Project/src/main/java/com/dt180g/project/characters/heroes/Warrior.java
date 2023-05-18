package com.dt180g.project.characters.heroes;

import com.dt180g.project.support.AppConfig;

public class Warrior extends BaseHero {

    public Warrior(String characterName) {
        super(characterName, AppConfig.ATTRIBUTE_VALUES_WARRIOR_HERO);
    }
}
