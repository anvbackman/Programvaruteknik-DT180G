package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.support.AppConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkeletonWarrior extends BaseEnemy {
    public SkeletonWarrior(int enemyNumber) {
        super(AppConfig.ENEMY_SKELETON_WARRIOR + " " + enemyNumber, AppConfig.ATTRIBUTE_VALUES_SKELETON_WARRIOR);
        List<String> enemyType = new ArrayList<>();
        enemyType.add(SkeletonWarrior.class.getSimpleName());
        equipEnemy(enemyType);
        addAbilities(Arrays.asList(new WeaponAttack(), new HeavyAttack(), new Whirlwind()));
    }
}
