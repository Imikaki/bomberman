package com.example.bomberman.entities;

import javafx.scene.Scene;
import javafx.scene.image.Image;

public abstract class AnimatedEntity extends Entity {
    protected final int MAX_ANIMATE = 600;
    protected int frame = 0;

    public AnimatedEntity() {
    }

    public AnimatedEntity(int x, int y, Image img) {
        super(x, y, img);
    }

    protected void animate() {
        if (frame++ > MAX_ANIMATE) {
            frame = 0;
        }
    }

    public abstract void update(Scene scene);
}
