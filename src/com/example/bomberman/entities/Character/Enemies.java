package com.example.bomberman.entities.Character;

import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Enemies extends Character {
    public Enemies(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(Scene scene) {

    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }
}
