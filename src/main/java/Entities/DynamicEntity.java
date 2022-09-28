package Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import System.Control.CONTROL;

public abstract class DynamicEntity extends Entity{
    protected CONTROL control;
    protected boolean run;
    protected double speed;

    public DynamicEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void render(GraphicsContext gc) {
        // TODO
    }

    @Override
    public void update() {
        // TODO
    }

    public void updateControl() {
        // TODO
    }

    public abstract Image chooseSprite();
}
