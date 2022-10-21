package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.CarriableEntity.Bomb;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import com.example.bomberman.system.KeyManager;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {
    KeyManager input = new KeyManager();
    private Direction currentDirection = Direction.NONE;
    private int placedBomb = 0;
    private int haveBomb = 0;
    private int limitBomb = 3;
    private boolean canPlaceBomb = true; // can only place bomb after the amount of time
    private List<Bomb> bombs = new ArrayList<>();
    // effect.
    private boolean isSpeedBuff = false;
    private boolean isBombBuff = false;
    private boolean isFlameBuff = false;
    private boolean alive = false;

    private int SpriteCounter = 0;
    private int SpriteNum = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    // xu li su kien ban phim
    public void update(Scene scene) {
        if (direction != Direction.NONE) {
            handleEvent(scene);
        }

    }

    public void handleEvent(Scene scene) {
        int _x = this.x;
        int _y = this.y;
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                placeBomb();
            }
            if (event.getCode() == KeyCode.UP) {
                if (canMove(x, y - getSpeed())) {
                    direction = Direction.UP;
                    moving = true;
                    if(SpriteNum == 1) {
                        imageView.setImage(Sprite.player_up.getFxImage());
                    }
                    else if(SpriteNum == 2) {
                        imageView.setImage(Sprite.player_up_1.getFxImage());
                    }
                    else if(SpriteNum == 3) {
                        imageView.setImage(Sprite.player_up_2.getFxImage());
                    }
                    moveUp();
                }
            } else if (event.getCode() == KeyCode.DOWN) {
                if (canMove(x, y + getSpeed())) {
                    direction = Direction.DOWN;
                    moving = true;
                    if(SpriteNum == 1) {
                        imageView.setImage(Sprite.player_down.getFxImage());
                    }
                    else if(SpriteNum == 2) {
                        imageView.setImage(Sprite.player_down_1.getFxImage());
                    }
                    else if(SpriteNum == 3) {
                        imageView.setImage(Sprite.player_down_2.getFxImage());
                    }
                    moveDown();
                }
            } else if (event.getCode() == KeyCode.LEFT) {
                if (canMove(x - getSpeed(), y)) {
                    direction = Direction.LEFT;
                    moving = true;
                    if(SpriteNum == 1) {
                        imageView.setImage(Sprite.player_left.getFxImage());
                    }
                    else if(SpriteNum == 2) {
                        imageView.setImage(Sprite.player_left_1.getFxImage());
                    }
                    else if(SpriteNum == 3) {
                        imageView.setImage(Sprite.player_left_2.getFxImage());
                    }
                    moveLeft();
                }
            } else if (event.getCode() == KeyCode.RIGHT) {
                if (canMove(x + getSpeed(), y)) {
                    direction = Direction.RIGHT;
                    moving = true;
                    if(SpriteNum == 1) {
                        imageView.setImage(Sprite.player_right.getFxImage());
                    }
                    else if(SpriteNum == 2) {
                        imageView.setImage(Sprite.player_right_1.getFxImage());
                    }
                    else if(SpriteNum == 3) {
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
            }
            else if (SpriteNum == 2) {
                SpriteNum = 3;
            }
            else if (SpriteNum == 3) {
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
        if (entity instanceof Bomb) {
            return false;
        }
        return super.collide(entity);
    }

    public void placeBomb() {
        boolean canPlace = true;
        int _x = x / Sprite.SCALED_SIZE;
        int _y = y / Sprite.SCALED_SIZE;
        if (placedBomb < limitBomb) {
            for (Bomb bomb : bombs) {
                if (bomb.getX() == _x && bomb.getY() == _y) {
                    canPlace = false;
                    break;
                }
            }
            if (canPlace && placedBomb < limitBomb) {
                Bomb bomb = new Bomb(_x, _y, Sprite.bomb.getFxImage());
                bombs.add(bomb);
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
        placedBomb = 0;
        for (Bomb bomb : bombs) {
            if (bomb.isExploded()) {
                bombs.remove(bomb);
            } else {
                placedBomb++;
            }
        }
    }

    public List<Bomb> getBombs() {
        return bombs;
    }
}
