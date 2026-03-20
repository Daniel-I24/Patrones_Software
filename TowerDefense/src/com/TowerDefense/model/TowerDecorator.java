package com.TowerDefense.model;

public abstract class TowerDecorator implements Tower {

    protected final Tower decoratedTower;

    public TowerDecorator(Tower decoratedTower) {
        this.decoratedTower = decoratedTower;
    }

    @Override
    public String getName() {
        return decoratedTower.getName();
    }

    @Override
    public int getDamage() {
        return decoratedTower.getDamage();
    }

    @Override
    public double getFireRate() {
        return decoratedTower.getFireRate();
    }

    @Override
    public int getArmor() {
        return decoratedTower.getArmor();
    }

    @Override
    public int getGoldGeneration() {
        return decoratedTower.getGoldGeneration();
    }

    @Override
    public String getDescription() {
        return decoratedTower.getDescription();
    }
}