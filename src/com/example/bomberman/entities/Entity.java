package com.example.bomberman.entities;

import com.example.bomberman.graphics.HitBox;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;
    protected ImageView imageView;
    protected boolean isRemoved = false;
    protected HitBox borderBox;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity() {

    }

    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        imageView = new ImageView(img);
        imageView.setX(x);
        imageView.setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return img;
    }

    public void setImage(Image img) {
        this.img = img;
    }
    public HitBox getBorderBox() {
        return borderBox;
    }

    public void setBorderBox(HitBox borderBox) {
        this.borderBox = borderBox;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update(Scene scene);

    public void remove() {
        isRemoved = true;
    }

    public boolean coordiantes(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean isColliding(Entity other) {
        return borderBox.collideWith(other.getBorderBox());
    }

    public ImageView getImageView() {
        return imageView;
    }
}

