package com.dt180g.project.gear;

import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.support.IOHelper;

import java.util.*;
import java.util.stream.Collectors;


public class GearManager {

    private static GearManager INSTANCE = new GearManager();

    private Map<String, List<Weapon>> weapons = new HashMap<>();
    private Map<String, List<Armor>> armorPieces = new HashMap<>();






    private GearManager() {

    }

    public static GearManager getInstance() {
        return INSTANCE;
    }

    public Map<String, List<Armor>> getAllMappedArmorPieces() {
        List<Map<String, String>> armor = IOHelper.readFromFile("gear_armor.json");
        for (Map<String, String> item : armor) {
            Armor armorItem = new Armor(
                    item.get("type")
            );
            String type = item.get("type");

            if (!armorPieces.containsKey(type)) {
                armorPieces.put(type, new ArrayList<>());
            }

            armorPieces.get(type).add(armorItem);
        }
        return armorPieces;
    }

    public Map<String, List<Weapon>> getAllMappedWeapons() {
        List<Map<String, String>> weapon = IOHelper.readFromFile("gear_weapons.json");
        for (Map<String, String> item : weapon) {
            Weapon weaponItem = new Weapon(
                    item.get("type")
            );
            String type = item.get("type");

            if (!weapons.containsKey(type)) {
                weapons.put(type, new ArrayList<>());
            }

            weapons.get(type).add(weaponItem);
        }
        System.out.println(weapons);
        return weapons;
    }

    public List<Weapon> getWeaponsOfType(String weaponType) {
        List<Weapon> weaponsOfType = new ArrayList<>();

        for (List<Weapon> weaponList : weapons.values()) {
            for (Weapon weapon : weaponList) {
                if (BaseGear.type) {
                    weaponsOfType.add(weapon);
                }
            }
        }
        return weaponsOfType;
    }

    public Weapon getRandomWeapon(Class<?> b) {

    }

    public Weapon getRandomWeapon(List<String> a) {


    }

    public Weapon getRandomOneHandedWeapon(Class<?> c) {

    }

    public Weapon getRandomOneHandedWeapon(List<String> d) {

    }

    public List<Armor> getAllArmorForRestriction(Class<?> e) {}

    public Armor getRandomArmorOfType(String armor, Class<?> f) {}

}
