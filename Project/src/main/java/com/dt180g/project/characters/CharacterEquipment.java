package com.dt180g.project.characters;

import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;
import java.util.*;

/**
 * The class CharacterEquipment represents the character equipment.
 * The class stores weapons and armor pieces, calculating damage and armor protection and uses methods to
 * manage the character equipment.
 * @author Andreas Backman
 */
public class CharacterEquipment {

    // Variables for weapons and armor
    private List<Weapon> weapons;
    private Map<String, Armor> armorPieces;

    /**
     * Method for returning the list of weapons, by first creating an empty ArrayList.
     *
     * @return the weapons list
     */
    public List<Weapon> getWeapons() {

        if (weapons == null) {
            weapons = new ArrayList<>();
        }

        return weapons;
    }

    /**
     * Method for returning the list of armor, by first creating an empty HashMap.
     *
     * @return the armor list
     */
    public List<Armor> getArmorPieces() {

        if (armorPieces == null) {
            armorPieces = new HashMap<>();
        }

        return new ArrayList<>(armorPieces.values());
    }

    /**
     * Method for getting the total weapon damage for the equipped weapons.
     *
     * @return the total weapon damage for the equipped weapons
     */
    public int getTotalWeaponDamage() {

        int totalDamage = 0;

        if (weapons != null) {
            for (Weapon weapon : weapons) {
                totalDamage += weapon.getDamage();
            }
        }

        return totalDamage;
    }

    /**
     * Method for getting the total armor protection for the equipped armor.
     *
     * @return the total armor protection for the equipped armor
     */
    public int getTotalArmorProtection() {

        int totalArmorProtection = 0;

        if (armorPieces != null) {
            for (Armor armor : armorPieces.values()) {
                totalArmorProtection += armor.getProtection();
            }
        }

        return totalArmorProtection;
    }

    /**
     * Method for calculating the amount of empty weapon slots by subtracting the used amount from the max amount of 2.
     *
     * @return the amount of empty weapon slots
     */
    public int amountOfEmptyWeaponSlots() {

        if (weapons == null) {
            weapons = new ArrayList<>();
        }

        int usedWeaponSlots = 0;

        for (Weapon weaponWield : weapons) {
            if (weaponWield.isTwoHanded()) {
                usedWeaponSlots += 2;
            }
            else {
                usedWeaponSlots += 1;
            }
        }

        return Math.max(0, 2 - usedWeaponSlots);
    }

    /**
     * Method for calculating the amount of empty armor slots by subtracting the used amount from the max amount of 5.
     *
     * @return the amount of empty weapon slots
     */
    public int amountOfEmptyArmorSlots() {

        int maxArmorSlots = 5;
        int usedArmorSlots = armorPieces.size();

        return maxArmorSlots - usedArmorSlots;
    }

    /**
     * Method for adding a weapon to the character.
     * It does this by checking if the amount of empty weapon slots allow for a weapon to be added.
     * A weapon cannot be added if there is not enough slots left for the weapon type or if both slots are taken.
     *
     * @param weapon the weapon being added
     * @return true if the weapon is added, otherwise false
     */
    public boolean addWeapon(Weapon weapon) {

        if (weapons == null) {
            weapons = new ArrayList<>();
        }

        if (weapon.isTwoHanded()) {
            if (weapons.size() >= 1) {
                return false;
            }
        }

        else {
            if (weapons.size() >= 2 || weapons.size() == 1 && weapons.get(0).isTwoHanded()) {
                return false;
            }
        }

        weapons.add(weapon);
        return true;
    }

    /**
     * Method for adding an armor piece to the character.
     * It does this by checking that the type of armor has not been added yet and that the slots are not full.
     * The method then adds the type and armor piece to armorPieces.
     *
     * @param armorKey the key in armorPieces
     * @param armor the armor to be added
     * @return true if the armor is added, otherwise false
     */
    public boolean addArmorPiece(String armorKey, Armor armor) {

        if (armorPieces == null) {
            armorPieces = new HashMap<>();
        }

        if (armorPieces.containsKey(armorKey)) {
            return false;
        }

        if (armorPieces.size() >= 5) {
            return false;
        }

        armorPieces.put(armorKey, armor);
        return true;
    }

    /**
     * Method for returning a formatted String of the characters weapons and armor pieces
     * with their respective information such as type and stats.
     *
     * @return the header and the weapons and armorPieces lists formatted through IOHelper
     */
    @Override
    public String toString() {

        List<List<String>> formatList = new ArrayList<>();
        String header = String.format("%s%s%n", AppConfig.ANSI_BLUE, "EQUIPMENT");
        String breakLine = String.format("%s%s", AppConfig.ANSI_WHITE, " | ");

        if (weapons != null && !weapons.isEmpty()) {
            for (Weapon weapon : weapons) {
                List<String> weaponsList = new ArrayList<>();

                weaponsList.add(String.format("%s[%s]", AppConfig.ANSI_WHITE, weapon.getType()));
                weaponsList.add(breakLine);
                weaponsList.add(String.format("%s %s", AppConfig.ANSI_PURPLE, weapon.getWield()));
                weaponsList.add(breakLine);
                weaponsList.add(String.format("%s Damage %14s%3s", AppConfig.ANSI_RED, AppConfig.ANSI_GREEN, "+" + weapon.getDamage()));
                weaponsList.add(breakLine);
                weaponsList.add(String.format("%s %s", AppConfig.ANSI_CYAN, weapon));
                weaponsList.add(String.format("%s %3s%s", AppConfig.ANSI_YELLOW, "+" + weapon.getStat().getModifiedValue(), AppConfig.ANSI_RESET));

                formatList.add(weaponsList);
            }
        }

        if (armorPieces != null && !armorPieces.isEmpty()) {
            for (Armor armor : armorPieces.values()) {
                List<String> armorList = new ArrayList<>();

                armorList.add(String.format("%s[%s]", AppConfig.ANSI_WHITE, armor.getType()));
                armorList.add(breakLine);
                armorList.add(String.format("%s %s", AppConfig.ANSI_PURPLE, armor.getMaterial()));
                armorList.add(breakLine);
                armorList.add(String.format("%s Protection %10s%3s", AppConfig.ANSI_RED, AppConfig.ANSI_GREEN, "+" + armor.getProtection()));
                armorList.add(breakLine);
                armorList.add(String.format("%s %s", AppConfig.ANSI_CYAN, armor));
                armorList.add(String.format("%s %3s%s", AppConfig.ANSI_YELLOW, "+" + armor.getStat().getModifiedValue(), AppConfig.ANSI_RESET));

                formatList.add(armorList);
            }
        }

        return header + IOHelper.formatAsTable(formatList);
    }
}
