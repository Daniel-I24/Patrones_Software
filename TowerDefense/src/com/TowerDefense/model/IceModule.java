package com.TowerDefense.model;

public class IceModule extends TowerDecorator {

    private static final int DAMAGE_BONUS = 5;
    private static final double SLOW_FACTOR = 0.3;
    private static final String MODULE_NAME = "+ Ice Module";

    public IceModule(Tower decoratedTower) {
        super(decoratedTower);
    }

    @Override
    public String getName() {
        return decoratedTower.getName() + MODULE_NAME;
    }

    @Override
    public int getDamage() {
        return decoratedTower.getDamage() + DAMAGE_BONUS;
    }

    @Override
    public String getDescription() {
        return decoratedTower.getDescription()
                + "\n  🔵 Ice Module: +" + DAMAGE_BONUS + " DMG"
                + " | Slows enemies by " + (int)(SLOW_FACTOR * 100) + "%";
    }
}