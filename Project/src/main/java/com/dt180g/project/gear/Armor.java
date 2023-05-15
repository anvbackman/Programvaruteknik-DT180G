package com.dt180g.project.gear;

import java.util.Map;

public class Armor extends BaseGear {

    private int protection;
    private String material;
    private Trait trait;

    public Armor(Map<String, String properties>) {

        protection = Integer.parseInt(properties.get("protection"));
        material = properties.get("material");
        trait = Trait.valueOf(properties.get("trait"));

        this.protection = protection;
        this.material = material;
        this.trait = trait;
    }

    public int getProtection() {
        return protection;
    }

    public String getMaterial() {
        return material;
    }

    public BaseStat getStat() {

    }

    public String toString() {
        return " of " + getStat();
    }

}
