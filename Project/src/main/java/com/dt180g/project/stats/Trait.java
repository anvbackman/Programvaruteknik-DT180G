package com.dt180g.project.stats;

/**
 * The class Trait extends the BaseStat class and represents a trait in the game.
 * It inherits the methods and properties of the BaseStat class.
 * @author Andreas Backman
 */
public class Trait extends BaseStat {

    /**
     * Constructor that creates a Trait object with the parameters for the stat name and base value.
     *
     * @param statName the name of the stat
     * @param baseValue the base value of the stat
     */
    public Trait(String statName, int baseValue) {
        super(statName, baseValue);
    }
}
