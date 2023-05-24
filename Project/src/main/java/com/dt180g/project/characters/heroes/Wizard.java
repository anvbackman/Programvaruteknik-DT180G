package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.*;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

public class Wizard extends BaseHero {

    public Wizard(String characterName) {
        super(characterName + " The " + AppConfig.HERO_WIZARD, AppConfig.ATTRIBUTE_VALUES_WIZARD_HERO);
//        equipHero(Weapon.class);
        equipHero(Wizard.class);
        addAbilities(Arrays.asList(new WeaponAttack(), new ElementalBolt(AppConfig.ELEMENT_FIRE),
                new ElementalBolt(AppConfig.ELEMENT_ICE), new ElementalBolt(AppConfig.ELEMENT_AIR),
                new ElementalBlast(AppConfig.ELEMENT_FIRE), new ElementalBlast(AppConfig.ELEMENT_ICE),
                new ElementalBlast(AppConfig.ELEMENT_AIR)));
    }
}
