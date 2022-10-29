package com.example.bomberman.entities.Character;

import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Kondoria extends Enemies {
    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
        enemyLeft = new Image[]{Sprite.kondoria_left1.getFxImage(),Sprite.kondoria_left2.getFxImage(),Sprite.kondoria_left3.getFxImage()};
        enemyRight = new Image[]{Sprite.kondoria_right1.getFxImage(),Sprite.kondoria_right2.getFxImage(),Sprite.kondoria_right3.getFxImage()};
        enemyDie = Sprite.kondoria_dead.getFxImage();

    }

    @Override
    public void update(Scene scene) {

    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }

    @Override
    public Direction getDirection() {
        int i = Math.random() < 0.5 ? 0 : 1;
        if (i == 0) {
            return DirectionFinding.getRandomDirection(this);
        } else {
            return DirectionFinding.getDirection(this);
        }
    }

    @Override
    public boolean isColliding(Entity other) {
        return false;
    }
}
