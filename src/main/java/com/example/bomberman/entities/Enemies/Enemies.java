package com.example.bomberman.entities.Enemies;

import com.example.bomberman.entities.DynamicEntity;
import javafx.scene.image.Image;

public abstract class Enemies extends DynamicEntity {
	public Enemies(int x, int y, Image img) {
		super(x, y, img);
	}

	@Override
	public void update() {}

	public abstract void handleMove();
	public abstract boolean canMove(int x, int y);
}
