package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.support.AppConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LichLord extends BaseEnemy {
    public LichLord() {
        super(AppConfig.ENEMY_LICH_LORD, AppConfig.ATTRIBUTE_VALUES_LICH_LORD);
        List<String> enemyType = new ArrayList<>();
        enemyType.add(LichLord.class.getSimpleName());
        equipEnemy(enemyType);
        addAbilities(Arrays.asList(new WeaponAttack(), new HeavyAttack(), new Whirlwind(), new FocusedHeal(),
                new ElementalBolt(AppConfig.ELEMENT_FIRE), new ElementalBlast(AppConfig.ELEMENT_FIRE)));
    }
}
