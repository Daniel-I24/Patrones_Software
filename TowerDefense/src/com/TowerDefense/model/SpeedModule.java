package com.TowerDefense.model;

public class SpeedModule extends TowerDecorator {

    private static final double SPEED_BONUS = 0.5;
    private static final String MODULE_NAME = "+ Speed Module";

    public SpeedModule(Tower decoratedTower) {
        super(decoratedTower);
    }

    @Override
    public String getName() {
        return decoratedTower.getName() + MODULE_NAME;
    }

    @Override
    public double getFireRate() {
        return decoratedTower.getFireRate() + SPEED_BONUS;
    }

    @Override
    public String getDescription() {
        return decoratedTower.getDescription()
                + "\n  🔵 Speed Module: +" + SPEED_BONUS + " Fire Rate";
    }
}