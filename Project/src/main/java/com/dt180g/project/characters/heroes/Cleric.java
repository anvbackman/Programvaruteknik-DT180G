package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.FocusedHeal;
import com.dt180g.project.abilities.GroupHeal;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import java.util.Arrays;

/**
 * The class Cleric representing the cleric character for the game.
 * The class extends the BaseHero class and sets the character name, attribute values and equipment.
 * The Cleric class has the ability to perform basic weapon attack and has healing abilities.
 * @author Andreas Backman
 */
public class Cleric extends BaseHero {

    /**
     * Constructor that creates a new instance of the Cleric class
     * The character name is appended with the hero role and the characters ability values are set
     * The character is then equipped with the appropriate gear and its abilities are set.
     *
     * @param characterName the characters name
     */
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
