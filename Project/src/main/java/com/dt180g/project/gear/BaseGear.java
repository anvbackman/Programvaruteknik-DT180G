package com.dt180g.project.gear;

import java.util.*;
import com.dt180g.project.stats.BaseStat;

public abstract class BaseGear {

    private String type;
    private String gearName;
    private List<Class<?>> classRestrictions;


    protected BaseGear(String type, String gearName, String classRestrictions) {
        this.type = type;
        this.gearName = gearName;
        this.classRestrictions = new ArrayList<>();
        String[] classNames = classRestrictions.split(",");
        ClassLoader classLoader = getClass().getClassLoader();
        for (String className : classNames) {
            try {
                Class<?> clazz = classLoader.loadClass(className.trim());
                this.classRestrictions.add(clazz);
            } catch (ClassNotFoundException e) {
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
