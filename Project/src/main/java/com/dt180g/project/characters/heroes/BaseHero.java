package com.dt180g.project.characters.heroes;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.support.ActivityLogger;
import java.util.List;

public abstract class BaseHero extends BaseCharacter {

    private String characterName;

    protected BaseHero(String characterName, List<Integer> attributeValue) {
        super(new CharacterStats(attributeValue));
        this.characterName = characterName;
    }

    protected void equipHero(Class<?> equip) {

        getEquipment().addWeapon(GearManager.INSTANCE.getRandomWeapon(equip));
        if (getEquipment().amountOfEmptyWeaponSlots() == 1) {
            getEquipment().addWeapon(GearManager.INSTANCE.getRandomOneHandedWeapon(equip));
        }

        for (String armorType : GearManager.INSTANCE.getAllMappedArmorPieces().keySet()) {
            getEquipment().addArmorPiece(armorType, GearManager.INSTANCE.getRandomArmorOfType(armorType, equip));
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
        ActivityLogger.INSTANCE.logTurnInfo(getTurnInformation(getCharacterName()));
        if (!isDead()) {
            executeActions(true);
        }
    }
}
