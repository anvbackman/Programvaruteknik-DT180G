package com.dt180g.project.characters.heroes;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.support.ActivityLogger;
import java.util.List;

/**
 * The abstract class BaseHero representing all base hero characters for the game.
 * The class extends the BaseCharacter class and provides functionality for the heroes.
 * @author Andreas Backman
 */
public abstract class BaseHero extends BaseCharacter {

    // Variable for holding the characters name
    private String characterName;

    /**
     * Constructor for creating a BaseHero object with parameters for the characters name and attribute values.
     *
     * @param characterName the name of the character
     * @param attributeValue the attribute values
     */
    protected BaseHero(String characterName, List<Integer> attributeValue) {
        super(new CharacterStats(attributeValue));
        this.characterName = characterName;
    }

    /**
     * Method for equipping the hero with weapons and armor of the heroes class.
     *
     * @param equip the class.
     */
    protected void equipHero(Class<?> equip) {

        getEquipment().addWeapon(GearManager.INSTANCE.getRandomWeapon(equip));
        if (getEquipment().amountOfEmptyWeaponSlots() == 1) {
            getEquipment().addWeapon(GearManager.INSTANCE.getRandomOneHandedWeapon(equip));
        }

        for (String armorType : GearManager.INSTANCE.getAllMappedArmorPieces().keySet()) {
            getEquipment().addArmorPiece(armorType, GearManager.INSTANCE.getRandomArmorOfType(armorType, equip));
        }
    }

    /**
     * Method for resetting the heroes stats
     */
    public void resetHeroStats() {
        CharacterStats characterStats = getCharacterStats();

        characterStats.resetActionPoints();
        characterStats.resetHitPoints();
        characterStats.resetEnergyLevel();
    }

    /**
     * Getting the name of the hero character.
     *
     * @return the characters name.
     */
    @Override
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Does the turn for the hero, logging the turn information and executes the actions if said character is alive.
     */
    @Override
    public void doTurn() {
        ActivityLogger.INSTANCE.logTurnInfo(getTurnInformation(getCharacterName()));
        if (!isDead()) {
            executeActions(true);
        }
    }
}
