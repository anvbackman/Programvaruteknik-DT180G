package com.dt180g.project.gear;

import com.dt180g.project.stats.Attribute;
import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

public class Weapon extends BaseGear {

    private int damage;
    private String wield;
    private Attribute attribute;

    public Weapon(Map<String, String> properties) {
        
        String damageStr = properties.get("damage");
        String wieldStr = properties.get("wield");
        String attributeStr = properties.get("attribute");

        this.damage = Integer.parseInt(damageStr);
        this.wield = wieldStr;
        int bonusValue = Randomizer.INSTANCE.getRandomValue(1, AppConfig.WEAPON_ATTRIBUTE_VALUE_UPPER_BOUND);
        this.attribute = new Attribute(StatsManager.getInstance().getRandomAttributeName(), bonusValue);


    }

    public int getDamage() {
        return damage;
    }


    public String getWield() {
        return wield;
    }

    public BaseStat getStat() {
        return;
    }

    public boolean isTwoHanded() {
        if ("Two Handed".equals(GearManager.getInstance().getAllMappedWeapons().get(1))) {
            return true;
        }
        else { return false; }
    }

    @Override
    public String toString() {
        return " of " + getStat();
    }
}
