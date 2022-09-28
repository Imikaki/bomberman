package com.example.bomberman.entities;

import javafx.scene.image.Image;

public class Enemy extends DestroyableEntity{
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void die() {

    }

    @Override
    public Image chooseSprite() {
        return null;
    }
}
