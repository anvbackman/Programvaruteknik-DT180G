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


//        StringBuilder stringBuilder = new StringBuilder();
//
//        if (weapons != null && !weapons.isEmpty()) {
//            for (Weapon weapon : weapons) {
//                stringBuilder.append("[WEAPON TYPE] | ").append(weapon.getType())
//                        .append(" | [WEAPON NAME] | ").append("name")
//                        .append(" | [DAMAGE] | ").append(weapon.getDamage())
//                        .append(" | [STAT] | ").append(weapon.getStat())
//                        .append(" | [WIELD] | ").append(weapon.getWield())
//                        .append("\n");
//            }
//        }
//
//        List<Armor> armorPieces = getArmorPieces();
//        if (armorPieces != null && !armorPieces.isEmpty()) {
//            for (Armor armor : armorPieces) {
//                stringBuilder.append("[BODY PART] | ").append(armor.getType())
//                        .append(" | [ARMOR NAME] | ").append("name")
//                        .append(" | [PROTECTION] | ").append(armor.getProtection())
//                        .append(" | [STAT] | ").append(armor.getStat())
//                        .append("\n");
//            }
//        }
//
//        return stringBuilder.toString();


        String header = String.format("%s%s%n", AppConfig.ANSI_BLUE, "EQUIPMENT");

//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_WHITE, AppConfig.ATTRIBUTE_STRENGTH, AppConfig.ANSI_CYAN, getWeapons()));
//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.TRAIT_VITALITY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_VITALITY), AppConfig.ANSI_YELLOW, getStat(AppConfig.TRAIT_VITALITY).getTotalModifier(), AppConfig.ANSI_RESET));
//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_ACTION_POINTS, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_ACTION_POINTS), AppConfig.ANSI_YELLOW, getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).getTotalModifier(), AppConfig.ANSI_RESET));
//        sb.append(String.format("%s%n", AppConfig.ANSI_WHITE + " | " + AppConfig.ANSI_RESET));

//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_DEXTERITY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_DEXTERITY), AppConfig.ANSI_YELLOW, getStat(AppConfig.ATTRIBUTE_DEXTERITY).getTotalModifier(), AppConfig.ANSI_RESET));
//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.TRAIT_ENERGY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_ENERGY), AppConfig.ANSI_YELLOW, getStat(AppConfig.TRAIT_ENERGY).getTotalModifier(), AppConfig.ANSI_RESET));
//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_PHYSICAL_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_PHYSICAL_POWER), AppConfig.ANSI_YELLOW, getStat(AppConfig.COMBAT_STAT_PHYSICAL_POWER).getTotalModifier(), AppConfig.ANSI_RESET));
//        sb.append(String.format("%s%n", AppConfig.ANSI_WHITE + " | " + AppConfig.ANSI_RESET));
//
//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_INTELLIGENCE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_INTELLIGENCE), AppConfig.ANSI_YELLOW, getStat(AppConfig.ATTRIBUTE_INTELLIGENCE).getTotalModifier(), AppConfig.ANSI_RESET));
//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.TRAIT_ATTACK_RATE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_ATTACK_RATE), AppConfig.ANSI_YELLOW, getStat(AppConfig.TRAIT_ATTACK_RATE).getTotalModifier(), AppConfig.ANSI_RESET));
//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_MAGIC_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_MAGIC_POWER), AppConfig.ANSI_YELLOW, getStat(AppConfig.COMBAT_STAT_MAGIC_POWER).getTotalModifier(), AppConfig.ANSI_RESET));
//        sb.append(String.format("%s%n", AppConfig.ANSI_WHITE + " | " + AppConfig.ANSI_RESET));
//
//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_WILLPOWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_WILLPOWER), AppConfig.ANSI_YELLOW, getStat(AppConfig.ATTRIBUTE_WILLPOWER).getTotalModifier(), AppConfig.ANSI_RESET));
//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.TRAIT_DEFENSE_RATE, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_DEFENSE_RATE), AppConfig.ANSI_YELLOW, getStat(AppConfig.TRAIT_DEFENSE_RATE).getTotalModifier(), AppConfig.ANSI_RESET));
//        sb.append(String.format("%s%-25s%s%-6s%s+%s%s%n", AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_HEALING_POWER, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_HEALING_POWER), AppConfig.ANSI_YELLOW, getStat(AppConfig.COMBAT_STAT_HEALING_POWER).getTotalModifier(), AppConfig.ANSI_RESET));
//        sb.append(String.format("%s%n", AppConfig.ANSI_WHITE + " | " + AppConfig.ANSI_RESET));

        return header;




    }
}
