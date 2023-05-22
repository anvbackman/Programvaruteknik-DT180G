package com.dt180g.project.characters.heroes;

import com.dt180g.project.support.AppConfig;



public class Ranger extends BaseHero {

    public Ranger(String characterName) {
        super(characterName, AppConfig.ATTRIBUTE_VALUES_RANGER_HERO);
    }
}
