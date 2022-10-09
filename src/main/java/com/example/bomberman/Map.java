package com.example.bomberman;

import com.example.bomberman.entities.Enemies.Enemies;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.Items.Brick;
import com.example.bomberman.entities.Items.Grass;
import com.example.bomberman.entities.Items.Portal;
import com.example.bomberman.entities.Items.Wall;
import com.example.bomberman.entities.bomb.Bomber;
import com.example.bomberman.graphics.LoadSprite;
import javafx.scene.Group;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
	public static int height;
	public static int width;
	public static List<List<Entity>> listEntity = new ArrayList<>();
	public void mapLoading(int stage) {
		try {
			FileInputStream fileInputStream = new FileInputStream("E:/bomberman/res/levels/Level1.txt");
			Scanner sc = new Scanner(fileInputStream);
			int lv = sc.nextInt();
			height = sc.nextInt();
			width = sc.nextInt();
			sc.nextLine();
			for (int i = 0; i < height; i++) {
				String temp = sc.nextLine();
				List<Entity> tempList = new ArrayList<>();
				for (int j = 0; j < width; j++) {
					switch (temp.charAt(j)) {
						case '#':
							tempList.add(new Wall(j, i, LoadSprite.wall.getFxImage()));
							break;
						case '*':
							tempList.add(new Brick(j, i, LoadSprite.brick.getFxImage()));
							break;
						case 'x':
							tempList.add(new Portal(j, i, LoadSprite.portal.getFxImage()));
							break;
						default:
							tempList.add(new Grass(j, i, LoadSprite.grass.getFxImage()));
					}
				}
				listEntity.add(tempList);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void renderMap(Group group) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (!(listEntity.get(i).get(j) instanceof Enemies) || !(listEntity.get(i).get(j) instanceof Bomber)) {
					group.getChildren().add(listEntity.get(i).get(j).getImgView());
				} else {

				}
			}
		}
	}
}
