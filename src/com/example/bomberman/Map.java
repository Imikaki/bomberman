package com.example.bomberman;

import com.example.bomberman.entities.Character.*;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.CarriableEntity.BombItem;
import com.example.bomberman.entities.staticEntity.CarriableEntity.FlameItem;
import com.example.bomberman.entities.staticEntity.CarriableEntity.Item;
import com.example.bomberman.entities.staticEntity.CarriableEntity.SpeedItem;
import com.example.bomberman.entities.staticEntity.StaticEntity.Brick;
import com.example.bomberman.entities.staticEntity.StaticEntity.Grass;
import com.example.bomberman.entities.staticEntity.StaticEntity.Portal;
import com.example.bomberman.entities.staticEntity.StaticEntity.Wall;
import com.example.bomberman.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Map {
    public static Bomber bomberman;
    public static boolean isWin = false;
    public static int level = 1;
    private static final List<Entity> staticEntities = new ArrayList<Entity>();
    private static final List<Entity> entities = new ArrayList<Entity>();
    private static final List<Entity> items = new ArrayList<Entity>();
    private static final List<Entity> enemies = new ArrayList<Entity>();
    private static Portal portal;
    private static int row;
    private static int col;
    private static AnimationTimer timer;

    public static void mapLoading(int levels) throws IOException {
        String mapPath = "res/levels/Level" + levels + ".txt";
        File map = new File(mapPath);
        BufferedReader sc = new BufferedReader(new InputStreamReader(new FileInputStream(mapPath)));

        String line;
        line = sc.readLine();
        String[] args = line.split(" ");
        level = Integer.parseInt(args[0]);
        row = Integer.parseInt(args[1]);
        col = Integer.parseInt(args[2]);

        int i = -1;

        while((line = sc.readLine()) != null) {
            ++i;
            for (int j = 0; j < line.length(); ++j) {
                if (line.charAt(j) == '#') {
                    staticEntities.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else {
                    staticEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                }
            }
            for (int j = 0; j < line.length(); ++j) {
                switch(line.charAt(j)) {
                    case '*': //brick
                        entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'x': //portal
                        portal = new Portal(j, i, Sprite.portal.getFxImage());
                        staticEntities.add(portal);
                        entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'b': //bombItem
                        items.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'f': //flameItem
                        items.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 's': //speedItem
                        items.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'p': //player
                        bomberman = new Bomber(j, i, Sprite.player_right.getFxImage());
                        entities.add(bomberman);
                        break;
                    case '1': //balloon
                        enemies.add(new Balloom(j, i, Sprite.balloom_left1.getFxImage()));
                        break;
                    case '2': //oneal
                        enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                        break;
                    case '3': //kondoria
                        enemies.add(new Kondoria(j, i, Sprite.kondoria_left1.getFxImage()));
                        break;
                    case '4': //doll
                        enemies.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                        break;
                    case '5': //minvo
                        enemies.add(new Minvo(j, i, Sprite.minvo_left1.getFxImage()));
                        break;
                }
            }
        }
        sc.close();
    }

    public static void update(Scene scene) {
        for (Entity e : entities) {
            e.update(scene);
        }
        for (Entity e : items) {
            e.update(scene);
        }
        for (Entity e : enemies) {
            e.update(scene);
        }
        bomberman.update(scene);
        portal.update(scene);
    }

    public static void render(Group group) {
        group.getChildren().clear();
        staticEntities.forEach(e -> group.getChildren().add(e.getImageView()));
        entities.forEach(e -> group.getChildren().add(e.getImageView()));
        items.forEach(e -> group.getChildren().add(e.getImageView()));
        enemies.forEach(e -> group.getChildren().add(e.getImageView()));
        bomberman.getBombs().forEach(e -> group.getChildren().add(e.getImageView()));
    }

    public static void createGameLoop(Group group, Scene scene) {
        render(group);
        timer = new AnimationTimer() {
            final double ns = 1000000000.0 / 60;
            long lastTime = System.nanoTime();
            double delta = 0;
            int updates = 0;
            @Override
            public void handle(long now) {
                delta += (now - lastTime) / ns;
                lastTime = now;
                while (delta >= 1) {
                    update(scene);
                    delta--;
                    updates++;
                }
            }
        };
        timer.start();
    }

   /* private void createKeyListener() {
        scene.setOnKeyPressed(event -> keys.pressed(event));
        scene.setOnKeyReleased(event -> keys.released(event));
    }*/

    public static void loadNewGame(Group group, Scene scene) {

        try {
            mapLoading(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        createGameLoop(group, scene);
    }

    public static List<Entity> getStaticEntities() {
        return staticEntities;
    }

    public static List<Entity> getEntities() {
        return entities;
    }

    public static void addEntity(Entity e) {
        entities.add(e);
    }

    public static int getCol() {
        return col;
    }

    public static int getRow() {
        return row;
    }

    public static Entity getEntity(int x, int y) {
        for (Entity e : enemies) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        for (Entity e : entities) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        for (Entity e : staticEntities) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        return null;
    }

    public static void bombExplode(List<Entity> explosion) {

    }
}
