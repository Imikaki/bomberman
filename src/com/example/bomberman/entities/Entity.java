package com.example.bomberman.entities;

import com.example.bomberman.entities.staticEntity.StaticEntity.Grass;
import com.example.bomberman.entities.staticEntity.StaticEntity.Wall;
import com.example.bomberman.graphics.HitBox;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class Entity {
    public static final int maxAnimate = 7500;
    public boolean isRemoved = false;
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected Image img;
    protected HitBox borderBox;
    protected int animate = 0;
    protected ImageView imageView;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity() {

    }

    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity(int x, int y) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
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

    public void render(Group group) {
        if (isRemoved == true) return;
        group.getChildren().add(this.getImageView());
    }

    public abstract void update(Scene scene);

    public void remove() {
        isRemoved = true;
    }

    public boolean coordiantes(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean canBreak() {
        return ((this instanceof Grass) || (this instanceof Wall)) == false;
    }

    public boolean isColliding(Entity other) {
        return borderBox.collideWith(other.getBorderBox());
    }

    public ImageView getImageView() {
        imageView = new ImageView(img);
        imageView.setX(x);
        imageView.setY(y);
        return imageView;
    }

    public void animate() {
        if (animate < maxAnimate) animate++;
        else animate = 0;
    }

}

