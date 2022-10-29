package com.example.bomberman.entities.Character;

import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Minvo extends Enemies {
    public Minvo(int x, int y, Image img) {
        super(x, y, img);
        enemyLeft = new Image[]{Sprite.minvo_left1.getFxImage(),Sprite.minvo_left2.getFxImage(),Sprite.minvo_left3.getFxImage()};
        enemyRight = new Image[] {Sprite.minvo_right1.getFxImage(),Sprite.minvo_right2.getFxImage(),Sprite.minvo_right3.getFxImage()};
        enemyDie = Sprite.minvo_dead.getFxImage();
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
        return DirectionFinding.getDirection(this);
    }

    @Override
    public boolean isColliding(Entity other) {
        return false;
    }
}
