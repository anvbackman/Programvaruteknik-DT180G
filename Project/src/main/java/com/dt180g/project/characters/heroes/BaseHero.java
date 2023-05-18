package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterEquipment;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;

import java.util.List;

public abstract class BaseHero extends BaseCharacter {

    private String characterName;

    protected BaseHero(String characterName, List<Integer> attributeValue) {
        super(new CharacterStats(attributeValue));
        this.characterName = characterName;
    }

    protected equipHero(Class<?> equip) {
        GearManager gearManager = GearManager.getInstance();
        CharacterEquipment characterEquipment = new CharacterEquipment();

        if (equip.equals(Weapon.class)) {

            Weapon twoHandedWeapon = gearManager.getRandomWeapon();
            Weapon oneHandedWeapon = gearManager.getRandomOneHandedWeapon();
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

        if (equip.equals(Armor.class)) {

            Armor chest = gearManager.getRandomArmorOfType();
            Armor hands = gearManager.getRandomArmorOfType();
            Armor head = gearManager.getRandomArmorOfType();
            Armor feet = gearManager.getRandomArmorOfType();
            Armor legs = gearManager.getRandomArmorOfType();

            characterEquipment.addArmorPiece(chest);
            characterEquipment.addArmorPiece(hands);
            characterEquipment.addArmorPiece(head);
            characterEquipment.addArmorPiece(feet);
            characterEquipment.addArmorPiece(legs);
        }


    }

    public void resetHeroStats() {
        CharacterStats characterStats = getCharacterStats();

        characterStats.resetActionPoints();
        characterStats.resetHitPoints();
        characterStats.resetEnergyLevel();

    }

    public String getCharacterName() {
        return characterName;
    }

    public void doTurn() {


    }
}
