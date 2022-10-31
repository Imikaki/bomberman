package com.example.bomberman.entities.staticEntity.CarriableEntity;

import com.example.bomberman.Map;
import com.example.bomberman.entities.Character.Bomber;
import com.example.bomberman.graphics.Sound;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class BombItem extends Item {
    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(Scene scene) {
        if (Map.collide(this, Map.bomberman)) {
            skill(Map.bomberman);
            this.remove();
            Sound.collectItem.playy();
        }
    }

    public boolean skill(Bomber bomber) {
        bomber.setBombLimit(bomber.getBombLimit() + 1);
        return true;
    }
}
