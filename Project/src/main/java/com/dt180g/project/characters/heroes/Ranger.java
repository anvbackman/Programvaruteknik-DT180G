package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.FocusedShot;
import com.dt180g.project.abilities.SprayOfArrows;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;


public class Ranger extends BaseHero {

    public Ranger(String characterName) {
        super(characterName + " The " + AppConfig.HERO_RANGER, AppConfig.ATTRIBUTE_VALUES_RANGER_HERO);

        equipHero(Ranger.class);

        addAbilities(Arrays.asList(new WeaponAttack(), new FocusedShot(), new SprayOfArrows()));
    }
}
