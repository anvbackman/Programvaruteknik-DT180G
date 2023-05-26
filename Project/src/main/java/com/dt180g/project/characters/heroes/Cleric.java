package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.*;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.stats.Trait;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

public class Cleric extends BaseHero {

    public Cleric(String characterName) {
        super(characterName + " The " + AppConfig.HERO_CLERIC, AppConfig.ATTRIBUTE_VALUES_CLERIC_HERO);
        equipHero(Cleric.class);

        for (Weapon weapon : getEquipment().getWeapons()) {
            getCharacterStats().adjustStatStaticModifier(weapon.getStat().getStatName(), weapon.getStat().getBaseValue());
        }
        for (Armor armor : getEquipment().getArmorPieces()) {
            getCharacterStats().adjustStatStaticModifier(armor.getStat().getStatName(), armor.getStat().getBaseValue());
        }

        addAbilities(Arrays.asList(new WeaponAttack(), new FocusedHeal(), new GroupHeal()));
    }
}
