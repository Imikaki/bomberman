package com.example.bomberman.entities.staticEntity.StaticEntity;

import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.HitBox;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Flame extends Entity {
    private int time = 20;

    public Flame(int x, int y, Image img) {

        super(x, y, img);
    }

    @Override
    public void update(Scene scene) {
        if (time > 0) {
            time--;
        } else {
            this.remove();
        }
    }
}
