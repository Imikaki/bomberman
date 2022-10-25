package com.example.bomberman.entities.Character;

import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Balloom extends Enemies {
    private int BalloomX;
    private int BalloomY;
    private Direction BalloomDirection = Direction.RIGHT;

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        enemyLeft = Sprite.balloom_left1.getFxImage();
        enemyRight = Sprite.balloom_right1.getFxImage();
        enemyDie = Sprite.balloom_dead.getFxImage();
    }

    @Override
    public void update(Scene scene) {
        super.update(scene);
    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }

    @Override
    public Direction getDirection() {
        if (BalloomX == x && BalloomY == y) {
            if (BalloomDirection == Direction.RIGHT) {
                BalloomDirection = Direction.LEFT;
            } else {
                BalloomDirection = Direction.RIGHT;
            }
        }
        BalloomX = x;
        BalloomY = y;
        return BalloomDirection;
    }

    @Override
    public boolean isColliding(Entity other) {
        return false;
    }
}
