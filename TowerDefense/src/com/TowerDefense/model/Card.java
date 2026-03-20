package com.TowerDefense.model;

public class Card {
    public final String     name;
    public final String     emoji;
    public final Troop.Type troopType;
    public final int        cost;

    public Card(Troop.Type type) {
        this.troopType = type;
        switch (type) {
            case KNIGHT -> { name = "Caballero"; emoji = "⚔";  cost = 3; }
            case ARCHER -> { name = "Arquero";   emoji = "🏹"; cost = 2; }
            default     -> { name = "Gigante";   emoji = "👊"; cost = 5; }
        }
    }
}