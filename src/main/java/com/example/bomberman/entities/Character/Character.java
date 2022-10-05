package com.example.bomberman.entities.Character;

import com.example.bomberman.entities.DynamicEntity;
import javafx.scene.image.Image;

public abstract class Character extends DynamicEntity {
	public Character(int x, int y, Image img) {
		super(x, y, img);
	}

	@Override
	public void update() {}

	public abstract void handleMove();
	public abstract boolean canMove(int x, int y);
}
