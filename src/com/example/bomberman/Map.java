package com.example.bomberman;

import com.example.bomberman.entities.Character.*;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.StaticEntity.Brick;
import com.example.bomberman.entities.staticEntity.StaticEntity.Grass;
import com.example.bomberman.entities.staticEntity.StaticEntity.Portal;
import com.example.bomberman.entities.staticEntity.StaticEntity.Wall;
import com.example.bomberman.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Map {
    public static Bomber bomberman;
    public static boolean isWin = false;
    public static int level = 1;
    private final List<Entity> staticEntities = new ArrayList<Entity>();
    private final List<Entity> entities = new ArrayList<Entity>();
    private int row;
    private int col;
    private AnimationTimer timer;

    public void mapLoading(int levels) {
        String mapPath = "res/levels/Level" + levels + ".txt";
        File map = new File(mapPath);
        Scanner sc = null;
        try {
            sc = new Scanner(map);
        } catch (FileNotFoundException e) {
            System.out.println("File reading error: Error loading map");
            e.printStackTrace();
        }

        level = sc.nextInt();
        row = sc.nextInt();
        col = sc.nextInt();

        Entity object;
        Entity enemy;

        sc.nextLine();

        for (int i = 0; i < row; ++i) {
            String line = sc.nextLine();
            for (int j = 0; j < col; ++j) {
                char t = line.charAt(j);
                switch (t) {
                    case '#': {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                        staticEntities.add(object);
                        break;
                    }
                    case '*': {
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                        staticEntities.add(object);
                        break;
                    }
                    case 'p': {
                        bomberman = new Bomber(i, j, Sprite.player_right.getFxImage(), this);
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        staticEntities.add(object);
                    }
                    case '1': {
                        enemy = new Balloom(i, j, Sprite.balloom_left1.getFxImage());
                        entities.add(enemy);
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        staticEntities.add(object);
                        break;
                    }
                    case '2': {
                        enemy = new Oneal(i, j, Sprite.oneal_left1.getFxImage());
                        entities.add(enemy);
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        staticEntities.add(object);
                        break;
                    }
                    case '3': {
                        enemy = new Doll(i, j, Sprite.doll_left1.getFxImage());
                        entities.add(enemy);
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        staticEntities.add(object);
                        break;
                    }
                    case '4': {
                        enemy = new Minvo(i, j, Sprite.minvo_left1.getFxImage());
                        entities.add(enemy);
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        staticEntities.add(object);
                        break;
                    }
                    case '5': {
                        enemy = new Kondoria(i, j, Sprite.kondoria_left1.getFxImage());
                        entities.add(enemy);
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        staticEntities.add(object);
                        break;
                    }
                    case 'x': {
                        object = new Portal(i, j, Sprite.portal.getFxImage());
                        staticEntities.add(object);
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                        staticEntities.add(object);
                        break;
                    }
                    default: {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        staticEntities.add(object);
                        break;
                    }
                }
            }
        }
        entities.add(bomberman);
    }

    public void update(Scene scene) {
        for (Entity e : entities) {
            e.update(scene);
        }
    }

    public void render(Group group) {
        for (Entity e : staticEntities) {
            group.getChildren().add(e.getImageView());
        }
        for (Entity e : entities) {
            group.getChildren().add(e.getImageView());
        }
    }

    public void createGameLoop(Group group, Scene scene) {
        render(group);
        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                bomberman.update(scene);
            }
        };
        timer.start();
    }

   /* private void createKeyListener() {
        scene.setOnKeyPressed(event -> keys.pressed(event));
        scene.setOnKeyReleased(event -> keys.released(event));
    }*/

    public void loadNewGame(Group group, Scene scene) {

        mapLoading(1);
        createGameLoop(group, scene);
    }

    public List<Entity> getStaticEntities() {
        return staticEntities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }
}
