package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.*;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import java.util.Arrays;

/**
 * The class Warrior representing the warrior character for the game.
 * The class extends the BaseHero class and sets the character name, attribute values and equipment.
 * The Warrior class has the ability to perform basic weapon attack and two other melee attacks.
 * @author Andreas Backman
 */
public class Warrior extends BaseHero {

    /**
     * Constructor that creates a new instance of the Warrior class
     * The character name is appended with the hero role and the characters ability values are set
     * The character is then equipped with the appropriate gear and its abilities are set.
     * @param characterName the characters name
     */
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
