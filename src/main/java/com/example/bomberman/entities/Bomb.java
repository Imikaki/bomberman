package com.example.bomberman.entities;

import javafx.scene.image.Image;

public class Bomb extends DynamicEntity{
    private int timeExplode;
    private boolean exploded;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public Image chooseSprite() {
        // TODO
        return null;
    }

    @Override
    public void update() {
        // TODO
    }
}
