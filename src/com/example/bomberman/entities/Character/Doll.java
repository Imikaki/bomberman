package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.image.Image;

import java.util.Random;

import static com.example.bomberman.graphics.Sprite.SCALED_SIZE;

// doll thay đổi direction khi gặp vật cản
public class Doll extends Enemies {
    private Direction dollDirection = Direction.LEFT;
    //private int n = SCALED_SIZE / speed;
    private boolean move = false;

    public Doll(int x, int y, Image img) {
        super(x, y, img);
        enemyLeft = new Image[]{Sprite.doll_left1.getFxImage(), Sprite.doll_left2.getFxImage(), Sprite.doll_left3.getFxImage()};
        enemyRight = new Image[]{Sprite.doll_right1.getFxImage(), Sprite.doll_right2.getFxImage(), Sprite.doll_right3.getFxImage()};
        enemyDie = Sprite.doll_dead.getFxImage();
        this.speed = 1;
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
            if (!canMove(x - SCALED_SIZE,y) || !moveBomb(x - SCALED_SIZE,y)) {
                while (dollDirection == Direction.LEFT) {
                    randomDirection();
                }
                if (dollDirection == Direction.UP) {
                    if (!checkMoveUp()) dollDirection = Direction.RIGHT;
                }
                if (dollDirection == Direction.DOWN) {
                    if (!checkMoveDown()) dollDirection = Direction.RIGHT;
                }
            }
        }
        if (dollDirection == Direction.RIGHT) {
            if (!canMove(x + SCALED_SIZE,y) || !moveBomb(x + SCALED_SIZE,y)) {
                while (dollDirection == Direction.RIGHT) {
                    randomDirection();
                }
                if (dollDirection == Direction.UP) {
                    if (!checkMoveUp()) dollDirection = Direction.LEFT;
                }
                if (dollDirection == Direction.DOWN) {
                    if (!checkMoveDown()) dollDirection = Direction.LEFT;
                }
            }
        }
        if (dollDirection == Direction.UP) {
            if (!canMove(x,y - SCALED_SIZE) || !moveBomb(x,y - SCALED_SIZE)) {
                while (dollDirection == Direction.UP) {
                    randomDirection();
                }
                if (dollDirection == Direction.LEFT) {
                    if (!checkMoveLeft()) dollDirection = Direction.DOWN;
                }
                if (dollDirection == Direction.RIGHT) {
                    if (!checkMoveRight()) dollDirection = Direction.DOWN;
                }
            }
        }
        if (dollDirection == Direction.DOWN) {
            if (!canMove(x,y + SCALED_SIZE) || !moveBomb(x,y + SCALED_SIZE)) {
                while (dollDirection == Direction.DOWN) {
                    randomDirection();
                }
                if (dollDirection == Direction.LEFT) {
                    if (!checkMoveLeft()) dollDirection = Direction.UP;
                }
                if (dollDirection == Direction.RIGHT) {
                    if (!checkMoveRight()) dollDirection = Direction.UP;
                }
            }
        }
        /*if (dollDirection == Direction.LEFT) {
            if (checkMoveUp() && checkMoveDown() && checkMoveLeft()) randomDirection();
            if (checkMoveUp() && checkMoveDown() && !checkMoveLeft()) {
                while(dollDirection == Direction.LEFT) randomDirection();
            }
            if (checkMoveUp() && !checkMoveDown() && checkMoveLeft()) {
                while (dollDirection == Direction.DOWN) randomDirection();
            }
            if (checkMoveUp() && !checkMoveDown() && !checkMoveLeft()) {
                dollDirection = Direction.UP;            }
            if (!checkMoveUp() && checkMoveDown() && checkMoveLeft()) {
                while (dollDirection == Direction.UP) randomDirection();
            }
            if (!checkMoveUp() && checkMoveDown() && !checkMoveLeft()) {
                dollDirection = Direction.DOWN;
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
                while(dollDirection == Direction.LEFT) randomDirection();
            }
            if (checkMoveUp() && !checkMoveDown() && checkMoveRight()) {
                while (dollDirection == Direction.DOWN) randomDirection();
            }
            if (checkMoveUp() && !checkMoveDown() && !checkMoveRight()) dollDirection = Direction.UP;
            if (!checkMoveUp() && checkMoveDown() && checkMoveRight()) {
                while (dollDirection == Direction.UP) checkMoveRight();
            }
            if (!checkMoveUp() && checkMoveDown() && !checkMoveRight()) dollDirection = Direction.DOWN;

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
            if (checkMoveLeft() && !checkMoveRight() && !checkMoveUp()) dollDirection = Direction.LEFT;
            if (!checkMoveLeft() && checkMoveRight() && checkMoveUp()) {
                while (dollDirection == Direction.LEFT) checkMoveRight();
            }
            if (!checkMoveLeft() && checkMoveRight() && !checkMoveUp()) dollDirection = Direction.RIGHT;

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
            if (checkMoveLeft() && !checkMoveRight() && !checkMoveDown()) dollDirection = Direction.LEFT;
            if (!checkMoveLeft() && checkMoveRight() && checkMoveDown()) {
                while (dollDirection == Direction.LEFT) checkMoveRight();
            }
            if (!checkMoveLeft() && checkMoveRight() && !checkMoveDown()) dollDirection = Direction.RIGHT;

            if (!checkMoveLeft() && !checkMoveRight() && checkMoveDown()) {
                while (dollDirection == Direction.LEFT || dollDirection == Direction.RIGHT ) randomDirection();
            }
            if (!checkMoveLeft() && !checkMoveRight() && !checkMoveDown()) {
                dollDirection = Direction.UP;
            }
        }*/
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
