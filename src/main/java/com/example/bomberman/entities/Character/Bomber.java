package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.system.Direction;
import com.example.bomberman.system.KeyManager;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Bomber extends Character {
    private Direction currentDirection = Direction.NONE;

    public int placedBomb = 0;
    public int maxBomb = 1;
    KeyManager input;
    Map map;
    private boolean move = false;
    public Bomber(int x, int y, Image img, KeyManager input, Map map) {
        super(x, y, img);
        this.input = input;
        this.map = map;
    }

    @Override
    public void update() {

    }

    @Override
    public void handleMove() {
        move = false;

    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }
}
