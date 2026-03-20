package com.TowerDefense.model;

public class BaseTower implements Tower {

    private static final String BASE_NAME = "Base Tower";
    private static final int BASE_DAMAGE = 10;
    private static final double BASE_FIRE_RATE = 1.0;
    private static final int BASE_ARMOR = 0;
    private static final int BASE_GOLD = 0;

    @Override
    public String getName() {
        return BASE_NAME;
    }

    @Override
    public int getDamage() {
        return BASE_DAMAGE;
    }

    @Override
    public double getFireRate() {
        return BASE_FIRE_RATE;
    }

    @Override
    public int getArmor() {
        return BASE_ARMOR;
    }

    @Override
    public int getGoldGeneration() {
        return BASE_GOLD;
    }

    @Override
    public String getDescription() {
        return "[ " + BASE_NAME + " ] "
                + "DMG: " + BASE_DAMAGE
                + " | Fire Rate: " + BASE_FIRE_RATE
                + " | Armor: " + BASE_ARMOR
                + " | Gold: " + BASE_GOLD;
    }
}