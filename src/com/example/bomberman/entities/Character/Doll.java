package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.Random;

import static com.example.bomberman.graphics.Sprite.SCALED_SIZE;

// doll di chuyển ngẫu nhiên
public class Doll extends Enemies {
    private Direction dollDirection = Direction.NONE;
    private int n = SCALED_SIZE/speed;
    private boolean move = false;
    public Doll(int x, int y, Image img) {
        super(x, y, img);
        enemyLeft = new Image[] {Sprite.doll_left1.getFxImage(),Sprite.doll_left2.getFxImage(),Sprite.doll_left3.getFxImage()};
        enemyRight = new Image[] {Sprite.doll_right1.getFxImage(),Sprite.doll_right2.getFxImage(),Sprite.doll_right3.getFxImage()};
        enemyDie = Sprite.doll_dead.getFxImage();
        //randomDirection();
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
        /*if (dollDirection == Direction.LEFT) {
            if (!canMove(x - SCALED_SIZE, y)) {
                dollDirection = Direction.RIGHT;
            } *//*else if (canMove(x, y + SCALED_SIZE) && canMove(x, y - SCALED_SIZE)) {
                randomDirection();
            }*//*
        }
        if (dollDirection == Direction.RIGHT) {
            if (!canMove(x + SCALED_SIZE, y)) {
                dollDirection = Direction.LEFT;
            } *//*else if (canMove(x, y + SCALED_SIZE) && canMove(x, y - SCALED_SIZE)) {
                randomDirection();
            }*//*
        }
        if (dollDirection == Direction.UP) {
            if (!canMove(x, y - SCALED_SIZE)) {
                dollDirection = Direction.DOWN;
            } *//*else if (canMove(x + SCALED_SIZE, y) && canMove(x - SCALED_SIZE, y)) {
                randomDirection();
            }*//*
        }
        if (dollDirection == Direction.DOWN) {
            if (!canMove(x, y + SCALED_SIZE)) {
                dollDirection = Direction.UP;
            } *//*else if (canMove(x + SCALED_SIZE, y) && canMove(x - SCALED_SIZE, y)) {
                randomDirection();
            }*//*
        }*/
        return dollDirection;
    }

    @Override
    public boolean isColliding(Entity other) {
        return false;
    }

    /*public void randomDirection() {
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
    }*/
}
