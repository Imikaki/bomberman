package com.example.bomberman.entities.staticEntity.StaticEntity;

import com.example.bomberman.Map;
import com.example.bomberman.entities.AnimatedEntity;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Flame extends AnimatedEntity {
    private int time = 20;
    private Direction direction;
    private boolean isLast = false;

    public Flame(int x, int y, Image img) {

        super(x, y, img);
        breakEntity();
    }

    public Flame(int x, int y, Direction direction, boolean isLast) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.direction = direction;
        this.isLast = isLast;
        breakEntity();
    }

    @Override
    public void update(Scene scene) {
        if (isRemoved) {
            Map.flames.remove(this);
        }
        Sprite sprite = Sprite.bomb;
        switch (direction) {
            case LEFT:
                if (isLast == false) {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, 35);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, animate, 35);
                }
                break;
            case RIGHT:
                if (isLast == false) {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, 35);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, animate, 35);
                }
                break;
            case UP:
                if (isLast == false) {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate, 35);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, animate, 35);
                }
                break;
            case DOWN:
                if (isLast == false) {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate, 35);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 35);
                }
                break;
        }
        this.img = sprite.getFxImage();
        animate();
    }
}
