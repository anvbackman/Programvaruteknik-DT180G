package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.*;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

public class Warrior extends BaseHero {

    public Warrior(String characterName) {
        super(characterName + " The " + AppConfig.HERO_WARRIOR, AppConfig.ATTRIBUTE_VALUES_WARRIOR_HERO);
        equipHero(Warrior.class);
        addAbilities(Arrays.asList(new WeaponAttack(), new HeavyAttack(), new Whirlwind()));
    }
}
