package com.example.bomberman.entities.staticEntity.CarriableEntity;

import com.example.bomberman.entities.AnimatedEntity;
import com.example.bomberman.entities.Entity;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends AnimatedEntity {
    public static int bombSize = 1;
    Entity center;
    List<Entity> entitiesLeft;
    List<Entity> entitiesRight;
    List<Entity> entitiesUp;
    List<Entity> entitiesDown;
    List<Entity> explosion;
    private int timeExplode = 90;
    /**
     * direction
     * 0 left
     * 1 right
     * 2 up
     * 3 down
     */

    private int[] around = {x, x, y, y};
    private boolean[] walled = new boolean[4];

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        explosion = new ArrayList<Entity>();
    }

    @Override
    public void update(Scene scene) {

    }

    public void checkEntities() {

    }

    public void addExplosion() {

    }

    public void bombExplodes() {

    }


    public void update() {
        if (timeExplode > 0) {
            timeExplode--;
        } else {
            checkEntities();
            addExplosion();

        }
    }
}
