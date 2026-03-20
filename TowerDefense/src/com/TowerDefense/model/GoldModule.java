package com.TowerDefense.model;

public class GoldModule extends TowerDecorator {

    private static final int GOLD_BONUS = 20;
    private static final int DAMAGE_BONUS = 2;
    private static final String MODULE_NAME = "+ Gold Module";

    public GoldModule(Tower decoratedTower) {
        super(decoratedTower);
    }

    @Override
    public String getName() {
        return decoratedTower.getName() + MODULE_NAME;
    }

    @Override
    public int getGoldGeneration() {
        return decoratedTower.getGoldGeneration() + GOLD_BONUS;
    }

    @Override
    public int getDamage() {
        return decoratedTower.getDamage() + DAMAGE_BONUS;
    }

    @Override
    public String getDescription() {
        return decoratedTower.getDescription()
                + "\n  🔵 Gold Module: +" + GOLD_BONUS + " Gold/wave"
                + " | +" + DAMAGE_BONUS + " DMG (golden bullets)";
    }
}