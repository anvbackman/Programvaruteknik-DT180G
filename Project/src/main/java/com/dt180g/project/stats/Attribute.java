package com.dt180g.project.stats;

/**
 * The class Attribute extends the BaseStat class and represents an attribute in the game.
 * It inherits the methods and properties of the BaseStat class.
 * @author Andreas Backman
 */
public class Attribute extends BaseStat {

    /**
     * Constructor that creates an Attribute object with the parameters for the stat name and base value.
     *
     * @param statName the name of the stat
     * @param baseValue the base value of the stat
     */
    public Attribute(String statName, int baseValue) {
        super(statName, baseValue);
    }
}
