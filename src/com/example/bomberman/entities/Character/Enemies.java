package com.example.bomberman.entities.Character;

import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public abstract class Enemies extends Character {
    private final int maxTimeMoving = 30;
    public boolean isKilled = false;
    protected Image[] enemyLeft;
    protected Image[] enemyRight;
    protected Image enemyDie;
    private Direction curDirection = Direction.LEFT;
    private Direction nextDirection;
    private int timeMoving = 0;

    public Enemies(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
    }

    @Override
    public void update(Scene scene) {
        if (isKilled) {
            return;
        }
        nextDirection = getDirection();
        switch (nextDirection) {
            case UP: {
                moveUp();
                break;
            }
            case DOWN: {
                moveDown();
                break;
            }
            case LEFT: {
                moveLeft();
                break;
            }
            case RIGHT: {
                moveRight();
                break;
            }
        }
        if (curDirection == Direction.LEFT && nextDirection == Direction.RIGHT) {
            resetTimeMoving();
            curDirection = Direction.RIGHT;
        } else if (curDirection == Direction.RIGHT && nextDirection == Direction.LEFT) {
            resetTimeMoving();
            curDirection = Direction.LEFT;
        }
        if (curDirection == Direction.LEFT) {
            imageView.setImage(enemyLeft[timeMoving/(maxTimeMoving/3)]);
        }
        if (curDirection == Direction.RIGHT) {
            imageView.setImage(enemyRight[timeMoving/(maxTimeMoving/3)]);
        }
        timeMoving++;
        if (timeMoving == maxTimeMoving) {
            resetTimeMoving();
        }
        imageView.relocate(x, y);
    }

    @Override
    public abstract boolean canMove(int x, int y);

    public void isKilled() {
        isKilled = true;
        setImage(enemyDie);
    }

    public void resetTimeMoving() {
        timeMoving = 0;
    }

    public abstract Direction getDirection();

    public Direction getCurDirection() {
        return curDirection;
    }
}
