package com.dt180g.project.gear;

import com.dt180g.project.stats.Attribute;
import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.gear.BaseGear;
import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.Map;

public class Weapon extends BaseGear {

    private int damage;
    private String wield;
    private Attribute attribute;

    public Weapon(Map<String, String> weapon) {
        super("Weapon", weapon.get("name"), (weapon.get("restriction")));

        damage = Integer.parseInt(weapon.get("damage"));
        wield = weapon.get("wield");
        attribute = new Attribute(StatsManager.INSTANCE.getRandomAttributeName(),
                Randomizer.INSTANCE.getRandomValue(1, AppConfig.WEAPON_ATTRIBUTE_VALUE_UPPER_BOUND));



    }

    public int getDamage() {
        return damage;
    }


    public String getWield() {
        return wield;
    }

    @Override
    public BaseStat getStat() {
        return attribute;
    }

    public boolean isTwoHanded() {
        return wield.contains("Two Handed");
    }

    @Override
    public String toString() {
        return " of " + getStat();
    }
}
