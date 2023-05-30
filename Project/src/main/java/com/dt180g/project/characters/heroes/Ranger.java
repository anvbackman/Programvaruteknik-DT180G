package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.*;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import java.util.Arrays;

/**
 * The class Ranger representing the ranger character for the game.
 * The class extends the BaseHero class and sets the character name, attribute values and equipment.
 * The Ranger class has the ability to perform basic weapon attack and two bow abilities.
 * @author Andreas Backman
 */
public class Ranger extends BaseHero {

    /**
     * Constructor that creates a new instance of the Ranger class
     * The character name is appended with the hero role and the characters ability values are set
     * The character is then equipped with the appropriate gear and its abilities are set.
     * @param characterName the characters name
     */
    public Ranger(String characterName) {
        super(characterName + " The " + AppConfig.HERO_RANGER, AppConfig.ATTRIBUTE_VALUES_RANGER_HERO);
        equipHero(Ranger.class);

        for (Weapon weapon : getEquipment().getWeapons()) {
            getCharacterStats().adjustStatStaticModifier(weapon.getStat().getStatName(), weapon.getStat().getBaseValue());
        }
        for (Armor armor : getEquipment().getArmorPieces()) {
            getCharacterStats().adjustStatStaticModifier(armor.getStat().getStatName(), armor.getStat().getBaseValue());
        }

        addAbilities(Arrays.asList(new WeaponAttack(), new FocusedShot(), new SprayOfArrows()));
    }
}
