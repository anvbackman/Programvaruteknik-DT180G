package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import java.util.Arrays;
import java.util.List;

/**
 * The class SkeletonArcher representing the skeleton archer character for the game.
 * The class extends the BaseEnemy class and sets the character name, attribute values and equipment.
 * The SkeletonArcher class has the ability to perform basic weapon attack and two other ranged abilities.
 * @author Andreas Backman
 */
public class SkeletonArcher extends BaseEnemy {

    /**
     * Constructor that creates a new instance of the SkeletonArcher class
     * The enemy type is appended with the enemies number and the enemies ability values are set
     * The enemy is then equipped with the appropriate gear and its abilities are set.
     *
     * @param enemyNumber number of the enemy
     */
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
