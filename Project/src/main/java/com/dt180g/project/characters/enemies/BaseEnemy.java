package com.dt180g.project.characters.enemies;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.support.ActivityLogger;
import java.util.List;

/**
 * The abstract class BaseEnemy representing all base enemy characters for the game.
 * The class extends the BaseCharacter class and provides functionality for the enemies.
 * @author Andreas Backman
 */
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
        getEquipment().addWeapon(GearManager.INSTANCE.getRandomWeapon(equip));
        if (getEquipment().amountOfEmptyWeaponSlots() == 1) {
            getEquipment().addWeapon(GearManager.INSTANCE.getRandomOneHandedWeapon(equip));
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
