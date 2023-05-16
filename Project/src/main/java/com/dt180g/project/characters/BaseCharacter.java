package com.dt180g.project.characters;

import com.dt180g.project.abilities.AbilityInfo;
import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.*;
public abstract class BaseCharacter {

    private CharacterStats characterStats;
    private CharacterEquipment equipment;
    private List<BaseAbility> abilities;


    protected BaseCharacter(CharacterStats characterStats) {
        this.characterStats = characterStats;

    }

    protected void addAbilities(List<BaseAbility> abilities) {
        this.abilities = abilities;
    }

    protected String getTurnInformation(String turn) {
        return turn;
    }

    protected void executeAction(boolean execute) {
        Deque<BaseAbility> actions = determineActions();
        int availableAP = characterStats.getAP();
        int availableEnergy = characterStats.getEnergy();

        for (BaseAbility ability : actions) {
            AbilityInfo abilityInfo = ability;
            int abilityAPCost = ability.getActionPointCost();
            int abilityEnergyCost = ability.getEnergyCost();

            if (availableAP >= abilityAPCost && availableEnergy >= abilityEnergyCost) {
                ability.execute();
                availableAP -= abilityAPCost;
                availableEnergy -= abilityEnergyCost;
            }
        }

        characterStats.setAP(availableAP);
        characterStats.setEnergy(availableEnergy);

    }

    private Deque<BaseAbility> determineActions() {
        int actionsPerTurn = AppConfig.ACTIONS_PER_TURN;
        List<BaseAbility> relevantAbilities = new ArrayList<>();

        for (BaseAbility ability : abilities) {
            if (ability.isRelevant(this)) {
                relevantAbilities.add(ability);
            }
        }
        Collections.shuffle(relevantAbilities, new Randomizer());
        return new ArrayDeque<>(relevantAbilities.subList(0, actionsPerTurn));
    }

    public List<Integer> registerDamage(int damage, boolean isMagic) {

    }

    public int registerHealing(int heal) {
        return heal;
    }

    public void roundReset() {
        int resetAP = AppConfig.ROUND_RESET_AP;
        int resetEnergy = AppConfig.ROUND_RESET_ENERGY;
        characterStats.setAP(resetAP);
        characterStats.setEnergy(resetEnergy);
    }

    public void doTurn() {

    }

    public String toString() {
        return characterStats.toString() + equipment.toString();
    }
}
