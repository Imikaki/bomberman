package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.CarriableEntity.Bomb;
import com.example.bomberman.graphics.Sound;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.List;

public class Bomber extends Character {
    public static int explodeRange = 1;
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
    private int SpriteCounter = 0;
    private int SpriteNum = 1;
    private boolean run = true;

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
                run = true;
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
                    if (up() && moveBomb(x, y - Sprite.SCALED_SIZE)) {
                        super.moveUp();
                    }
                    if (run) {
                        if (!checkBomb()) run = false;
                        if (run) moveUp();
                    }
                }
            } else if (event.getCode() == KeyCode.DOWN) {
                moving = true;
                for (int i = 1 - n; i <= n - 1; i++) {
                    if (!canMove(x + i * getSpeed(), y + Sprite.SCALED_SIZE)) {
                        moving = false;
                        break;
                    }
                }
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
                    if (down() && moveBomb(x, y+Sprite.SCALED_SIZE)) {
                        super.moveDown();
                    }
                    if (run) {
                        if (!checkBomb()) run = false;
                        if (run) moveDown();
                    }
                }
            } else if (event.getCode() == KeyCode.LEFT) {
                moving = true;
                for (int i = 1 - n; i <= n - 1; i++) {
                    if (!canMove(x - Sprite.SCALED_SIZE, y + i * getSpeed())) {
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
                    if (left() && moveBomb(x - Sprite.SCALED_SIZE, y)) {
                        super.moveLeft();
                    }
                    if (run) {
                        if (!checkBomb()) run = false;
                        if (run) moveLeft();
                    }
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
                    if (right() && moveBomb(x + Sprite.SCALED_SIZE, y)) {
                        super.moveRight();
                    }
                    if (run) {
                        if (!checkBomb()) run = false;
                        if (run) moveRight();
                    }
                }
            } else if (killed) {
                img = Sprite.player_dead1.getFxImage();
                imageView.setImage(Sprite.player_dead1.getFxImage());
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
            Sound.newBomb.play();
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
        img = Sprite.movingSprite(Sprite.player_dead1,Sprite.player_dead2,Sprite.player_dead3,animate,60).getFxImage();
        super.remove();
    }

    private void countBomb() {
        placedBomb = Map.bombs.size();
        if (Map.bombs.size() == 0) return;
        Map.bombs.removeIf(bomb -> bomb.isExploded());
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

    public int getBombLimit() {
        return this.limitBomb;
    }


    @Override
    public void moveUp() {
        y -= getSpeed();
    }

    @Override
    public void moveDown() {
        y += getSpeed();
    }

    @Override
    public void moveRight() {
        x += getSpeed();
    }
    @Override
    public void moveLeft() {
        x -= getSpeed();
    }

    public boolean up() {
        boolean move = true;
        for (int i = 0; i < Map.bombs.size(); i++) {
            int bomb_x = Map.bombs.get(i).getX();
            int bomb_y = Map.bombs.get(i).getY();
            if (this.y - Sprite.SCALED_SIZE < bomb_y && this.y > bomb_y) {
                if (((this.x > bomb_x && this.x < bomb_x + Sprite.SCALED_SIZE)
                        || (this.x < bomb_x && this.x + Sprite.SCALED_SIZE > bomb_x))) {
                    move = false;
                    break;
                }
            }
        }
        return move;
    }
    public boolean down() {
        boolean move = true;
        for (int i = 0; i < Map.bombs.size(); i++) {
            int bomb_x = Map.bombs.get(i).getX();
            int bomb_y = Map.bombs.get(i).getY();
            if (this.y + Sprite.SCALED_SIZE > bomb_y && this.y < bomb_y) {
                if (((this.x > bomb_x && this.x < bomb_x + Sprite.SCALED_SIZE)
                        || (this.x < bomb_x && this.x + Sprite.SCALED_SIZE > bomb_x))) {
                    move = false;
                    break;
                }
            }
        }
        return move;
    }

    public boolean left() {
        boolean move = true;
        for (int i = 0; i < Map.bombs.size(); i++) {
            int bomb_x = Map.bombs.get(i).getX();
            int bomb_y = Map.bombs.get(i).getY();
            if (this.x > bomb_x && this.x < bomb_x + Sprite.SCALED_SIZE) {
                if (((this.y > bomb_y && this.y < bomb_y + Sprite.SCALED_SIZE)
                        || (this.y < bomb_y && this.y + Sprite.SCALED_SIZE > bomb_y))) {
                    move = false;
                    break;
                }
            }
        }
        return move;
    }

    public boolean right() {
        boolean move = true;
        for (int i = 0; i < Map.bombs.size(); i++) {
            int bomb_x = Map.bombs.get(i).getX();
            int bomb_y = Map.bombs.get(i).getY();
            if (this.x < bomb_x && this.x > bomb_x - Sprite.SCALED_SIZE) {
                if (((this.y > bomb_y && this.y < bomb_y + Sprite.SCALED_SIZE)
                        || (this.y < bomb_y && this.y + Sprite.SCALED_SIZE > bomb_y))) {
                    move = false;
                    break;
                }
            }
        }
        return move;
    }
    public void setBombLimit(int val) {
        this.limitBomb = val;
    }
}
