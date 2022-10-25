package com.example.bomberman.entities.staticEntity.CarriableEntity;

import com.example.bomberman.Map;
import com.example.bomberman.entities.DynamicEntity;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.awt.*;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(Scene scene) {
        if (collided(this, Map.bomberman)) {
            Map.bomberman.setSpeed(Sprite.SCALED_SIZE / 2);
            DynamicEntity.n = 2;
            this.remove();
        }
    }

    public boolean collided(Entity e1, Entity e2) {
        Rectangle r1 = new Rectangle(e1.getX(), e1.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        Rectangle r2 = new Rectangle(e2.getX(), e2.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        if (e1.getX() == e2.getX() && e1.getY() == e2.getY()) {
            return true;
        }
        return false;
    }
}
