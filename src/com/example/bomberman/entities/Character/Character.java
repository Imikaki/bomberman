package com.example.bomberman.entities.Character;

import com.example.bomberman.entities.DynamicEntity;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.CarriableEntity.Bomb;
import com.example.bomberman.entities.staticEntity.CarriableEntity.Item;
import com.example.bomberman.entities.staticEntity.StaticEntity.Grass;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Character extends DynamicEntity {
    protected boolean alive = true;
    protected boolean killed = false;
    protected boolean moving = false;

    public Character(int x, int y, Image img) {
        super(x, y, img);
        alive = true;
        moving = false;
    }

    @Override
    public void update(Scene scene) {

    }

    public void kill() {
        killed = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                alive = false;
                killed = false;
            }
        }, 750L);
    }

    public abstract boolean canMove(int x, int y);

    protected boolean collide(Entity entity) {
        return (entity instanceof Grass || entity instanceof Character);
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isMoving() {
        return moving;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    protected void moveUp() {
        y-= getSpeed();
    }

    protected void moveDown() {
        y += getSpeed();
    }

    protected void moveRight() {
        x += getSpeed();
    }

    protected void moveLeft() {
        x -= getSpeed();
    }
}
