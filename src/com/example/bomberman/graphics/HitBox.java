package com.example.bomberman.graphics;

public class HitBox {
    private int x;
    private int y;
    private int width;
    private int height;

    public HitBox(int xUnit, int yUnit, int width, int height) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.width = width;
        this.height = height;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private boolean inside(int otherX, int otherY) {
        return otherX >= x && otherX <= x + width && otherY >= y && otherY <= y + height;
    }

    public boolean collideWith(HitBox other) {
        return inside(other.x, other.y)
                || inside(other.x + other.width, other.y)
                || inside(other.x, other.y + other.height)
                || inside(other.x + other.width, other.y + other.height)
                || other.inside(x, y)
                || other.inside(x + width, y)
                || other.inside(x, y + height)
                || other.inside(x + width, y + height);
    }
}
