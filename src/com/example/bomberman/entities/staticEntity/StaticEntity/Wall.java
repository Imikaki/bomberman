package com.example.bomberman.entities.staticEntity.StaticEntity;

import com.example.bomberman.entities.AnimatedEntity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Wall extends AnimatedEntity {
    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    public Wall(int x, int y) {
        super(x, y, Sprite.wall.getFxImage());
    }

    @Override
    public void update(Scene scene) {
    }
}
