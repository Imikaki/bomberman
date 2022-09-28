package com.example.bomberman.entities;

import javafx.scene.image.Image;

public class flameItem extends Item{
    private static final int flameRangeUp = 1;

    public flameItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void powerUp() {
        // TODO
    }
}
