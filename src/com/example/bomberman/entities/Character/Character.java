package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.entities.DynamicEntity;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.StaticEntity.Grass;
import com.example.bomberman.graphics.Sprite;
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
        moving = false;
        killed = false;
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
                isRemoved = true;
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

    public boolean checkBomb() {
        int x_ = this.x / Sprite.SCALED_SIZE;
        int y_ = this.y / Sprite.SCALED_SIZE;
        for (int i = 0; i < Map.bombs.size(); i++) {
            int r=Map.bombs.get(i).getX() / Sprite.SCALED_SIZE;
            int c=Map.bombs.get(i).getY() / Sprite.SCALED_SIZE;
            if (r == x_ && c == y_) {
                return true;
            }
        }
        return false;
    }

    public Entity checkHaveBomb(int x, int y) {
        for (Entity e : Map.bombs) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        return null;
    }

    public boolean moveBomb(int x, int y) {
        Entity entity = checkHaveBomb(x, y);
        if (entity == null) {
            return true;
        }
        return super.collide(entity);
    }

    protected void moveUp() {
        if (!checkBomb()) {
            y -= getSpeed();
        }
    }

    protected void moveDown() {
        if (!checkBomb()) {
            y += getSpeed();
        }
    }

    protected void moveRight() {
        if (!checkBomb()) {
            x += getSpeed();
        }
    }

    protected void moveLeft() {
        if (!checkBomb()) {
            x -= getSpeed();
        }
    }
}
