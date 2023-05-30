package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkeletonArcher extends BaseEnemy {

    public SkeletonArcher(int enemyNumber) {
        super(AppConfig.ENEMY_SKELETON_ARCHER + " " + enemyNumber, AppConfig.ATTRIBUTE_VALUES_SKELETON_ARCHER);
        List<String> enemyWeapon = Arrays.asList(AppConfig.WEAPON_BOW, AppConfig.WEAPON_CROSSBOW);
        equipEnemy(enemyWeapon);

        for (Weapon weapon : getEquipment().getWeapons()) {
            getCharacterStats().adjustStatStaticModifier(weapon.getStat().getStatName(), weapon.getStat().getBaseValue());
        }

        addAbilities(Arrays.asList(new WeaponAttack(), new FocusedShot(), new SprayOfArrows()));
    }
}
