package com.TowerDefense.model;

public class Troop {

    public enum Type { KNIGHT, ARCHER, GIANT }

    public String name;
    public int health, maxHealth;
    public int damage, speed, range;
    public float x, y;
    public boolean isAlly;
    public Type type;

    public Troop(Type type, float x, float y, boolean isAlly) {
        this.type   = type;
        this.x      = x;
        this.y      = y;
        this.isAlly = isAlly;

        switch (type) {
            case KNIGHT -> { name = "Caballero"; health = 120; damage = 15; speed = 2; range = 30; }
            case ARCHER -> { name = "Arquero";   health = 60;  damage = 25; speed = 3; range = 80; }
            case GIANT  -> { name = "Gigante";   health = 250; damage = 8;  speed = 1; range = 20; }
        }
        maxHealth = health;
    }

    public boolean isAlive()  { return health > 0; }
    public boolean inRange(float targetX) { return Math.abs(x - targetX) <= range; }
}