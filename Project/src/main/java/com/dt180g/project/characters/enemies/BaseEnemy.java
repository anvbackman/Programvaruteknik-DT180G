package com.dt180g.project.characters.enemies;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterEquipment;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.gear.Weapon;

import java.util.List;

public class BaseEnemy extends BaseCharacter {


    private String characterName;

    protected BaseEnemy(String characterName, List<Integer> attributeValue) {
        super(new CharacterStats(attributeValue));
        this.characterName = characterName;
    }

    protected void equipEnemy(List<String> equip) {
        GearManager gearManager = GearManager.getInstance();
        CharacterEquipment characterEquipment = new CharacterEquipment();

        if (equip.equals(Weapon.class)) {

            Weapon twoHandedWeapon = gearManager.getRandomWeapon(equip);
            Weapon oneHandedWeapon = gearManager.getRandomOneHandedWeapon(equip);
            Weapon weapon1;
            Weapon weapon2;

            if (equip.equals(twoHandedWeapon)) {
                weapon1 = twoHandedWeapon;
                weapon2 = null;
                characterEquipment.addWeapon(weapon1);
                characterEquipment.addWeapon(weapon2);
            }
            else if (equip.equals(oneHandedWeapon)){
                weapon1 = oneHandedWeapon;
                weapon2 = oneHandedWeapon;
                characterEquipment.addWeapon(weapon1);
                characterEquipment.addWeapon(weapon2);
            }



        }



    }



    public String getCharacterName() {
        return characterName;
    }

    public void doTurn() {



    }
}
