package com.example.bomberman.entities.staticEntity.CarriableEntity;

import com.example.bomberman.entities.AnimatedEntity;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public abstract class Item extends AnimatedEntity {
    public Item(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(Scene scene) {

    }
}
