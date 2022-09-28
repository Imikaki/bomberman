package Entities;

import javafx.scene.image.Image;

public class speedItem extends Item{
    private static final int speedUp = 1;

    public speedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void powerUp() {
        // TODO
    }
}
