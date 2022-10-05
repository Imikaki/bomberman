package com.example.bomberman.entities.Items;

import com.example.bomberman.entities.AnimatedEntity;
import javafx.scene.image.Image;

public abstract class Item extends AnimatedEntity {
	public Item(int x, int y, Image img) {
		super(x, y, img);
	}

	@Override
	public void update() {

	}
}
