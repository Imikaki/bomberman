package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import static com.example.bomberman.graphics.Sprite.SCALED_SIZE;

// balloom chỉ di chuyển ngang
public class Balloom extends Enemies {
    private Direction BalloomDirection = Direction.LEFT;

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        enemyLeft = new Image[]{Sprite.balloom_left1.getFxImage(), Sprite.balloom_left2.getFxImage(), Sprite.balloom_left3.getFxImage()};
        enemyRight = new Image[]{Sprite.balloom_right1.getFxImage(), Sprite.balloom_right2.getFxImage(), Sprite.balloom_right3.getFxImage()};
        enemyDie = Sprite.balloom_dead.getFxImage();
    }

    @Override
    public void update(Scene scene) {
        super.update(scene);
    }

    @Override
    public boolean canMove(int x, int y) {
        Entity entity = Map.getEntity(x, y);
        if (entity == null) {
            return true;
        }
        return super.collide(entity);
    }

    @Override
    public Direction getDirection() {
        if (BalloomDirection == Direction.LEFT) {
            if (!canMove(x - SCALED_SIZE, y) || !moveBomb(x - SCALED_SIZE, y)) {
                BalloomDirection = Direction.RIGHT;
            }
        }
        if (BalloomDirection == Direction.RIGHT) {
            if (!canMove(x + SCALED_SIZE, y)|| !moveBomb(x + SCALED_SIZE, y)) {
                BalloomDirection = Direction.LEFT;
            }
        }
        return BalloomDirection;
    }

    @Override
    public boolean isColliding(Entity other) {
        return false;
    }
}
