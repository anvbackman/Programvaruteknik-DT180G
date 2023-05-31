package com.dt180g.project.gear;

import com.dt180g.project.stats.Attribute;
import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;
import java.util.Map;

/**
 * The Weapon class represents the weapon of the character.
 * The class extends the BaseGear class and adds additional properties specific to weapons.
 * @author Andreas Backman
 */
public class Weapon extends BaseGear {

    // Variables for weapon damage, weapon wield and attribute
    private int damage;
    private String wield;
    private Attribute attribute;

    /**
     * Constructor used to create an Weapon object with a parameter to store weapons.
     * It stores the type, name and class restriction and then assign the weapon specific
     * damage, wield and attribute. The attribute is randomized using the Randomizer class.
     *
     * @param weapon a Map storing the weapons
     */
    public Weapon(Map<String, String> weapon) {
        super(weapon.get("type"), weapon.get("name"), (weapon.get("restriction")));

        damage = Integer.parseInt(weapon.get("damage"));
        wield = weapon.get("wield");
        attribute = new Attribute(StatsManager.INSTANCE.getRandomAttributeName(),
                Randomizer.INSTANCE.getRandomValue(0, AppConfig.WEAPON_ATTRIBUTE_VALUE_UPPER_BOUND));
    }

    /**
     * Method for returning the weapon damage.
     *
     * @return the weapon damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Method for returning the weapon wield.
     *
     * @return the weapon wield
     */
    public String getWield() {
        return wield;
    }

    /**
     * Method for returning the weapon stat.
     *
     * @return the stat as attribute
     */
    @Override
    public BaseStat getStat() {
        return attribute;
    }

    /**
     * Method for checking if the weapon is two-handed.
     *
     * @return true if two-handed, false if one-handed
     */
    public boolean isTwoHanded() {
        return wield.contains("Two Handed");
    }

    /**
     * Method that returns a formatted String containing the gear name and stat name.
     *
     * @return the formatted String
     */
    @Override
    public String toString() {
        return String.format("%s of %s", super.toString(), getStat().getStatName());
    }
}
