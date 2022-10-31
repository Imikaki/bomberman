package com.example.bomberman.entities.staticEntity.CarriableEntity;

import com.example.bomberman.Map;
import com.example.bomberman.entities.AnimatedEntity;
import com.example.bomberman.entities.Character.Bomber;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.StaticEntity.Flame;
import com.example.bomberman.entities.staticEntity.StaticEntity.Portal;
import com.example.bomberman.entities.staticEntity.StaticEntity.Wall;
import com.example.bomberman.graphics.Sound;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.Timer;

public class Bomb extends AnimatedEntity {
    public static int bombSize = 1;
    // left, right, up, down
    public static int[] dix = {-1, 1, 0, 0};
    public static int[] diy = {0, 0, -1, 1};
    public boolean waiting = true;
    private long timeDelay;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        waiting = true;
        isRemoved = false;
        timeDelay = 2000;
        Timer clock = new Timer();
        clock.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                waiting = false;
                bombExplodes();
            }
        }, timeDelay);
    }

    @Override
    public void update(Scene scene) {
        for (Flame flame : Map.flames) {
            if (Map.collide(this, flame)) {
                bombExplodes();
            }
        }
        animate();
        Sprite s = null;
        if (isExploded) {
            s = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, 35);
        } else if (waiting) {
            s = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 35);
        }
        img = s.getFxImage();
    }

    private void bombExplodes() {
        if (isExploded() || isRemoved) {
            return;
        }
        breakEntity();
        int _x = this.x / Sprite.SCALED_SIZE;
        int _y = this.y / Sprite.SCALED_SIZE;
        for (int i = 0; i < dix.length; ++i) {
            for (int j = 1; j <= Bomber.explodeRange; ++j) {
                int x_ = _x + dix[i] * j;
                int y_ = _y + diy[i] * j;
                Entity entity = Map.getEntity(x_ * Sprite.SCALED_SIZE, y_ * Sprite.SCALED_SIZE);
                if (entity != null) {
                    if (entity instanceof Wall) {
                        break;
                    }
                    if (entity.canBreak()) {
                        if (entity instanceof Item || entity instanceof Portal) {
                            Map.flames.add(new Flame(x_, y_, Direction.values()[i + 1], false));
                        }
                        entity.remove();
                        break;
                    }
                    Map.flames.add(new Flame(x_, y_, Direction.values()[i + 1], (j == Bomber.explodeRange)));
                }
            }
        }
        Map.flames.add(new Flame(_x, _y, Direction.NONE, false));
        Sound.bombExplode.play();
    }
}