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
        int totalWeaponDamage = 0;
        if (weapons != null) {
            totalWeaponDamage += weapons.getDamage();
        }
        return totalWeaponDamage;
    }

    public int getTotalArmorProtection() {
        int totalArmorProtection = 0;
        for (Armor armor : armorPieces.values()) { // Kan kanske göras lika som getTotalWeaponDamage?
            totalArmorProtection += armorPieces.getProtection();
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
            return true; // Weapon added successfully
        }
        return false; // No empty weapon slots available
    }

    public boolean addArmorPiece(String armorKey, Armor armor) {
        if (armorPieces.containsKey(armorKey)) {
            return false; // Armor slot is already occupied
        }
        armorPieces.put(armorKey, armor);
        return true; // Armor piece added successfully
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (weapons != null && !weapons.isEmpty()) {
            for (Weapon weapon : weapons) {
                stringBuilder.append("[WEAPON TYPE] | " + BaseGear.getType()).append("WEILD | " + Weapon.getWield()).append("DAMAGE " + getTotalWeaponDamage() + " | ")
                        .append("WEAPON name").append(" of " + BaseGear.getStat()).append();
            }
        }

        for (Map.Entry<String, Armor> entry : armorPieces.entrySet()) {
            String armorKey = entry.getKey();
            Armor armor = entry.getValue();
            stringBuilder.append("[BODY PART] | " + BaseGear.getType()).append("MATERIAL | ").append("PROTECTION " + getTotalArmorProtection() + " | ")
                    .append("ARMOR name").append(" of " + BaseGear.getStat()).append();
        }
        return stringBuilder.toString();
    }
}
