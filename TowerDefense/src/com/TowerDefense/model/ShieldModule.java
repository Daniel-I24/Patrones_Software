package com.TowerDefense.model;

public class ShieldModule extends TowerDecorator {

    private static final int ARMOR_BONUS = 15;
    private static final int DAMAGE_BONUS = 3;
    private static final String MODULE_NAME = "+ Shield Module";

    public ShieldModule(Tower decoratedTower) {
        super(decoratedTower);
    }

    @Override
    public String getName() {
        return decoratedTower.getName() + MODULE_NAME;
    }

    @Override
    public int getArmor() {
        return decoratedTower.getArmor() + ARMOR_BONUS;
    }

    @Override
    public int getDamage() {
        return decoratedTower.getDamage() + DAMAGE_BONUS;
    }

    @Override
    public String getDescription() {
        return decoratedTower.getDescription()
                + "\n  🔵 Shield Module: +" + ARMOR_BONUS + " Armor"
                + " | +" + DAMAGE_BONUS + " DMG (reflected damage)";
    }
}