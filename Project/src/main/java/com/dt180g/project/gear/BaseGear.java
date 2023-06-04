package com.dt180g.project.gear;

import java.util.*;
import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.characters.heroes.BaseHero;

/**
 * The abstract class BaseGear represent the base class for weapons and armor.
 * It provides information about the gears type, name and class restrictions.
 * @author Andreas Backman
 */
public abstract class BaseGear {

    // Variables for the gears type, name and class restriction
    private String type;
    private String gearName;
    private List<Class<?>> classRestrictions;

    /**
     * Constructor for creating a BaseGear object with parameters for the gears type, name and class restrictions.
     * It creates a new ArrayList for the class restrictions, iterates over it, checks if the restriction
     * matches the class and then add the characters class to the class restrictions list.
     *
     * @param type
     * @param gearName
     * @param classRestrictions
     */
    protected BaseGear(String type, String gearName, String classRestrictions) {

        this.type = type;
        this.gearName = gearName;
        this.classRestrictions = new ArrayList<>();

        for (String restriction : classRestrictions.split(",")) {
            String className = BaseHero.class.getPackageName() + "." + restriction.trim();
            try {
                Class<?> characterClass = Class.forName(className);
                this.classRestrictions.add(characterClass);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for returning the gear type.
     *
     * @return the gear type
     */
    public String getType() {
        return type;
    }

    /**
     * Method for returning the gear class restrictions.
     *
     * @return the gear class restrictions
     */
    public List<Class<?>> getClassRestrictions() {
        return classRestrictions;
    }

    /**
     * Method for checking is the class is allowed to use the gear by checking if getClassRestrictions()
     * contain the character class.
     *
     * @param checkClassType the class to be checked
     * @return true if character class is allowed to use the gear, false otherwise
     */
    public boolean checkClassRestriction(Class<?> checkClassType) {
        return getClassRestrictions().contains(checkClassType);
    }

    /**
     * Abstract method used to get the base stat of the gear.
     *
     * @return the base stat of the gear
     */
    public abstract BaseStat getStat();

    /**
     * Method that returns a String of the gear name.
     *
     * @return a String of the gear name
     */
    @Override
    public String toString() {
        return gearName;
    }
}
