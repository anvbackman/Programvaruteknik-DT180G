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

    }

    private Deque<BaseAbility> determineActions() {

    }

    public List<Integer> registerDamage(int damage, boolean isMagic) {

    }

    public int registerHealing(int heal) {
        return heal;
    }

    public void roundReset() {

    }

    public void doTurn() {

    }

    public String toString() {

    }
}
