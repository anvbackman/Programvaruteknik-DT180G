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

    // Variable for holding the characters name
    private String characterName;

    /**
     * Constructor for creating a BaseEnemy object with parameters for the characters name and attribute values.
     *
     * @param characterName the name of the character
     * @param attributeValue the attribute values
     */
    protected BaseEnemy(String characterName, List<Integer> attributeValue) {
        super(new CharacterStats(attributeValue));
        this.characterName = characterName;
    }

    /**
     * Method for equipping the enemy with weapons of the enemy class.
     * @param equip the class.
     */
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

    /**
     * Getting the name of the enemy character.
     * @return the characters name.
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Does the turn for the enemy, logging the turn information and executes the actions if said character is alive.
     */
    public void doTurn() {
        ActivityLogger.INSTANCE.logTurnInfo(getTurnInformation(getCharacterName()));
        if (!isDead()) {
            executeActions(false);
        }
    }
}
