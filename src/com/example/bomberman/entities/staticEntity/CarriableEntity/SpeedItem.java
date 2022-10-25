package com.example.bomberman.entities.staticEntity.CarriableEntity;

import com.example.bomberman.Map;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class SpeedItem extends Item {
    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(Scene scene) {
        if (Map.collide(this, Map.bomberman)) {
            Map.bomberman.setSpeed(Sprite.SCALED_SIZE / 2);
            this.remove();
        }
    }
}
