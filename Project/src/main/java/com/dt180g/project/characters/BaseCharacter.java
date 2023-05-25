package com.dt180g.project.characters;

import com.dt180g.project.GameEngine;
import com.dt180g.project.GameRunner;

import com.dt180g.project.abilities.AbilityInfo;
import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.characters.enemies.BaseEnemy;
import com.dt180g.project.characters.heroes.BaseHero;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseCharacter {

    private CharacterStats characterStats;
    private CharacterEquipment equipment;
    private List<BaseAbility> abilities;


    protected BaseCharacter(CharacterStats characterStats) {
        this.characterStats = characterStats;
        this.equipment = new CharacterEquipment();


    }

    protected void addAbilities(List<BaseAbility> abilities) {
        this.abilities = abilities;
    }

    protected String getTurnInformation(String turn) {
        String character = getCharacterName();
        int actionPoints = getActionPoints();
        int hitPoints = getHitPoints();
        int energyLevel = getEnergyLevel();

        String turnInfo = "[" + turn.toUpperCase() + "] " + character + " | " + actionPoints + " AP | " +
                hitPoints + " HP | " + energyLevel + " Energy";
        return turnInfo;
    }

    protected void executeAction(boolean execute) {

        Deque<BaseAbility> selectedAbilities = determineActions();
        int availableAP = getActionPoints();
        int availableEnergy = getEnergyLevel();

        for (BaseAbility ability : selectedAbilities) {
            int abilityAPCost = ability.getActionPointCost();
            int abilityEnergyCost = ability.getEnergyCost();

            // Check if the ability can be afforded
            if (abilityAPCost <= availableAP && abilityEnergyCost <= availableEnergy) {
                // Perform the specific logic for triggering the ability
                boolean abilityTriggered = ability.execute(abilityAPCost, execute);

                if (abilityTriggered) {
                    // Register cost reductions
                    availableAP -= abilityAPCost;
                    availableEnergy -= abilityEnergyCost;
                }

            }

        }
        // Update the character's action points and energy level
        characterStats.adjustActionPoints(availableAP);
        characterStats.adjustEnergyLevel(availableEnergy);

        List<BaseCharacter> targets;

        if (execute) {
            targets = GameEngine.INSTANCE.getAllCharacters().stream().filter(character -> character instanceof BaseEnemy).collect(Collectors.toList());
        }
        else {
            targets = GameEngine.INSTANCE.getAllCharacters().stream().filter(character -> character instanceof BaseHero).collect(Collectors.toList());
        }

        // Eller
//        if (execute) {
//            List<BaseEnemy> enemies = GameEngine.INSTANCE.getEnemies();
//            targets = new ArrayList<>(enemies.size());
//            targets.addAll(enemies);
//        }
//        else {
//            List<BaseHero> heroes = GameEngine.INSTANCE.getHeroes();
//            targets = new ArrayList<>(heroes.size());
//            targets.addAll(heroes);
//        }




        for (BaseCharacter target : targets) {
            if (!target.isDead()) {
                int damage = equipment.getTotalWeaponDamage();
                boolean isMagic = false; // You need to determine whether the damage is magic or physical
                List<Integer> damageInfo = target.registerDamage(damage, isMagic);
                int mitigateAmount = damageInfo.get(0);
                int actualDamage = damageInfo.get(1);
                System.out.println(getCharacterName() + " dealt " + actualDamage + " damage to " + target.getCharacterName());
            }
        }
    }

    private Deque<BaseAbility> determineActions() {
        Deque<BaseAbility> selectedAbilities = new LinkedList<>();
        List<BaseAbility> availableAbilities = new ArrayList<>(abilities);
        int actionsPerTurn = AppConfig.ACTIONS_PER_TURN;
        int numAbilities = availableAbilities.size();
        int numActions = Math.min(actionsPerTurn, numAbilities);

//        Randomizer randomizer = new Randomizer().getRandomValue(numActions);

        for (int i = 0; i < numActions; i++) {
            int randomIndex = Randomizer.INSTANCE.getRandomValue(numAbilities);
            BaseAbility ability = availableAbilities.remove(randomIndex);
            selectedAbilities.add(ability);
            numAbilities--;
        }
        return selectedAbilities;
    }


    // Public accessors
    public String getCharacterName() {
        return GameEngine.INSTANCE.getHeroes().toString();
    }

    public CharacterStats getCharacterStats() {
        return characterStats;
    }

    public CharacterEquipment getEquipment() {
        return equipment;
    }

    public int getActionPoints() { // Tveek?
        return characterStats.getCurrentActionPoints();
    }

    public int getHitPoints() {
        return characterStats.getCurrentHitPoints();
    }

    public int getEnergyLevel() {
        return characterStats.getCurrentEnergyLevel();
    }

    public List<BaseAbility> getAbilities() {
        return abilities;
    }

    public boolean isDead() {
        return characterStats.getCurrentHitPoints() <= 0;
        // en annan tanke var
        // if (characterStats.getCurrentHitPoints() == 0) {
        //            boolean dead = true;
        //            return dead;
        //        }
        //        else {
        //            return false;
        //        }
    }









    public List<Integer> registerDamage(int damage, boolean isMagic) {
        int mitigateAmount;
        int actualDamage;

        if (isMagic) {
            mitigateAmount = (int) (damage * characterStats.getDefenceRate());
        }
        else {
            int armorProtection = (int) equipment.getTotalArmorProtection();
            double totalMitigation = characterStats.getDefenceRate() * armorProtection;
            mitigateAmount = (int) (damage * totalMitigation);
        }

        actualDamage = damage - mitigateAmount;

        List<Integer> damageInfo = new ArrayList<>();
        damageInfo.add(mitigateAmount);
        damageInfo.add(actualDamage);

        return damageInfo;
    }

    public int registerHealing(int heal) {
        int currentHP = characterStats.getCurrentHitPoints();
        int newHP = currentHP + heal;
        characterStats.adjustHitPoints(newHP); // kan vara getCurrentHitPoints också
        return newHP;
    }

    public void roundReset() {
        characterStats.resetActionPoints();
        characterStats.resetEnergyLevel();
    }

    public void doTurn() {
        executeAction(true);
        roundReset();
    }

    public String toString() {
//        return characterStats.toString() + equipment.toString(); eller
        String characterInfo = getCharacterName();
        characterInfo += getCharacterStats();
        characterInfo += getEquipment();

        return characterInfo;
    }
}
