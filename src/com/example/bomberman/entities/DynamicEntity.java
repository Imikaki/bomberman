package com.example.bomberman.entities;

import com.example.bomberman.graphics.Sprite;
import javafx.scene.image.Image;


public abstract class DynamicEntity extends AnimatedEntity {
    private int speed = Sprite.SCALED_SIZE;

    public DynamicEntity(int x, int y, Image img) {
        super(x, y, img);
    }

    public DynamicEntity(int x, int y, Image img, int speed) {
        super(x, y, img);
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    protected boolean collide(Entity entity) {
        return false;
    }
}
