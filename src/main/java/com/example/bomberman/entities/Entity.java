package com.example.bomberman.entities;

import com.example.bomberman.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;

    public Entity(int x, int y, Image img) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY() {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void updateImg() {

    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
