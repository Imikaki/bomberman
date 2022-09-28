package com.example.bomberman.entities;

import javafx.scene.image.Image;

public class Brick extends DestroyableEntity{
    public Brick(int xUnit, int yUnit, Image img) {
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
