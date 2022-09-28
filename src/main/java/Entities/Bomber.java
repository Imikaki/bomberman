package Entities;

import javafx.scene.image.Image;

public class Bomber extends DestroyableEntity{
    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void die() {

    }

    @Override
    public Image chooseSprite() {
        return null;
    }
}
