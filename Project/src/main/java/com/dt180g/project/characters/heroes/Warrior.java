package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.*;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

public class Warrior extends BaseHero {

    public Warrior(String characterName) {
        super(characterName + " The " + AppConfig.HERO_WARRIOR, AppConfig.ATTRIBUTE_VALUES_WARRIOR_HERO);
        equipHero(Warrior.class);

        for (Weapon weapon : getEquipment().getWeapons()) {
            getCharacterStats().adjustStatStaticModifier(weapon.getStat().getStatName(), weapon.getStat().getBaseValue());
        }
        for (Armor armor : getEquipment().getArmorPieces()) {
            getCharacterStats().adjustStatStaticModifier(armor.getStat().getStatName(), armor.getStat().getBaseValue());
        }

        addAbilities(Arrays.asList(new WeaponAttack(), new HeavyAttack(), new Whirlwind()));
    }
}
