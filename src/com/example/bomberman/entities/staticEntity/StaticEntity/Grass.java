package com.example.bomberman.entities.staticEntity.StaticEntity;

import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Grass extends Entity {
    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    public Grass(int x, int y) {
        super(x, y, Sprite.grass.getFxImage());
    }

    @Override
    public void update(Scene scene) {

    }
}
