package com.example.bomberman.entities.Character;

import javafx.scene.image.Image;

public class Minvo extends Character {
    public Minvo(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public void handleMove() {

    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }
}
