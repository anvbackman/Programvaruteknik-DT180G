package com.dt180g.project.gear;

import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.stats.Trait;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.Map;

public class Armor extends BaseGear {

    private final int protection;
    private final String material;
    private final Trait trait;

    public Armor(Map<String, String> armor) {

        super(armor.get("type"), armor.get("name"), armor.get("restriction"));

        protection = Integer.parseInt(armor.get("protection"));
        material = armor.get("material");
        trait = new Trait(StatsManager.INSTANCE.getRandomTraitName(),
                Randomizer.INSTANCE.getRandomValue(1, AppConfig.ARMOR_STAT_VALUE_UPPER_BOUND));



    }

    public int getProtection() {
        return protection;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public BaseStat getStat() {
        return trait;
    }

    @Override
    public String toString() {
//        return BaseGear.class.getName() + " of " + trait.getStatName();
        return String.format("%s of %s", super.toString(), getStat().getStatName());
    }

}
