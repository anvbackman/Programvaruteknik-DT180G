package com.dt180g.project.characters.enemies;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterEquipment;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.ActivityLogger;

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


            if (twoHandedWeapon != null) {
                characterEquipment.addWeapon(twoHandedWeapon);
            } else if (oneHandedWeapon != null) {
                characterEquipment.addWeapon(oneHandedWeapon);
                characterEquipment.addWeapon(oneHandedWeapon);

            }


        }
    }



    public String getCharacterName() {
        return characterName;
    }

    public void doTurn() {
        ActivityLogger.INSTANCE.logTurnInfo(getTurnInformation(getCharacterName()));
        if (!isDead()) {
            executeActions(false);
        }



    }
}
