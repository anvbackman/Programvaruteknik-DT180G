package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.*;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

public class Wizard extends BaseHero {

    public Wizard(String characterName) {
        super(characterName + " The " + AppConfig.HERO_WIZARD, AppConfig.ATTRIBUTE_VALUES_WIZARD_HERO);
        equipHero(Wizard.class);

        for (Weapon weapon : getEquipment().getWeapons()) {
            getCharacterStats().adjustStatStaticModifier(weapon.getStat().getStatName(), weapon.getStat().getBaseValue());
        }
        for (Armor armor : getEquipment().getArmorPieces()) {
            getCharacterStats().adjustStatStaticModifier(armor.getStat().getStatName(), armor.getStat().getBaseValue());
        }

        addAbilities(Arrays.asList(new WeaponAttack(), new ElementalBolt(AppConfig.ELEMENT_FIRE),
                new ElementalBolt(AppConfig.ELEMENT_ICE), new ElementalBolt(AppConfig.ELEMENT_AIR),
                new ElementalBlast(AppConfig.ELEMENT_FIRE), new ElementalBlast(AppConfig.ELEMENT_ICE),
                new ElementalBlast(AppConfig.ELEMENT_AIR)));
    }
}
