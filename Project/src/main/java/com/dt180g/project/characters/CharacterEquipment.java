package com.dt180g.project.characters;

import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.BaseGear;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;

import java.util.*;
public class CharacterEquipment {

    private List<Weapon> weapons;
    private Map<String, Armor> armorPieces;


    public List<Weapon> getWeapons() {
        if (weapons == null) {
            weapons = new ArrayList<>();
        }

        return weapons;
    }

    public List<Armor> getArmorPieces() {

        if (armorPieces == null) {
            armorPieces = new HashMap<>();
        }




        return new ArrayList<>(armorPieces.values());

    }

    public int getTotalWeaponDamage() {
        int totalDamage = 0;
        if (weapons != null) {
            for (Weapon weapon : weapons) {
                totalDamage += weapon.getDamage();
            }
        }
        return totalDamage;
    }

    public int getTotalArmorProtection() {
        int totalArmorProtection = 0;
        if (armorPieces != null) {
            for (Armor armor : armorPieces.values()) {
                totalArmorProtection += armor.getProtection();
            }
        }
        return totalArmorProtection;
    }

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

    public int amountOfEmptyArmorSlots() {
        // Kan behöva checka null
        // int maxArmorSlots = 5;
        //        int usedArmorSlots = 0;
        //        if (armorPieces != null) {
        //            usedArmorSlots = armorPieces.size();
        //        }
        //        return maxArmorSlots - usedArmorSlots;
        int maxArmorSlots = 5;
        int usedArmorSlots = armorPieces.size();
        return maxArmorSlots - usedArmorSlots;
    }
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

    public boolean addArmorPiece(String armorKey, Armor armor) {
        if (armorPieces == null) {
            armorPieces = new HashMap<>();
        }

        if (armorPieces.containsKey(armorKey)) {
            return false; // Armor slot is already occupied
        }

        if (armorPieces.size() >= 5) {
            return false;
        }
        armorPieces.put(armorKey, armor);
        return true; // Armor piece added successfully
    }

    public String toString() {


        List<List<String>> formatList = new ArrayList<>();
        String header = String.format("%s%s%n", AppConfig.ANSI_BLUE, "STATISTICS");

        if (weapons != null) {
            for (Weapon weapon : weapons) {
                List<String> weaponsList = new ArrayList<>();
                weaponsList.add(weapon.getType());
                weaponsList.add(weapon.getWield());
                weaponsList.add("Damage" + weapon.getDamage());
                weaponsList.add("name" + " of " + weapon.getStat());

                formatList.add(weaponsList);
            }
        }

        if (armorPieces != null) {
            for (Armor armor : armorPieces.values()) {
                List<String> armorList = new ArrayList<>();
                armorList.add(armor.getType());
                armorList.add(armor.getMaterial());
                armorList.add("Protection" + armor.getProtection());
                armorList.add("name" + " of " + armor.getStat());

                formatList.add(armorList);
            }
        }

        return header + IOHelper.formatAsTable(formatList);





//        List<List<String>> formatList = new ArrayList<>();
//
//
//
//        List<String> formatListSection1 = new ArrayList<>();
//        List<String> formatListSection2 = new ArrayList<>();
//        List<String> formatListSection3 = new ArrayList<>();
//        List<String> formatListSection4 = new ArrayList<>();
//
//        String header = String.format("%s%s%n", AppConfig.ANSI_BLUE, "STATISTICS");
//
////        formatList.add(formatListHeader);
//
//        String formatString = "%s%-15s%s%3s%s%3s%-7s|";
//
//
//        formatListSection1.add(String.format(formatString, AppConfig.ANSI_WHITE,  AppConfig.ANSI_CYAN, getWeapons()));
//        formatListSection1.add(String.format(formatString, AppConfig.ANSI_GREEN, AppConfig.TRAIT_VITALITY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_VITALITY), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.TRAIT_VITALITY).getTotalModifier(), AppConfig.ANSI_WHITE));
//        formatListSection1.add(String.format(formatString, AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_ACTION_POINTS, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_ACTION_POINTS), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).getTotalModifier(), AppConfig.ANSI_WHITE));
////        formatListSection1.add(separator);
//        formatList.add(formatListSection1);
//
//
//        formatListSection2.add(String.format(formatString, AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_DEXTERITY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_DEXTERITY), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.ATTRIBUTE_DEXTERITY).getTotalModifier(), AppConfig.ANSI_WHITE));
//        formatListSection2.add(String.format(formatString, AppConfig.ANSI_GREEN, AppConfig.TRAIT_ENERGY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_ENERGY), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.TRAIT_ENERGY).getTotalModifier(), AppConfig.ANSI_WHITE));
//        formatListSection2.add(String.format(formatString, AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_PHYSICAL_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_PHYSICAL_POWER), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.COMBAT_STAT_PHYSICAL_POWER).getTotalModifier(), AppConfig.ANSI_WHITE));
////        formatListSection2.add(separator);
//        formatList.add(formatListSection2);
//
//
//        formatListSection3.add(String.format(formatString, AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_INTELLIGENCE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_INTELLIGENCE), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.ATTRIBUTE_INTELLIGENCE).getTotalModifier(), AppConfig.ANSI_WHITE));
//        formatListSection3.add(String.format(formatString, AppConfig.ANSI_GREEN, AppConfig.TRAIT_ATTACK_RATE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_ATTACK_RATE), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.TRAIT_ATTACK_RATE).getTotalModifier(), AppConfig.ANSI_WHITE));
//        formatListSection3.add(String.format(formatString, AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_MAGIC_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_MAGIC_POWER), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.COMBAT_STAT_MAGIC_POWER).getTotalModifier(), AppConfig.ANSI_WHITE));
////        formatListSection3.add(separator);
//        formatList.add(formatListSection3);
//
//
//        formatListSection4.add(String.format(formatString, AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_WILLPOWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_WILLPOWER), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.ATTRIBUTE_WILLPOWER).getTotalModifier(), AppConfig.ANSI_WHITE));
//        formatListSection4.add(String.format(formatString, AppConfig.ANSI_GREEN, AppConfig.TRAIT_DEFENSE_RATE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_DEFENSE_RATE), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.TRAIT_DEFENSE_RATE).getTotalModifier(), AppConfig.ANSI_WHITE));
//        formatListSection4.add(String.format(formatString + "\n", AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_HEALING_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_HEALING_POWER), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.COMBAT_STAT_HEALING_POWER).getTotalModifier(), AppConfig.ANSI_WHITE));
////        formatListSection4.add(String.format(separator + "\n"));
//        formatList.add(formatListSection4);





//        return header + IOHelper.formatAsTable(formatList);








    }
}
