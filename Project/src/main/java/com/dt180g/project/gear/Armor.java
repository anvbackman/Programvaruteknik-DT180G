package com.dt180g.project.gear;

import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.stats.Trait;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * The Armor class represents the armor of the character.
 * The class extends the BaseGear class and adds additional properties specific to armor.
 * @author Andreas Backman
 */
public class Armor extends BaseGear {

    // Variables for amount of protection, armor material and trait.
    private final int protection;
    private final String material;
    private final Trait trait;

    /**
     * Constructor used to create an Armor object with a parameter to store armor.
     * It stores the type, name and class restriction and then assign the armor specific
     * protection, material and trait. The trait is randomized using the Randomizer class.
     *
     * @param armor a Map storing the armor
     */
    public Armor(Map<String, String> armor) {

        super(armor.get("type"), armor.get("name"), armor.get("restriction"));

        protection = parseInt(armor.get("protection"));
        material = armor.get("material");
        trait = new Trait(StatsManager.INSTANCE.getRandomTraitName(),
                Randomizer.INSTANCE.getRandomValue(0, AppConfig.ARMOR_STAT_VALUE_UPPER_BOUND));
    }

    /**
     * Method for returning the armor protection.
     *
     * @return the armor protection
     */
    public int getProtection() {
        return protection;
    }

    /**
     * Method for returning the armor material.
     *
     * @return the armor material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Method for returning the armor stat.
     *
     * @return the stat as trait
     */
    @Override
    public BaseStat getStat() {
        return trait;
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
