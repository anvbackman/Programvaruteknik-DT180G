package com.dt180g.project.gear;

import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.support.IOHelper;
import com.dt180g.project.support.Randomizer;

import java.util.*;
import java.util.stream.Collectors;


public class GearManager {

    public static GearManager INSTANCE = new GearManager();

    private Map<String, List<Weapon>> weapons = new HashMap<>();
    private Map<String, List<Armor>> armorPieces = new HashMap<>();






    private GearManager() {
        List<Map<String, String>> weaponList = IOHelper.readFromFile("gear_weapons.json)");
        for (Map<String, String> weaponData : weaponList) {
            Weapon weapon = new Weapon(weaponData);
            String weaponType = weapon.getType();
            weapons.computeIfAbsent(weaponType, key -> new ArrayList<>()).add(weapon);
        }

        List<Map<String, String>> armorDataList = IOHelper.readFromFile("gear_armor.json");
        for (Map<String, String> armorData : armorDataList) {
            Armor armor = new Armor(armorData);
            String armorType = armor.getType();
            armorPieces.computeIfAbsent(armorType, key -> new ArrayList<>()).add(armor);
        }

    }

    public static GearManager getInstance() {
        return INSTANCE;
    }

    public Map<String, List<Armor>> getAllMappedArmorPieces() {
        return armorPieces;
    }

    public Map<String, List<Weapon>> getAllMappedWeapons() {
        return weapons;

    }

    public List<Weapon> getWeaponsOfType(String weaponType) {
        return weapons.getOrDefault(weaponType, new ArrayList<>());
    }

    public Weapon getRandomWeapon(Class<?> randomWeapon) {
        // Get all weapons from the class
        List<Weapon> weaponOfClass = weapons.values().stream().flatMap(Collection::stream).filter(weapon -> weapon.getClass().equals(randomWeapon)).collect(Collectors.toList());
        // Return a random weapon from the filtered list
        if (!weaponOfClass.isEmpty()) {
            int aRandom = Randomizer.INSTANCE.getRandomValue(weaponOfClass.size());
            return weaponOfClass.get(aRandom);
        }
        return null;
    }

    public Weapon getRandomWeapon(List<String> randomWeapon) {
        List<Weapon> weaponsOfType = randomWeapon.stream()
                .flatMap(type -> weapons.getOrDefault(type, Collections.emptyList()).stream()).collect(Collectors.toList());

        // Return a random weapon from the filtered list
        if (!weaponsOfType.isEmpty()) {
            int randomIndex = new Random().nextInt(weaponsOfType.size());
            return weaponsOfType.get(randomIndex);
        }

        return null;
    }



    public Weapon getRandomOneHandedWeapon(Class<?> randomOneHandedWeapon) {
        // Get all one-handed weapons of the specified class
        List<Weapon> oneHandedWeapons = weapons.values().stream()
                .flatMap(Collection::stream)
                .filter(weapon -> weapon.getClass().equals(randomOneHandedWeapon))
                .filter(weapon -> !weapon.isTwoHanded())
                .collect(Collectors.toList());

        // Return a random one-handed weapon from the filtered list
        if (!oneHandedWeapons.isEmpty()) {
            int randomIndex = new Random().nextInt(oneHandedWeapons.size());
            return oneHandedWeapons.get(randomIndex);
        }

        return null; // or throw an exception, depending on your requirements



    }

    public Weapon getRandomOneHandedWeapon(List<String> randomOneHandedWeapon) {
        // Get all one-handed weapons of the specified types
        List<Weapon> oneHandedWeaponsOfType = randomOneHandedWeapon.stream()
                .flatMap(type -> weapons.getOrDefault(type, Collections.emptyList()).stream())
                .filter(weapon -> !weapon.isTwoHanded())
                .collect(Collectors.toList());

        // Return a random one-handed weapon from the filtered list
        if (!oneHandedWeaponsOfType.isEmpty()) {
            int randomIndex = new Random().nextInt(oneHandedWeaponsOfType.size());
            return oneHandedWeaponsOfType.get(randomIndex);
        }

        return null;
    }

    public List<Armor> getAllArmorForRestriction(Class<?> armorForRestriction) {
        List<Armor> armorForClass = new ArrayList<>();

        // Iterate over each armor type
        for (List<Armor> armorList : armorPieces.values()) {
            // Filter armor pieces based on the specified class
            List<Armor> armorOfTypeForClass = armorList.stream()
                    .filter(armor -> armor.getClassRestrictions().equals(armorForRestriction))
                    .collect(Collectors.toList());

            // Add the filtered armor pieces to the result list
            armorForClass.addAll(armorOfTypeForClass);
        }

        return armorForClass;
    }

    public Armor getRandomArmorOfType(String armor, Class<?> ofType) {
        // Get the list of armor pieces of the specified type
        List<Armor> armorOfType = armorPieces.getOrDefault(armor, Collections.emptyList());

        // Filter armor pieces based on the specified class restriction
        List<Armor> filteredArmor = armorOfType.stream()
                .filter(a -> a.getClassRestrictions().equals(ofType))
                .collect(Collectors.toList());

        // Return a random armor piece from the filtered list
        if (!filteredArmor.isEmpty()) {
            int randomIndex = new Random().nextInt(filteredArmor.size());
            return filteredArmor.get(randomIndex);
        }

        return null;

    }

}
