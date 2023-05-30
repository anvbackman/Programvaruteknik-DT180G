package com.dt180g.project.characters;

import com.dt180g.project.GameEngine;
import com.dt180g.project.GameRunner;

import com.dt180g.project.abilities.AbilityInfo;
import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.characters.enemies.*;
import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.characters.heroes.BaseHero;
import com.dt180g.project.support.ActivityLogger;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;
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
        this.abilities = new ArrayList<>();


    }

    // Public accessors
    public String getCharacterName() {

        return GameEngine.INSTANCE.getAllCharacters().toString();
    }

    public CharacterStats getCharacterStats() {
        return characterStats;
    }

    public CharacterEquipment getEquipment() {
        return equipment;
    }

    public int getActionPoints() {
        return characterStats.getCurrentActionPoints();
    }

    public int getHitPoints() {
//        return characterStats.getCurrentHitPoints();
        return characterStats.getStatValue(AppConfig.TRAIT_VITALITY);
    }

    public int getEnergyLevel() {
        return characterStats.getCurrentEnergyLevel();
    }

    public List<BaseAbility> getAbilities() {
        return abilities;
    }

    protected String getTurnInformation(String turn) {
//        String character = getCharacterName();
//        int actionPoints = getActionPoints();
//        int hitPoints = getHitPoints();
//        int energyLevel = getEnergyLevel();
//
//        String turnInfo = "[" + turn.toUpperCase() + "] " + character + " | " + actionPoints + " AP | " +
//                hitPoints + " HP | " + energyLevel + " Energy";
//        return turnInfo;
        String formatString = "%s[%s%s%s%s%s%s%s%s%s";

        if (this instanceof SkeletonArcher || this instanceof SkeletonWarrior || this instanceof SkeletonMage) {
            return String.format(formatString, AppConfig.ANSI_RED, AppConfig.CHARACTER_TYPE_ENEMY + " TURN] ", getCharacterName(), " | ", getActionPoints() + " AP ", " | ", getHitPoints() + " HP ", " | ", getEnergyLevel() + " Energy ", AppConfig.ANSI_RESET);
        }
        if (this instanceof LichLord) {
            return String.format(formatString, AppConfig.ANSI_RED, AppConfig.CHARACTER_TYPE_ENEMY + " BOSS TURN] ", getCharacterName(), " | ", getActionPoints() + " AP ", " | ", getHitPoints() + " HP ", " | ", getEnergyLevel() + " Energy ", AppConfig.ANSI_RESET);
        }
        else {
            return String.format(formatString, AppConfig.ANSI_BLUE, AppConfig.CHARACTER_TYPE_HERO + " TURN] ", getCharacterName(), " | ", getActionPoints() + " AP ", " | ", getHitPoints() + " HP ", " | ", getEnergyLevel() + " Energy ", AppConfig.ANSI_RESET);
        }
    }

    public boolean isDead() {
        return getHitPoints() <= 0;
    }

    protected void addAbilities(List<BaseAbility> abilities) {
        this.abilities = abilities;
    }

    protected void executeActions(boolean targets) {

        Deque<BaseAbility> selectedAbilities = determineActions();
        int magicDamage = getCharacterStats().getMagicPower();
        int physicalDamage = getCharacterStats().getPhysicalPower();
        int healingPower = getCharacterStats().getHealingPower();
        int availableActionPoints = getActionPoints();
        int availableEnergy = getEnergyLevel();

        while (!selectedAbilities.isEmpty() && availableActionPoints >= AppConfig.LOWEST_AP_COST) {
            BaseAbility baseAbility = selectedAbilities.pop();
            int actionPointCost = baseAbility.getActionPointCost();
            int energyCost = baseAbility.getEnergyCost();

            if (availableActionPoints >= actionPointCost && availableEnergy >= energyCost) {
                availableActionPoints -= actionPointCost;
                availableEnergy -= energyCost;
                characterStats.adjustActionPoints(- actionPointCost);
                characterStats.adjustEnergyLevel(- energyCost);

                if (baseAbility.isMagic() && !baseAbility.isHeal()) {
                    baseAbility.execute(magicDamage + energyCost, targets);
                }

                if (!baseAbility.isMagic() && !baseAbility.isHeal()) {
                    baseAbility.execute(physicalDamage + energyCost, targets);
                }

                if (baseAbility.isHeal()) {
                    baseAbility.execute(healingPower + energyCost, !targets);
                }
            }
            else {

            }
        }





//        int availableAP = getActionPoints();
//        int availableEnergy = getEnergyLevel();
//
//        for (BaseAbility ability : selectedAbilities) {
//            int abilityAPCost = ability.getActionPointCost();
//            int abilityEnergyCost = ability.getEnergyCost();
//
//            // Check if the ability can be afforded
//            if (abilityAPCost <= availableAP && abilityEnergyCost <= availableEnergy) {
//                // Perform the specific logic for triggering the ability
//                boolean abilityTriggered = ability.execute(abilityAPCost, execute);
//
//                if (abilityTriggered) {
//                    // Register cost reductions
//                    availableAP -= abilityAPCost;
//                    availableEnergy -= abilityEnergyCost;
//                }
//
//            }
//
//        }
//        // Update the character's action points and energy level
//        characterStats.adjustActionPoints(availableAP);
//        characterStats.adjustEnergyLevel(availableEnergy);
//
//        List<BaseCharacter> targets;
//
//        if (execute) {
//            targets = GameEngine.INSTANCE.getAllCharacters().stream().filter(character -> character instanceof BaseEnemy).collect(Collectors.toList());
//        }
//        else {
//            targets = GameEngine.INSTANCE.getAllCharacters().stream().filter(character -> character instanceof BaseHero).collect(Collectors.toList());
//        }
//
//        // Eller
////        if (execute) {
////            List<BaseEnemy> enemies = GameEngine.INSTANCE.getEnemies();
////            targets = new ArrayList<>(enemies.size());
////            targets.addAll(enemies);
////        }
////        else {
////            List<BaseHero> heroes = GameEngine.INSTANCE.getHeroes();
////            targets = new ArrayList<>(heroes.size());
////            targets.addAll(heroes);
////        }
//
//
//
//
//        for (BaseCharacter target : targets) {
//            if (!target.isDead()) {
//                int damage = equipment.getTotalWeaponDamage();
//                boolean isMagic = false; // You need to determine whether the damage is magic or physical
//                List<Integer> damageInfo = target.registerDamage(damage, isMagic);
//                int mitigateAmount = damageInfo.get(0);
//                int actualDamage = damageInfo.get(1);
//                System.out.println(getCharacterName() + " dealt " + actualDamage + " damage to " + target.getCharacterName());
//            }
//        }
    }

    private Deque<BaseAbility> determineActions() {
        Deque<BaseAbility> selectedAbilities = new ArrayDeque<>();
        List<BaseAbility> availableAbilitiesList = getAbilities();
        int actionsPerTurn = AppConfig.ACTIONS_PER_TURN;
        int availableAbilities = availableAbilitiesList.size();
//        int numActions = Math.min(actionsPerTurn, availableAbilities);



        for (int i = 0; i < actionsPerTurn; i++) {
            int randomAbility = Randomizer.INSTANCE.getRandomValue(0, availableAbilities - 1);
            BaseAbility ability = availableAbilitiesList.get(randomAbility);
            selectedAbilities.add(ability);

        }
        return selectedAbilities;
    }










    public List<Integer> registerDamage(int damage, boolean isMagic) {
        int defenceRate = getCharacterStats().getDefenceRate();
        int armorProtection = getEquipment().getTotalArmorProtection();
        int mitigateAmount;
        int actualDamage;

        if (isMagic) {
            actualDamage = Math.max(0, (damage - defenceRate));
        }
        else {
            int resist = damage - defenceRate;
            resist -= armorProtection;
            actualDamage = Math.max(0, resist);
//            double totalMitigation = characterStats.getDefenceRate() * armorProtection;
//            mitigateAmount = (int) (damage * totalMitigation);
        }

        mitigateAmount = damage - actualDamage;
        characterStats.adjustHitPoints(- actualDamage);


        return Arrays.asList(mitigateAmount, actualDamage);
    }

    public int registerHealing(int heal) {
        int currentHP = getHitPoints();
        int maxHP = characterStats.getTotalHitPoints();
        if (currentHP + heal > maxHP) {
            characterStats.resetHitPoints();
        }
        else {
            characterStats.adjustHitPoints(heal);
        }
        return getHitPoints();
    }

    public void roundReset() {

        int actionPoints = AppConfig.ROUND_RESET_AP;
        int energyLevel = AppConfig.ROUND_RESET_ENERGY;
        int currentActionPoints = getActionPoints();
        int currentEnergyLevel = getEnergyLevel();
        int maxActionPoints = characterStats.getTotalActionPoints();
        int maxEnergyLevel = characterStats.getTotalEnergyLevel();

        if (currentActionPoints + actionPoints > maxActionPoints) {
            characterStats.resetActionPoints();
        }

        else {
            characterStats.adjustActionPoints(actionPoints);
        }

        if (currentEnergyLevel + energyLevel > maxEnergyLevel) {
            characterStats.resetEnergyLevel();
        }
        else {
            characterStats.adjustEnergyLevel(energyLevel);
        }
    }

    public abstract void doTurn();

    public String toString() {
        String header = String.format("%s****************************************\n%28s\n****************************************\n", AppConfig.ANSI_WHITE, getCharacterName());
        List<List<String>> character = new ArrayList<>();
        List<String> stats = new ArrayList<>();
        stats.add(getCharacterStats().toString());
        character.add(stats);
        List<String> equipment = new ArrayList<>();
        equipment.add(getEquipment().toString());
        character.add(equipment);

        return header + IOHelper.formatAsTable(character);
    }
}
