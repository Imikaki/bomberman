package com.example.bomberman.entities.staticEntity.CarriableEntity;

import com.example.bomberman.Map;
import com.example.bomberman.entities.Character.Bomber;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class FlameItem extends Item {
    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(Scene scene) {
        if (Map.collide(this, Map.bomberman)) {
            Bomber.explodeRange = 3;
            this.remove();
        }
    }
}

