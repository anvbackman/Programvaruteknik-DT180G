package com.dt180g.project.abilities;

public class ElementalBolt extends BaseAbility {

    private String magicalPhrase;
    private String element;



    public ElementalBolt(String element) {

    }

    public boolean isMagic() {
        return true;
    }

    public boolean isHeal() {
        return false;
    }

    public int getAmountOfTargets() {

    }

}
