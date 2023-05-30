package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.support.AppConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LichLord extends BaseEnemy {
    public LichLord() {
        super(AppConfig.ENEMY_LICH_LORD, AppConfig.ATTRIBUTE_VALUES_LICH_LORD);
        List<String> enemyType = new ArrayList<>();
        enemyType.add(LichLord.class.getSimpleName());
        getCharacterStats().adjustStatStaticModifier(AppConfig.TRAIT_VITALITY, getCharacterStats().getCurrentHitPoints() * AppConfig.BOSS_HEALTH_MULTIPLIER);
        equipEnemy(enemyType);
        addAbilities(Arrays.asList(new WeaponAttack(), new HeavyAttack(), new Whirlwind(), new FocusedHeal(),
                new ElementalBolt(AppConfig.ELEMENT_FIRE), new ElementalBlast(AppConfig.ELEMENT_FIRE)));

//        CharacterStats characterStats = new CharacterStats(AppConfig.ATTRIBUTE_VALUES_LICH_LORD);
//        double vitalityModifier = AppConfig.BOSS_HEALTH_MULTIPLIER;
//        double vitalityIncrease = characterStats.getCurrentHitPoints() * vitalityModifier;
//        characterStats.adjustHitPoints((int)vitalityIncrease);

    }
}
