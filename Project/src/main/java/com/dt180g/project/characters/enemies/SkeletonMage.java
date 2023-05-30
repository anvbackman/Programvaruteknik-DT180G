package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkeletonMage extends BaseEnemy {
    public SkeletonMage(int enemyNumber) {
        super(AppConfig.ENEMY_SKELETON_MAGE + " " + enemyNumber, AppConfig.ATTRIBUTE_VALUES_SKELETON_MAGE);
        List<String> enemyWeapon = Arrays.asList(AppConfig.WEAPON_STAFF, AppConfig.WEAPON_WAND);
        equipEnemy(enemyWeapon);

        for (Weapon weapon : getEquipment().getWeapons()) {
            getCharacterStats().adjustStatStaticModifier(weapon.getStat().getStatName(), weapon.getStat().getBaseValue());
        }

        addAbilities(Arrays.asList(new WeaponAttack(), new ElementalBolt(AppConfig.ELEMENT_FIRE),
                new ElementalBolt(AppConfig.ELEMENT_ICE), new ElementalBolt(AppConfig.ELEMENT_AIR),
                new ElementalBlast(AppConfig.ELEMENT_FIRE), new ElementalBlast(AppConfig.ELEMENT_ICE),
                new ElementalBlast(AppConfig.ELEMENT_AIR)));
    }
}
