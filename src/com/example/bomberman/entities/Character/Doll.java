package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.image.Image;

import java.util.Random;

// doll di chuyen ngau nhien
public class Doll extends Enemies {
    private Direction dollDirection = Direction.LEFT;
    private boolean move = false;

    public Doll(int x, int y, Image img) {
        super(x, y, img);
        enemyLeft = new Image[]{Sprite.doll_left1.getFxImage(), Sprite.doll_left2.getFxImage(), Sprite.doll_left3.getFxImage()};
        enemyRight = new Image[]{Sprite.doll_right1.getFxImage(), Sprite.doll_right2.getFxImage(), Sprite.doll_right3.getFxImage()};
        enemyDie = Sprite.doll_dead.getFxImage();
        this.speed = 2;
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
        if (dollDirection == Direction.LEFT) {
            if (checkMoveUp() && checkMoveDown() && checkMoveLeft()) randomDirection();
            if (checkMoveUp() && checkMoveDown() && !checkMoveLeft()) {
                while(dollDirection == Direction.LEFT) randomDirection();
            }
            if (checkMoveUp() && !checkMoveDown() && checkMoveLeft()) {
                while (dollDirection == Direction.DOWN) randomDirection();
            }
            if (checkMoveUp() && !checkMoveDown() && !checkMoveLeft()) {
                while(dollDirection == Direction.DOWN || dollDirection == Direction.LEFT) randomDirection();
            }
            if (!checkMoveUp() && checkMoveDown() && checkMoveLeft()) {
                while (dollDirection == Direction.UP) randomDirection();
            }
            if (!checkMoveUp() && checkMoveDown() && !checkMoveLeft()) {
                while(dollDirection == Direction.UP || dollDirection == Direction.LEFT) randomDirection();
            }
            if (!checkMoveUp() && !checkMoveDown() && checkMoveLeft()) {
                while (dollDirection == Direction.DOWN || dollDirection == Direction.UP ) randomDirection();
            }
            if (!checkMoveUp() && !checkMoveDown() && !checkMoveLeft()) {
                dollDirection = Direction.RIGHT;
            }
        }
        if (dollDirection == Direction.RIGHT) {
            if (checkMoveUp() && checkMoveDown() && checkMoveRight()) randomDirection();
            if (checkMoveUp() && checkMoveDown() && !checkMoveRight()) {
                while(dollDirection == Direction.RIGHT) randomDirection();
            }
            if (checkMoveUp() && !checkMoveDown() && checkMoveRight()) {
                while (dollDirection == Direction.DOWN) randomDirection();
            }
            if (checkMoveUp() && !checkMoveDown() && !checkMoveRight()) {
                while(dollDirection == Direction.DOWN || dollDirection == Direction.RIGHT) randomDirection();
            }
            if (!checkMoveUp() && checkMoveDown() && checkMoveRight()) {
                while (dollDirection == Direction.UP) randomDirection();
            }
            if (!checkMoveUp() && checkMoveDown() && !checkMoveRight()) {
                while(dollDirection == Direction.UP || dollDirection == Direction.RIGHT) randomDirection();
            }

            if (!checkMoveUp() && !checkMoveDown() && checkMoveRight()) {
                while (dollDirection == Direction.DOWN || dollDirection == Direction.UP ) randomDirection();
            }
            if (!checkMoveUp() && !checkMoveDown() && !checkMoveRight()) {
                dollDirection = Direction.LEFT;
            }
        }
        if (dollDirection == Direction.UP) {
            if (checkMoveLeft() && checkMoveRight() && checkMoveUp()) randomDirection();
            if (checkMoveLeft() && checkMoveRight() && !checkMoveUp()) {
                while(dollDirection == Direction.UP) randomDirection();
            }
            if (checkMoveLeft() && !checkMoveRight() && checkMoveUp()) {
                while (dollDirection == Direction.RIGHT) randomDirection();
            }
            if (checkMoveLeft() && !checkMoveRight() && !checkMoveUp()) {
                while(dollDirection == Direction.UP || dollDirection == Direction.RIGHT) randomDirection();
            }
            if (!checkMoveLeft() && checkMoveRight() && checkMoveUp()) {
                while (dollDirection == Direction.LEFT) randomDirection();
            }
            if (!checkMoveLeft() && checkMoveRight() && !checkMoveUp()) {
                while(dollDirection == Direction.LEFT || dollDirection == Direction.UP) randomDirection();
            }

            if (!checkMoveLeft() && !checkMoveRight() && checkMoveUp()) {
                while (dollDirection == Direction.LEFT || dollDirection == Direction.RIGHT ) randomDirection();
            }
            if (!checkMoveLeft() && !checkMoveRight() && !checkMoveUp()) {
                dollDirection = Direction.DOWN;
            }
        }
        if (dollDirection == Direction.DOWN) {
            if (checkMoveLeft() && checkMoveRight() && checkMoveDown()) randomDirection();
            if (checkMoveLeft() && checkMoveRight() && !checkMoveDown()) {
                while(dollDirection == Direction.DOWN) randomDirection();
            }
            if (checkMoveLeft() && !checkMoveRight() && checkMoveDown()) {
                while (dollDirection == Direction.RIGHT) randomDirection();
            }
            if (checkMoveLeft() && !checkMoveRight() && !checkMoveDown()) {
                while(dollDirection == Direction.DOWN || dollDirection == Direction.RIGHT) randomDirection();
            }
            if (!checkMoveLeft() && checkMoveRight() && checkMoveDown()) {
                while (dollDirection == Direction.LEFT) randomDirection();
            }
            if (!checkMoveLeft() && checkMoveRight() && !checkMoveDown()) {
                while(dollDirection == Direction.DOWN || dollDirection == Direction.LEFT) randomDirection();
            }

            if (!checkMoveLeft() && !checkMoveRight() && checkMoveDown()) {
                while (dollDirection == Direction.LEFT || dollDirection == Direction.RIGHT ) randomDirection();
            }
            if (!checkMoveLeft() && !checkMoveRight() && !checkMoveDown()) {
                dollDirection = Direction.UP;
            }
        }
        return dollDirection;
    }

    public void randomDirection() {
        Random random = new Random();
        int direction =random.nextInt(4);
        switch (direction) {
            case 0:
                dollDirection = Direction.LEFT;
                break;
            case 1:
                dollDirection = Direction.RIGHT;
                break;
            case 2:
                dollDirection = Direction.UP;
                break;
            case 3:
                dollDirection = Direction.DOWN;
                break;
        }
    }
}
