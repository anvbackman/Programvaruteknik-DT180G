package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import java.util.Arrays;
import java.util.List;

/**
 * The class LichLord representing the boss character for the game.
 * The class extends the BaseEnemy class and sets the character name, attribute values and equipment.
 * The LichLord class has the ability to perform basic weapon attack, two other melee attacks, a healing ability and
 * two magic abilities.
 * @author Andreas Backman
 */
public class LichLord extends BaseEnemy {
    /**
     * Constructor that creates a new instance of the LichLord class
     * The enemy type is appended with the enemies number and the enemies ability values are set
     * The enemy is then equipped with the appropriate gear and its abilities are set.
     */
    public LichLord() {
        super(AppConfig.ENEMY_LICH_LORD, AppConfig.ATTRIBUTE_VALUES_LICH_LORD);
        List<String> enemyWeapon = Arrays.asList(AppConfig.WEAPON_AXE, AppConfig.WEAPON_SWORD, AppConfig.WEAPON_SHIELD);
        equipEnemy(enemyWeapon);
        getCharacterStats().adjustStatStaticModifier(AppConfig.TRAIT_VITALITY, getCharacterStats().getCurrentHitPoints() * AppConfig.BOSS_HEALTH_MULTIPLIER);

        for (Weapon weapon : getEquipment().getWeapons()) {
            getCharacterStats().adjustStatStaticModifier(weapon.getStat().getStatName(), weapon.getStat().getBaseValue());
        }

        addAbilities(Arrays.asList(new WeaponAttack(), new HeavyAttack(), new Whirlwind(), new FocusedHeal(),
                new ElementalBolt(AppConfig.ELEMENT_FIRE), new ElementalBlast(AppConfig.ELEMENT_FIRE)));
    }
}
