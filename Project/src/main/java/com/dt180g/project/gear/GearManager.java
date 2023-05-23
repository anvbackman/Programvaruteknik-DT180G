package com.dt180g.project.gear;

import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.support.IOHelper;
import com.dt180g.project.support.Randomizer;

import java.util.*;
import java.util.stream.Collectors;


public class GearManager {

    public static GearManager INSTANCE = new GearManager();

    private Map<String, List<Weapon>> weapons;
    private Map<String, List<Armor>> armorPieces;






    private GearManager() {
        armorPieces = getAllMappedArmorPieces();
        weapons = getAllMappedWeapons();





    }

    public static GearManager getInstance() {
        return INSTANCE;
    }

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

    public List<Weapon> getWeaponsOfType(String weaponType) {
        return weapons.get(weaponType);
    }

    public Weapon getRandomWeapon(Class<?> randomWeapon) {
        List<Weapon> aRandomWeapon = new ArrayList<>();
        for (List<Weapon> usableWeapons : weapons.values()) {
            for (Weapon weapon : usableWeapons) {
                if (weapon.checkClassRestriction(randomWeapon)) {
                    aRandomWeapon.add(weapon);
                }
            }
        };
        return aRandomWeapon.get(Randomizer.INSTANCE.getRandomValue(0, aRandomWeapon.size() - 1));
    }

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

    public Armor getRandomArmorOfType(String armorType, Class<?> armorForRestriction) {
        List<Armor> armorForClass = new ArrayList<>();
        // Iterate over each armor type
        for (List<Armor> armorList : armorPieces.values()) {
            // Filter armor pieces based on the specified class
            for (Armor armor : armorList) {
                if (armor.checkClassRestriction(armorForRestriction) && armorType.contains(armor.getType())) {
                    armorForClass.add(armor);
                }
            }
        }
        return armorForClass.get(Randomizer.INSTANCE.getRandomValue(0, armorForClass.size() - 1));

    }

}
