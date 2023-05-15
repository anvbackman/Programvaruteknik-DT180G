package com.dt180g.project.gear;

public class Weapon extends BaseGear {

    private int damage;
    private String weild;
    private Attribute attribute;

    public Weapon(Map<String, String> properties) {
        damage = Integer.parseInt(properties.get("damage"));
        weild = properties.get("weild");
        attribute = Attribute.valueOf(properties.get("attribute"));


        this.damage = damage;
        this.weild = weild;
        this.attribute = attribute;
    }

    public int getDamage() {
        return damage;
    }


    public String getWield() {
        return weild;
    }

    public BaseStat getStat() {

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
