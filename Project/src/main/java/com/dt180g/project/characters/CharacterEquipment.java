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
        String header = String.format("%s%s%n", AppConfig.ANSI_BLUE, "STATISTICS", AppConfig.ANSI_RESET);
        String breakLine = String.format("%s%s", AppConfig.ANSI_WHITE, " | ");

        if (weapons != null && !weapons.isEmpty()) {
            for (Weapon weapon : weapons) {
                List<String> weaponsList = new ArrayList<>();
//                weaponsList.add(String.format("%s%s%10s|%s", AppConfig.ANSI_WHITE, "[" + weapon.getType().toUpperCase() + "]", AppConfig.ANSI_WHITE, AppConfig.ANSI_RESET));
//
//                weaponsList.add(String.format("%s%s%s%s", AppConfig.ANSI_MAGENTA,  weapon.getWield(), AppConfig.ANSI_WHITE, AppConfig.ANSI_RESET));
//
//                weaponsList.add(String.format("%s%s%s%s%s%s", AppConfig.ANSI_RED, "Damage ", AppConfig.ANSI_GREEN, "+" + weapon.getDamage(), AppConfig.ANSI_WHITE, AppConfig.ANSI_RESET));
//
//                weaponsList.add(String.format("%s%s%s%s%s", AppConfig.ANSI_CYAN, weapon + " ", AppConfig.ANSI_YELLOW, "+" + weapon.getStat().getBaseValue(), AppConfig.ANSI_RESET));

                weaponsList.add(String.format("%s[%s]", AppConfig.ANSI_WHITE, weapon.getType()));
                weaponsList.add(breakLine);
                weaponsList.add(String.format("%s %s", AppConfig.ANSI_PURPLE, weapon.getWield()));
                weaponsList.add(breakLine);
                weaponsList.add(String.format("%s Damage %14s+%s", AppConfig.ANSI_RED, AppConfig.ANSI_GREEN, weapon.getDamage()));
                weaponsList.add(breakLine);
                weaponsList.add(String.format("%s %s", AppConfig.ANSI_CYAN, weapon));
                weaponsList.add(String.format("%s %3s%s", AppConfig.ANSI_YELLOW, "+" + weapon.getStat().getModifiedValue(), AppConfig.ANSI_RESET));

                formatList.add(weaponsList);


            }
        }


        if (armorPieces != null && !armorPieces.isEmpty()) {
            for (Armor armor : armorPieces.values()) {
                List<String> armorList = new ArrayList<>();


//                armorList.add(String.format("%s%s|%s", AppConfig.ANSI_WHITE, "[" + armor.getType().toUpperCase() + "]", AppConfig.ANSI_RESET));
//
//                armorList.add(String.format("%s%s%s|%s", AppConfig.ANSI_MAGENTA,  armor.getMaterial(), AppConfig.ANSI_WHITE, AppConfig.ANSI_RESET));
//
//                armorList.add(String.format("%s%s%s%s%s%s|", AppConfig.ANSI_RED, "Protection ", AppConfig.ANSI_GREEN, "+" + armor.getProtection(), AppConfig.ANSI_WHITE, AppConfig.ANSI_RESET));
//
//                armorList.add(String.format("%s%s%s%s%s", AppConfig.ANSI_CYAN, armor.toString() + " ", AppConfig.ANSI_YELLOW, "+" + armor.getStat().getBaseValue(), AppConfig.ANSI_RESET));

                armorList.add(String.format("%s[%s]", AppConfig.ANSI_WHITE, armor.getType()));
                armorList.add(breakLine);
                armorList.add(String.format("%s %s", AppConfig.ANSI_PURPLE, armor.getMaterial()));
                armorList.add(breakLine);
                armorList.add(String.format("%s Protection %10s+%s",AppConfig.ANSI_RED, AppConfig.ANSI_GREEN, armor.getProtection()));
                armorList.add(breakLine);
                armorList.add(String.format("%s %s",AppConfig.ANSI_CYAN, armor));
                armorList.add(String.format("%s %3s%s", AppConfig.ANSI_YELLOW,  armor.getStat().getModifiedValue(),AppConfig.ANSI_RESET));
//
                formatList.add(armorList);
            }
        }


        return header + IOHelper.formatAsTable(formatList);

//        List<List<String>> allGear = new ArrayList<>();
//        String separate = (String.format("%s%s", AppConfig.ANSI_WHITE, " | "));
//
//        for (Weapon weapon : weapons) {
//            List<String> gear = new ArrayList<>();
//            gear.add(String.format("%s[%s]", AppConfig.ANSI_WHITE, weapon.getType()));
//            gear.add(separate);
//            gear.add(String.format("%s %s", AppConfig.ANSI_PURPLE, weapon.getWield()));
//            gear.add(separate);
//            gear.add(String.format("%s Damage %14s+%s",AppConfig.ANSI_RED, AppConfig.ANSI_GREEN, weapon.getDamage()));
//            gear.add(separate);
//            gear.add(String.format("%s %s",AppConfig.ANSI_CYAN, weapon));
//            gear.add(String.format("%s +%s%s", AppConfig.ANSI_YELLOW, weapon.getStat().getModifiedValue(),AppConfig.ANSI_RESET));
//            allGear.add(gear);
//        }
//
//        for (Map.Entry<String, Armor> entry : armorPieces.entrySet()) {
//            List<String> gear = new ArrayList<>();
//            Armor armor = entry.getValue();
//            gear.add(String.format("%s[%s]", AppConfig.ANSI_WHITE, armor.getType()));
//            gear.add(separate);
//            gear.add(String.format("%s %s", AppConfig.ANSI_PURPLE, armor.getMaterial()));
//            gear.add(separate);
//            gear.add(String.format("%s Protection %10s+%s",AppConfig.ANSI_RED, AppConfig.ANSI_GREEN, armor.getProtection()));
//            gear.add(separate);
//            gear.add(String.format("%s %s",AppConfig.ANSI_CYAN, armor));
//            gear.add(String.format("%s +%s%s", AppConfig.ANSI_YELLOW, armor.getStat().getModifiedValue(),AppConfig.ANSI_RESET));
//            allGear.add(gear);
//        }
//
//        String header = String.format("%s%s", AppConfig.ANSI_BLUE, "EQUIPMENT \n");
//
//        return header + IOHelper.formatAsTable(allGear);
//    }
//
//}

    }
}
