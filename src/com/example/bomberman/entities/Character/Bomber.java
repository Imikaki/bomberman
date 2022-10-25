package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.CarriableEntity.Bomb;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.List;

public class Bomber extends Character {
    private Direction currentDirection = Direction.NONE;
    private int placedBomb = 0;
    private int haveBomb = 0;
    private int limitBomb = 3;
    private boolean canPlaceBomb = true; // can only place bomb after the amount of time
    // effect.
    private boolean isSpeedBuff = false;
    private boolean isBombBuff = false;
    private boolean isFlameBuff = false;
    private boolean alive = false;
    public static int explodeRange = 1;
    private int SpriteCounter = 0;
    private int SpriteNum = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    // xu li su kien ban phim
    @Override
    public void update(Scene scene) {
        handleEvent(scene);
        checkAlive();
        countBomb();
    }

    public void handleEvent(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                placeBomb();
            }
            if (event.getCode() == KeyCode.UP) {
                moving = true;
                for (int i = 1 - n; i <= n - 1; i++) {
                    if (!canMove(x + i * getSpeed(), y - Sprite.SCALED_SIZE)) {
                        moving = false;
                        break;
                    }
                }
                if (moving) {
                    if (SpriteNum == 1) {
                        img = Sprite.player_up.getFxImage();
                        imageView.setImage(Sprite.player_up.getFxImage());
                    } else if (SpriteNum == 2) {
                        img = Sprite.player_up_1.getFxImage();
                        imageView.setImage(Sprite.player_up_1.getFxImage());
                    } else if (SpriteNum == 3) {
                        img = Sprite.player_up_2.getFxImage();
                        imageView.setImage(Sprite.player_up_2.getFxImage());
                    }
                    moveUp();
                }
            } else if (event.getCode() == KeyCode.DOWN) {
                moving = true;
                for (int i = 1 - n; i <= n - 1; i++) {
                    if (!canMove(x + i * getSpeed() , y + Sprite.SCALED_SIZE)) {
                        moving = false;
                        break;
                    }
                }
                System.out.println(getSpeed());
                if (moving) {
                    if (SpriteNum == 1) {
                        img = Sprite.player_down.getFxImage();
                        imageView.setImage(Sprite.player_down.getFxImage());
                    } else if (SpriteNum == 2) {
                        img = Sprite.player_down_1.getFxImage();
                        imageView.setImage(Sprite.player_down_1.getFxImage());
                    } else if (SpriteNum == 3) {
                        img = Sprite.player_down_2.getFxImage();
                        imageView.setImage(Sprite.player_down_2.getFxImage());
                    }
                    moveDown();
                }
            } else if (event.getCode() == KeyCode.LEFT) {
                moving = true;
                for (int i = 1 - n; i <= n - 1; i++) {
                    if (!canMove(x - Sprite.SCALED_SIZE, y + i * getSpeed() )) {
                        moving = false;
                        break;
                    }
                }
                if (moving) {
                    if (SpriteNum == 1) {
                        img = Sprite.player_left.getFxImage();
                        imageView.setImage(Sprite.player_left.getFxImage());
                    } else if (SpriteNum == 2) {
                        img = Sprite.player_left_1.getFxImage();
                        imageView.setImage(Sprite.player_left_1.getFxImage());
                    } else if (SpriteNum == 3) {
                        img = Sprite.player_left_2.getFxImage();
                        imageView.setImage(Sprite.player_left_2.getFxImage());
                    }
                    moveLeft();
                }
            } else if (event.getCode() == KeyCode.RIGHT) {
                moving = true;
                for (int i = 1 - n; i <= n - 1; i++) {
                    if (!canMove(x + Sprite.SCALED_SIZE, y + i * getSpeed())) {
                        moving = false;
                        break;
                    }
                }
                if (moving) {
                    if (SpriteNum == 1) {
                        img = Sprite.player_right.getFxImage();
                        imageView.setImage(Sprite.player_right.getFxImage());
                    } else if (SpriteNum == 2) {
                        img = Sprite.player_right_1.getFxImage();
                        imageView.setImage(Sprite.player_right_1.getFxImage());
                    } else if (SpriteNum == 3) {
                        img = Sprite.player_right_2.getFxImage();
                        imageView.setImage(Sprite.player_right_2.getFxImage());
                    }
                    moveRight();
                }
            }
        });
        SpriteCounter++;
        if (SpriteCounter > 15) {
            if (SpriteNum == 1) {
                SpriteNum = 2;
            } else if (SpriteNum == 2) {
                SpriteNum = 3;
            } else if (SpriteNum == 3) {
                SpriteNum = 1;
            }
            SpriteCounter = 0;
        }
        imageView.relocate(x, y);
        moving = false;
    }

    @Override
    public boolean canMove(int x, int y) {
        Entity entity = Map.getEntity(x, y);
        if (entity == null) {
            return true;
        }
        return super.collide(entity);
    }

    public void placeBomb() {
        boolean canPlace = true;
        int _x = x / Sprite.SCALED_SIZE;
        int _y = y / Sprite.SCALED_SIZE;
        if (placedBomb < limitBomb) {
            for (Bomb bomb : Map.bombs) {
                if (bomb.getX() == _x && bomb.getY() == _y) {
                    canPlace = false;
                    break;
                }
            }
            if (canPlace && placedBomb < limitBomb) {
                Bomb bomb = new Bomb(_x, _y, Sprite.bomb.getFxImage());
                Map.bombs.add(bomb);
                placedBomb++;
                System.out.println("Placed bomb " + placedBomb);
            }
        }
    }

    private void checkAlive() {
        Entity entity = Map.getEntity(x, y);
        if (entity instanceof Bomb) {
            remove();
        }
    }

    @Override
    public void remove() {
        img = Sprite.player_dead1.getFxImage();
        super.remove();
    }

    private void countBomb() {
        placedBomb = Map.bombs.size();
        for (Bomb bomb : Map.bombs) {
            if (bomb.isExploded()) {
                Map.bombs.remove(bomb);
            }
        }
    }

    public List<Bomb> getBombs() {
        return Map.bombs;
    }

    public boolean isInPortal() {
        return x == Map.mapPortal.getX() && y == Map.mapPortal.getY();
    }

    public int getPlacedBomb() {
        return placedBomb;
    }

    public void setPlacedBomb(int placedBomb) {
        this.placedBomb = placedBomb;
    }

    public void setBombLimit(int val) {
        this.limitBomb = val;
    }

    public int getBombLimit() {
        return this.limitBomb;
    }
}
