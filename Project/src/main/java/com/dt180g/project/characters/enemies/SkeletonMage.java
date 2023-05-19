package com.dt180g.project.characters.enemies;

import com.dt180g.project.support.AppConfig;

public class SkeletonMage extends BaseEnemy {
    public SkeletonMage(int enemyNumber) {
        super(AppConfig.ENEMY_SKELETON_MAGE + enemyNumber, AppConfig.ATTRIBUTE_VALUES_SKELETON_MAGE);
    }
}
