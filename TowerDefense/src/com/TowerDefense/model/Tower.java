package com.TowerDefense.model;

public interface Tower {
    String getName(); // retorna el nombre de la torre
    int getDamage(); // retorna el daño que hace la torre
    double getFireRate(); // retorna la velocidad de disparo
    int getArmor(); // retorna la armadura de la torre
    int getGoldGeneration(); // retorna el oro generado por la torre
    String getDescription(); // retorna una descripcion de la torre
}