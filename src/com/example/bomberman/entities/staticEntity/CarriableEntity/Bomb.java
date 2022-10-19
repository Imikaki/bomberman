package com.example.bomberman.entities.staticEntity.CarriableEntity;

import com.example.bomberman.Map;
import com.example.bomberman.entities.AnimatedEntity;
import com.example.bomberman.entities.Character.Character;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.StaticEntity.Brick;
import com.example.bomberman.entities.staticEntity.StaticEntity.Flame;
import com.example.bomberman.entities.staticEntity.StaticEntity.Wall;
import com.example.bomberman.graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends AnimatedEntity {
    public static int bombSize = 1;

    private int left = x;
    private int right = x;
    private int top = y;
    private int bottom = y;

    Entity centerEntity;
    Entity leftEntity;
    Entity rightEntity;
    Entity topEntity;
    Entity bottomEntity;

    List<Entity> entitiesLeft;
    List<Entity> entitiesRight;
    List<Entity> entitiesUp;
    List<Entity> entitiesDown;
    List<Entity> explosion;
    private int timeExplode = 90;
    private boolean leftWalled = false;
    private boolean rightWalled = false;
    private boolean topWalled = false;
    private boolean bottomWalled = false;
    private boolean exploded = false;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        explosion = new ArrayList<Entity>();
    }

    @Override
    public void update(Scene scene) {
        if (timeExplode > 0) {
            --timeExplode;
        } else {
            checkEntities();
            addFlame();
            Map.bombExplode(explosion);
            bombExplodes();
        }
        if (timeExplode < 20) {
            img = Sprite.bomb_2.getFxImage();
        } else if (timeExplode < 70) {
            img = Sprite.bomb_1.getFxImage();
        }
    }

    private void checkEntities() {
        centerEntity = Map.getEntity(x, y);

        entitiesLeft = new ArrayList<Entity>();
        entitiesRight = new ArrayList<Entity>();
        entitiesUp = new ArrayList<Entity>();
        entitiesDown = new ArrayList<Entity>();

        for (int i = 0; i < bombSize; ++i) {
            left -= Sprite.SCALED_SIZE;
            right += Sprite.SCALED_SIZE;
            top -= Sprite.SCALED_SIZE;
            bottom += Sprite.SCALED_SIZE;

            if (!leftWalled) {
                leftEntity = Map.getEntity(left, y);
                if (leftEntity instanceof Wall) {
                    leftWalled = true;
                } else {
                    entitiesLeft.add(leftEntity);
                    if (leftEntity instanceof Brick) {
                        leftWalled = true;
                    }
                }
            }

            if (!rightWalled) {
                rightEntity = Map.getEntity(right, y);
                if (rightEntity instanceof Wall) {
                    rightWalled = true;
                } else {
                    entitiesRight.add(rightEntity);
                    if (rightEntity instanceof Brick) {
                        rightWalled = true;
                    }
                }
            }

            if (!topWalled) {
                topEntity = Map.getEntity(x, top);
                if (topEntity instanceof Wall) {
                    topWalled = true;
                } else {
                    entitiesUp.add(topEntity);
                    if (topEntity instanceof Brick) {
                        topWalled = true;
                    }
                }
            }

            if (!bottomWalled) {
                bottomEntity = Map.getEntity(x, bottom);
                if (bottomEntity instanceof Wall) {
                    bottomWalled = true;
                } else {
                    entitiesDown.add(bottomEntity);
                    if (bottomEntity instanceof Brick) {
                        bottomWalled = true;
                    }
                }
            }
        }

    }

    private void addFlame() {
        int a = x / Sprite.SCALED_SIZE;
        int b = y / Sprite.SCALED_SIZE;
        explosion.add(new Flame(a, b, Sprite.explosion_vertical.getFxImage()));
        int i = 0;
        // left
        for (Entity entity : entitiesLeft) {
            a = entity.getX() / Sprite.SCALED_SIZE;
            b = entity.getY() / Sprite.SCALED_SIZE;
            ++i;
            if (i == entitiesLeft.size()) {
                explosion.add(new Flame(a, b, Sprite.explosion_horizontal_left_last.getFxImage()));
            } else {
                explosion.add(new Flame(a, b, Sprite.explosion_horizontal.getFxImage()));
            }
        }

        i = 0;
        // right
        for (Entity entity : entitiesRight) {
            a = entity.getX() / Sprite.SCALED_SIZE;
            b = entity.getY() / Sprite.SCALED_SIZE;
            ++i;
            if (i == entitiesRight.size()) {
                explosion.add(new Flame(a, b, Sprite.explosion_horizontal_right_last.getFxImage()));
            } else {
                explosion.add(new Flame(a, b, Sprite.explosion_horizontal.getFxImage()));
            }
        }

        i = 0;
        // up
        for (Entity entity : entitiesUp) {
            a = entity.getX() / Sprite.SCALED_SIZE;
            b = entity.getY() / Sprite.SCALED_SIZE;
            ++i;
            if (i == entitiesUp.size()) {
                explosion.add(new Flame(a, b, Sprite.explosion_vertical_top_last.getFxImage()));
            } else {
                explosion.add(new Flame(a, b, Sprite.explosion_vertical.getFxImage()));
            }
        }

        i = 0;
        // down
        for (Entity entity : entitiesDown) {
            a = entity.getX() / Sprite.SCALED_SIZE;
            b = entity.getY() / Sprite.SCALED_SIZE;
            ++i;
            if (i == entitiesDown.size()) {
                explosion.add(new Flame(a, b, Sprite.explosion_vertical_top_last.getFxImage()));
            } else {
                explosion.add(new Flame(a, b, Sprite.explosion_vertical.getFxImage()));
            }
        }
    }

    private void bombExplodes() {
        if (centerEntity instanceof Brick || centerEntity instanceof Character) {
            centerEntity.remove();
        }
        for (int i = 0; i < entitiesLeft.size(); ++i) {
            if (entitiesLeft.get(i) instanceof Brick || entitiesLeft.get(i) instanceof Character) {
                entitiesLeft.get(i).remove();
            }
        }
        for (int i = 0; i < entitiesRight.size(); ++i) {
            if (entitiesRight.get(i) instanceof Brick || entitiesRight.get(i) instanceof Character) {
                entitiesRight.get(i).remove();
            }
        }
        for (int i = 0; i < entitiesUp.size(); ++i) {
            if (entitiesUp.get(i) instanceof Brick || entitiesUp.get(i) instanceof Character) {
                entitiesUp.get(i).remove();
            }
        }
        for (int i = 0; i < entitiesDown.size(); ++i) {
            if (entitiesDown.get(i) instanceof Brick || entitiesDown.get(i) instanceof Character) {
                entitiesDown.get(i).remove();
            }
        }
        exploded = true;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void update() {
        if (timeExplode > 0) {
            timeExplode--;
        } else {
            checkEntities();
            addFlame();

        }
    }
}
