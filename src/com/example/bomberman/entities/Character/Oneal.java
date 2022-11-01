package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import static com.example.bomberman.graphics.Sprite.SCALED_SIZE;

// oneal chỉ di chuyển dọc
public class Oneal extends Enemies {
    private Direction OnealDirection = Direction.DOWN;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        enemyLeft = new Image[]{Sprite.oneal_left1.getFxImage(), Sprite.oneal_left2.getFxImage(), Sprite.oneal_left3.getFxImage()};
        enemyRight = new Image[]{Sprite.oneal_right1.getFxImage(), Sprite.oneal_right2.getFxImage(), Sprite.oneal_right3.getFxImage()};
        enemyDie = Sprite.oneal_dead.getFxImage();
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
        if (OnealDirection == Direction.UP) {
            if (!canMove(x, y - SCALED_SIZE) || !moveBomb(x, y - SCALED_SIZE)) {
                OnealDirection = Direction.DOWN;
            }
        }
        if (OnealDirection == Direction.DOWN) {
            if (!canMove(x, y + SCALED_SIZE)|| !moveBomb(x, y + SCALED_SIZE)) {
                OnealDirection = Direction.UP;
            }
        }
        return OnealDirection;
    }
}

