package com.example.bomberman.entities.Character;

import com.example.bomberman.entities.DynamicEntity;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.system.Direction;
import javafx.scene.image.Image;

public abstract class Character extends DynamicEntity {
	protected boolean alive = true;
	protected boolean moving = false;
	protected Direction direction;
	public Character(int x, int y, Image img) {
		super(x, y, img);
		alive = true;
		moving = false;
	}

	@Override
	public void update() {}

	public abstract void handleMove();
	public abstract boolean canMove(int x, int y);

	public boolean isAlive() {
		return alive;
	}

	public boolean isMoving() {
		return moving;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public abstract boolean isColliding(Entity other);

	@Override
	public int getX() {
		return super.getX();
	}

	public int getY() {
		return super.getY();
	}
}
