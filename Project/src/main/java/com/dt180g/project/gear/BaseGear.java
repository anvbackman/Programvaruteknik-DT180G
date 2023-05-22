package com.dt180g.project.gear;

import java.util.*;
import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.characters.heroes.BaseHero;

public abstract class BaseGear {

    private String type;
    private String gearName;
    private List<Class<?>> classRestrictions;


    protected BaseGear(String type, String gearName, String classRestrictions) {
        this.type = type;
        this.gearName = gearName;
        this.classRestrictions = new ArrayList<>();

        for (String restriction : classRestrictions.split(",")) {
            String className = BaseHero.class.getPackageName() + "." + restriction.trim();
            try {
                Class<?> heroClass = Class.forName(className);
                this.classRestrictions.add(heroClass);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public String getType() {
        return type;
    }

    public List<Class<?>> getClassRestrictions() {
        return classRestrictions;
    }

    public boolean checkClassRestriction(Class<?> checkClassType) {
//        checkClassType = Character.class;
        return classRestrictions.contains(checkClassType);

    }

    public abstract BaseStat getStat();

    @Override
    public String toString() {
        return gearName;
    }
}
