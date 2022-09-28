package com.example.bomberman.entities;

import javafx.scene.image.Image;

public abstract class DestroyableEntity extends DynamicEntity{
    protected boolean death;

    public DestroyableEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract void die();

    public abstract Image chooseSprite();
}
