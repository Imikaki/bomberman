package Entities;

import javafx.scene.image.Image;

public class bombItem extends Item{
    private static final int bombNumberUp = 1;

    public bombItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void powerUp() {
        // TODO
    }
}
