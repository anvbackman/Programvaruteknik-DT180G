package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import java.util.Arrays;
import java.util.List;

/**
 * The class SkeletonMage representing the skeleton mage character for the game.
 * The class extends the BaseEnemy class and sets the character name, attribute values and equipment.
 * The SkeletonMage class has the ability to perform basic weapon attack and two other magic abilities.
 * @author Andreas Backman
 */
public class SkeletonMage extends BaseEnemy {
    /**
     * Constructor that creates a new instance of the SkeletonMage class
     * The enemy type is appended with the enemies number and the enemies ability values are set
     * The enemy is then equipped with the appropriate gear and its abilities are set.
     * @param enemyNumber number of the enemy
     */
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
