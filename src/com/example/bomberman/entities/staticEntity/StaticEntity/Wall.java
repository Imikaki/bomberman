package com.example.bomberman.entities.staticEntity.StaticEntity;

import com.example.bomberman.entities.AnimatedEntity;
import javafx.scene.image.Image;

public class Wall extends AnimatedEntity {
    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
    }
}
