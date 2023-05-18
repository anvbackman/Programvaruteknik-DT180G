package com.dt180g.project.characters.heroes;

import com.dt180g.project.support.AppConfig;

public class Cleric extends BaseHero {

    public Cleric(String characterName) {
        super(characterName, AppConfig.ATTRIBUTE_VALUES_CLERIC_HERO);
    }
}
