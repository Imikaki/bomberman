package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.entities.staticEntity.CarriableEntity.Bomb;
import com.example.bomberman.system.Direction;
import com.example.bomberman.system.KeyManager;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {
    KeyManager input;
    private Direction currentDirection = Direction.NONE;
    private int placedBomb = 0;
    private int haveBomb = 0;
    private int limitBomb = 3;
    private boolean canPlaceBomb = true; // can only place bomb after the amount of time
    private List<Bomb> bombs = new ArrayList<>();
    // effect.
    private boolean isSpeedBuff = false;
    private boolean isBombBuff = false;
    private boolean isFlameBuff = false;

    public Bomber(int x, int y, Image img, KeyManager input, Map map) {
        super(x, y, img);
        this.input = input;
    }

    @Override
    public void update() {

    }

    @Override
    public void handleMove() {
        moving = false;
        if (input.isPressed(KeyCode.UP)) {
            moving = true;
            currentDirection = Direction.UP;
        }
        if (input.isPressed(KeyCode.DOWN)) {
            moving = true;
            currentDirection = Direction.DOWN;
        }
        if (input.isPressed(KeyCode.LEFT)) {
            moving = true;
            currentDirection = Direction.LEFT;
        }
        if (input.isPressed(KeyCode.RIGHT)) {
            moving = true;
            currentDirection = Direction.RIGHT;
        }
    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }


}
