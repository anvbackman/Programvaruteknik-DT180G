package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;

import java.util.List;

public abstract class BaseHero extends BaseCharacter {

    private String characterName;

    protected BaseHero(String characterName, List<Integer> attributeValue) {
        super(attributeValue);
        this.characterName = characterName;
        equipHero();

    }

    protected void equipHero(Class<?> equip) {

        Weapon weapon1;
        Weapon weapon2;

        if (nånting.equals("Two Handed")) {
            weapon1 = GearManager.getInstance().getRandomWeapon();
            weapon2 = null;
        }
        else {
            weapon1 = GearManager.getInstance().getRandomOneHandedWeapon();
            weapon2 = GearManager.getInstance().getRandomOneHandedWeapon();
        }

        Armor head = GearManager.getInstance().getRandomArmorOfType(Armor.HEAD);
        Armor chest = GearManager.getInstance().getRandomArmorOfType(Armor.CHEST);
        Armor Hands = GearManager.getInstance().getRandomArmorOfType(Armor.HANDS);
        Armor legs = GearManager.getInstance().getRandomArmorOfType(Armor.LEGS);
        Armor feet  = GearManager.getInstance().getRandomArmorOfType(Armor.FEET);

        setArmor

    }

    public void resetHeroStats() {
        super.roundReset();

    }

    public String getCharacterName() {
        return " The ".split(characterName).toString();
    }

    public void doTurn() {

        performHeroAction();
    }
}
