package com.example.bomberman.entities.Object;

import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.HitBox;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;
import javafx.scene.image.Image;

public class Flame extends Entity {
    public static final int explosionZone = 2;
    private Direction direction;
    private boolean exploded = false;
    private int state = 0; // explosion state 0 brick 1 middle 2 end


    public Flame(int x, int y, Image img) {

        super(x, y, img);
        direction = Direction.NONE;
        exploded = false;
        state = 0;
        borderBox = new HitBox(x + explosionZone, y + explosionZone,
                Sprite.SCALED_SIZE - 2 * explosionZone, Sprite.SCALED_SIZE - 2 * explosionZone);
    }

    @Override
    public void update() {
        
    }
}
