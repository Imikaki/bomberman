package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.Random;

// kondoria di chuyển ngẫu nhiên và có thể đi qua vật cản
public class Kondoria extends Enemies {
    private Direction konDirection = Direction.LEFT;
    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
        enemyLeft = new Image[]{Sprite.kondoria_left1.getFxImage(),Sprite.kondoria_left2.getFxImage(),Sprite.kondoria_left3.getFxImage()};
        enemyRight = new Image[]{Sprite.kondoria_right1.getFxImage(),Sprite.kondoria_right2.getFxImage(),Sprite.kondoria_right3.getFxImage()};
        enemyDie = Sprite.kondoria_dead.getFxImage();
        speed = 2;
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
        if (this.x >= Sprite.SCALED_SIZE + getSpeed() && this.x <= Sprite.SCALED_SIZE * (Map.getCol() - 2) - getSpeed()
                && this.y >= Sprite.SCALED_SIZE + getSpeed() && this.y <= Sprite.SCALED_SIZE * (Map.getRow() - 2) - getSpeed()) {
            if (konDirection == Direction.LEFT) {
                if (this.x % Sprite.SCALED_SIZE != 0) {
                    konDirection = Direction.LEFT;
                } else randomDirection();
            }
            if (konDirection == Direction.RIGHT) {
                if (this.x % Sprite.SCALED_SIZE != 0) {
                    konDirection = Direction.RIGHT;
                } else randomDirection();
            }
            if (konDirection == Direction.UP) {
                if (this.y % Sprite.SCALED_SIZE != 0) {
                    konDirection = Direction.UP;
                } else randomDirection();
            }
            if (konDirection == Direction.DOWN) {
                if (this.y % Sprite.SCALED_SIZE != 0) {
                    konDirection = Direction.DOWN;
                } else randomDirection();
            }
        } else if (this.y == Sprite.SCALED_SIZE) konDirection = Direction.DOWN;
        else if (this.y == Sprite.SCALED_SIZE * (Map.getRow() - 2)) konDirection = Direction.UP;
        else if (this.x==Sprite.SCALED_SIZE) konDirection = Direction.RIGHT;
        else konDirection = Direction.LEFT;
        return konDirection;
    }

    public void randomDirection() {
        Random random = new Random();
        int direction =random.nextInt(4);
        switch (direction) {
            case 0:
                konDirection = Direction.LEFT;
                break;
            case 1:
                konDirection = Direction.RIGHT;
                break;
            case 2:
                konDirection = Direction.UP;
                break;
            case 3:
                konDirection = Direction.DOWN;
                break;
        }
    }
}
