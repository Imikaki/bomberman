package com.example.bomberman.graphics;

import javafx.geometry.Rectangle2D;

public class HitBox {
    private int x;
    private int y;
    private int width;
    private int height;
    Rectangle2D border;

    public HitBox(int xUnit, int yUnit, int width, int height) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.width = width;
        this.height = height;
        this.border = new Rectangle2D(x, y, this.width, this.height);
        border = new Rectangle2D(x, y, this.width, this.height);
    }

    public Rectangle2D getBorder() {
        return border;
    }

    public void setBorder(Rectangle2D border) {
        this.border = border;
    }

    public boolean checkCollision(HitBox box) {
        return box.getBorder().intersects(this.getBorder());
    }

    public void setPosition(int x, int y, int _width, int _height) {
        border = new Rectangle2D(x, y, _width, _height);
    }
}
