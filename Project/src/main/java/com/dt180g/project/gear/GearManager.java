package com.dt180g.project.gear;

import com.dt180g.project.support.IOHelper;
import com.dt180g.project.support.Randomizer;
import java.util.*;

/**
 * The GearManager class represents the gear manager which manages the weapons and armor.
 * The class provides methods to retrieve and generate random gear.
 * @author Andreas Backman
 */
public class GearManager {

    public static GearManager INSTANCE = new GearManager();

    // Variables for weapons and armor
    private Map<String, List<Weapon>> weapons;
    private Map<String, List<Armor>> armorPieces;

    /**
     * Constructor for creating a GearManager object with the mapped gear.
     */
    private GearManager() {
        armorPieces = getAllMappedArmorPieces();
        weapons = getAllMappedWeapons();
    }

    /**
     * Method to return the instance of the GearManager.
     *
     * @return the instance of the GearManager
     */
    public static GearManager getInstance() {
        return INSTANCE;
    }

    /**
     * Method to get all armor pieces from a JSON-file.
     * It then iterates over it and gets each armor type and its corresponding values and puts
     * the results in armorPieces as keys and values.
     *
     * @return the armorPieces
     */
    public Map<String, List<Armor>> getAllMappedArmorPieces() {

        armorPieces = new HashMap<>();
        List<Map<String, String>> armorList = IOHelper.readFromFile("gear_armor.json");

        for (Map<String, String> armorMap : armorList) {
            Armor armor = new Armor(armorMap);
            String armorType = armor.getType();
            List<Armor> armorMappedList = armorPieces.getOrDefault(armorType, new ArrayList<>());
            armorMappedList.add(armor);
            armorPieces.put(armorType, armorMappedList);
        }

        return armorPieces;
    }

    /**
     * Method to get all weapons from a JSON-file.
     * It then iterates over it and gets each weapon type and its corresponding values and puts
     * the results in weapons as keys and values.
     *
     * @return the weapons
     */
    public Map<String, List<Weapon>> getAllMappedWeapons() {

        weapons = new HashMap<>();
        List<Map<String, String>> weaponList = IOHelper.readFromFile("gear_weapons.json");

        for (Map<String, String> weaponMap : weaponList) {
            Weapon weapon = new Weapon(weaponMap);
            String weaponType = weapon.getType();
            List<Weapon> weaponMappedList = weapons.getOrDefault(weaponType, new ArrayList<>());
            weaponMappedList.add(weapon);
            weapons.put(weaponType, weaponMappedList);
        }

        return weapons;
    }

    /**
     * Method for returning the weapons type based on which type that is specified.
     *
     * @param weaponType the type of the weapon
     * @return weapons of the specified type
     */
    public List<Weapon> getWeaponsOfType(String weaponType) {
        return weapons.get(weaponType);
    }

    /**
     * Method for returning a random weapon for the specified class by checking for class restrictions.
     *
     * @param randomWeapon the random weapon based on the class
     * @return a random weapon for the specified class
     */
    public Weapon getRandomWeapon(Class<?> randomWeapon) {

        List<Weapon> aRandomWeapon = new ArrayList<>();

        for (List<Weapon> usableWeapons : weapons.values()) {
            for (Weapon weapon : usableWeapons) {
                if (weapon.checkClassRestriction(randomWeapon)) {
                    aRandomWeapon.add(weapon);
                }
            }
        }

        return aRandomWeapon.get(Randomizer.INSTANCE.getRandomValue(0, aRandomWeapon.size() - 1));
    }

    /**
     * Method for returning a random weapon for the specified weapon type.
     *
     * @param weaponType a list of weapon types
     * @return a random weapon for the specified weapon type
     */
    public Weapon getRandomWeapon(List<String> weaponType) {

        List<Weapon> aRandomWeapon = new ArrayList<>();

        for (List<Weapon> usableWeapons : weapons.values()) {
            for (Weapon weapon : usableWeapons) {
                if (weaponType.contains(weapon.getType())) {
                    aRandomWeapon.add(weapon);
                }
            }
        }

        return aRandomWeapon.get(Randomizer.INSTANCE.getRandomValue(0, aRandomWeapon.size() - 1));
    }

    /**
     * Method for returning a random one-handed weapon for the specified class by checking for class restrictions.
     *
     * @param randomOneHandedWeapon the random one-handed weapon based on the class
     * @return a random one-handed weapon for the specified class
     */
    public Weapon getRandomOneHandedWeapon(Class<?> randomOneHandedWeapon) {

        List<Weapon> aRandomOneHandedWeapon = new ArrayList<>();

        for (List<Weapon> usableWeapons : weapons.values()) {
            for (Weapon weapon : usableWeapons) {
                if (weapon.checkClassRestriction(randomOneHandedWeapon) && !weapon.isTwoHanded()) {
                    aRandomOneHandedWeapon.add(weapon);
                }
            }
        }

        return aRandomOneHandedWeapon.get(Randomizer.INSTANCE.getRandomValue(0, aRandomOneHandedWeapon.size() - 1));
    }

    /**
     * Method for returning a random one-handed weapon for the specified weapon type.
     *
     * @param weaponType a list of one-handed weapon types
     * @return a random one-handed weapon for the specified weapon type
     */
    public Weapon getRandomOneHandedWeapon(List<String> weaponType) {

        List<Weapon> aRandomWeapon = new ArrayList<>();

        for (List<Weapon> usableWeapons : weapons.values()) {
            for (Weapon weapon : usableWeapons) {
                if (weaponType.contains(weapon.getType()) && !weapon.isTwoHanded()) {
                    aRandomWeapon.add(weapon);
                }
            }
        }

        return aRandomWeapon.get(Randomizer.INSTANCE.getRandomValue(0, aRandomWeapon.size() - 1));
    }

    /**
     * Method for returning armor for the specified class by checking for class restrictions.
     *
     * @param armorForRestriction the armor based on class restrictions
     * @return armor for the specified class
     */
    public List<Armor> getAllArmorForRestriction(Class<?> armorForRestriction) {

        List<Armor> armorForClass = new ArrayList<>();

        // Iterate over each armor type
        for (List<Armor> armorList : armorPieces.values()) {
            // Filter armor pieces based on the specified class
            for (Armor armor : armorList) {
                if (armor.checkClassRestriction(armorForRestriction)) {
                    armorForClass.add(armor);
                }
            }
        }

        return armorForClass;
    }

    /**
     * Method for returning a random armor type for the specified armor type.
     *
     * @param armorType the type of armor
     * @param armorForRestriction the class restriction
     * @return a random armor piece based on the type
     */
    public Armor getRandomArmorOfType(String armorType, Class<?> armorForRestriction) {

        List<Armor> armorForClass = new ArrayList<>();

        for (List<Armor> armorList : armorPieces.values()) {

            for (Armor armor : armorList) {
                
                if (armor.checkClassRestriction(armorForRestriction) && armorType.contains(armor.getType())) {
                    armorForClass.add(armor);
                }
            }
        }

        return armorForClass.get(Randomizer.INSTANCE.getRandomValue(0, armorForClass.size() - 1));
    }
}
