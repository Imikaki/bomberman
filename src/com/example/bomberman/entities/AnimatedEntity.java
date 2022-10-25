package com.example.bomberman.entities;

import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.Timer;
import java.util.TimerTask;


public abstract class AnimatedEntity extends Entity {
    protected final int MAX_ANIMATE = 600;
    protected int frame = 0;

    protected boolean isExploded = false;

    public AnimatedEntity() {
    }

    public AnimatedEntity(int x, int y, Image img) {
        super(x, y, img);
    }

    public abstract void update(Scene scene);

    public void breakEntity() {
        isExploded = true;
        animate = 0;
        Timer clock = new Timer();
        clock.schedule(new TimerTask() {
            @Override
            public void run() {
                isExploded = false;
                isRemoved = true;
            }
        }, 250L);
    }

    public boolean isExploded() {
        return isExploded;
    }

    @Override
    public void remove() {
        breakEntity();
    }
}
