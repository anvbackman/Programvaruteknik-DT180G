package com.dt180g.project.characters.enemies;

import com.dt180g.project.support.AppConfig;

public class SkeletonArcher extends BaseEnemy {

    public SkeletonArcher(int enemyNumber) {
        super(AppConfig.ENEMY_SKELETON_ARCHER + enemyNumber, AppConfig.ATTRIBUTE_VALUES_SKELETON_ARCHER);
    }
}
