package com.example.bomberman.entities.staticEntity.StaticEntity;

import com.example.bomberman.entities.AnimatedEntity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Portal extends AnimatedEntity {
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    public Portal(int x, int y) {
        super(x, y, Sprite.portal.getFxImage());
    }

    @Override
    public void update(Scene scene) {

    }
}
