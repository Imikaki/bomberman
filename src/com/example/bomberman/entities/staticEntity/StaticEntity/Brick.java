package com.example.bomberman.entities.staticEntity.StaticEntity;

import com.example.bomberman.entities.AnimatedEntity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Brick extends AnimatedEntity {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(Scene scene) {
        if (this.isExploded == false) {
            return;
        }
        animate();
        Sprite sprite = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, 35);
        this.img = sprite.getFxImage();
    }

}
