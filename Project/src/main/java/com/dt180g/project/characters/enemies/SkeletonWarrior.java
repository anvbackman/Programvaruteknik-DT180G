package com.dt180g.project.characters.enemies;

import com.dt180g.project.support.AppConfig;

public class SkeletonWarrior extends BaseEnemy {
    public SkeletonWarrior(int enemyNumber) {
        super(AppConfig.ENEMY_SKELETON_WARRIOR + enemyNumber, AppConfig.ATTRIBUTE_VALUES_SKELETON_WARRIOR);
    }
}
