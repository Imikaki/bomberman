package Entities;

import javafx.scene.image.Image;

public class Explosion extends DynamicEntity{
    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public Image chooseSprite() {
        return null;
    }
}
