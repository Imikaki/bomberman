package com.example.bomberman.entities.bomb;


import com.example.bomberman.entities.DynamicEntity;
import com.example.bomberman.system.Direction;
import javafx.scene.image.Image;

public class Bomber extends DynamicEntity {
    private Direction currentDirection = Direction.NONE;

    public int placedBomb = 0;
    public int maxBomb = 1;
    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

}
