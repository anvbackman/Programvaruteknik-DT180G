package com.dt180g.project.gear;

import java.util.List;

public abstract class BaseGear {

    private String type;
    private String gearName;
    private List<Class<?>> classRestrictions;


    protected BaseGear(String type, String gearName, String classRestrictions) {
        this.type = type;
        this.gearName = gearName;
        this.classRestrictions = classRestrictions;
    }

    public String getType() {
        return type;
    }

    public List<Class<?>> getClassRestrictions() {
        return classRestrictions;
    }

    public boolean checkClassRestrictions(Class<?>) {

    }

    public BaseStat getStat() {
        return Armor.getStat(), Weapon.getStat();
    }

    @Override
    public String toString() {
        return gearName.toString();
    }
}
