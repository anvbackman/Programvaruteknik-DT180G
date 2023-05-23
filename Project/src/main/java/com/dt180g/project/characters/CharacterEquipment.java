package com.dt180g.project.characters;

import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.BaseGear;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;

import java.util.*;
public class CharacterEquipment {

    private List<Weapon> weapons;
    private Map<String, Armor> armorPieces = new HashMap<>();


    public List<Weapon> getWeapons() {
        return weapons;
    }

    public List<Armor> getArmorPieces() {
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
        int maxWeaponSlots = 2;
        int usedWeaponSlots = 0;
        if (weapons != null) {
            usedWeaponSlots = weapons.size();
        }
        return maxWeaponSlots - usedWeaponSlots;
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
        // Check if there are empty weapon slots
        if (amountOfEmptyWeaponSlots() > 0) {
            weapons.add(weapon);
            return false; // Weapon added successfully
        }
        return true; // No empty weapon slots available
    }

    public boolean addArmorPiece(String armorKey, Armor armor) {
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
        StringBuilder stringBuilder = new StringBuilder();

        if (weapons != null && !weapons.isEmpty()) {
            for (Weapon weapon : weapons) {
                stringBuilder.append("[WEAPON TYPE] | " + weapon.getType()).append("WEILD | " + weapon.getWield()).append("DAMAGE " + weapon.getDamage() + " | ")
                        .append("WEAPON name").append(" of " + weapon.getStat());
            }
        }

        for (Map.Entry<String, Armor> entry : armorPieces.entrySet()) {
            String armorKey = entry.getKey();
            Armor armor = entry.getValue();
            stringBuilder.append("[BODY PART] | " + armor.getType()).append("MATERIAL | ").append("PROTECTION " + armor.getProtection() + " | ")
                    .append("ARMOR name").append(" of " + armor.getStat());

            // eller List<Armor> armorPieces = getArmorPieces();
            //    if (armorPieces != null && !armorPieces.isEmpty()) {
            //        sb.append("Armor Pieces:\n");
            //        for (Armor armor : armorPieces) {
            //            sb.append("[").append(armor.getBodyPart()).append("] | MATERIAL | PROTECTION + ").append(armor.getProtection())
            //                    .append(" | ").append(armor.getGearName()).append("\n");
            //        }
            //    }

//        List<Armor> armorPieces = getArmorPieces();
//        if (armorPieces != null && !armorPieces.isEmpty()) {
//            stringBuilder.append("Armor Pieces:\n");
//            for (Armor armor : armorPieces) {
//                stringBuilder.append("[").append(armor.getType()).append("] | MATERIAL | PROTECTION + ").append(armor.getProtection())
//                        .append(" | ");
//            }
//        }


        }
        return stringBuilder.toString();
    }
}
