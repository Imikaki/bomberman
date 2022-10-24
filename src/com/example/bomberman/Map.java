package com.example.bomberman;

import com.example.bomberman.entities.Character.*;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.CarriableEntity.*;
import com.example.bomberman.entities.staticEntity.StaticEntity.*;
import com.example.bomberman.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Map {
    public static Bomber bomberman;
    public static boolean isWin = false;
    public static int level = 1;
    public static Entity mapPortal;
    public static List<Entity> staticEntities = new ArrayList<Entity>();
    public static List<Entity> entities = new ArrayList<Entity>();
    public static List<Entity> items = new ArrayList<Entity>();
    public static List<Enemies> enemies = new ArrayList<Enemies>();
    public static List<Flame> flames = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<Bomb>();
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

        reset();

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
                        mapPortal = new Portal(j, i, Sprite.portal.getFxImage());
                        staticEntities.add(mapPortal);
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
        for (Entity e : bombs) {
            e.update(scene);
        }
        for (Entity e : flames) {
            e.update(scene);
        }
        if (bomberman.isAlive() == false) {
            isWin = true;
            timer.stop();
            bombs.forEach(bomb -> bomb.breakEntity());
            flames.forEach(flame -> flame.breakEntity());
        }
        if (bomberman.isInPortal() == true) {
            isWin = true;
            timer.stop();
        }
    }

    public static void render(Group group) {
        group.getChildren().clear();
        staticEntities.forEach(e -> e.render(group));
        items.forEach(e -> e.render(group));
        entities.forEach(e -> e.render(group));
        enemies.forEach(e -> e.render(group));
        bomberman.render(group);
        bombs.forEach(e -> e.render(group));
        flames.forEach(e -> e.render(group));
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
                    render(group);
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
        for (Entity e : items) {
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

    public static boolean collide(Entity e1, Entity e2) {
        Rectangle r1 = new Rectangle(e1.getX(), e1.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        Rectangle r2 = new Rectangle(e2.getX(), e2.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        return r1.intersects(r2);
    }
    public void bomberKill() {
        if (bomberman.isAlive() == false) return;
        bombs.forEach(bomb -> {
            if (bomb.isExploded() && collide(bomberman, bomb)) {
                bomberman.kill();
            }
        });
        flames.forEach(flame -> {
            if (collide(bomberman, flame)) {
                bomberman.kill();
            }
        });
        enemies.forEach(enemy -> {
            if (collide(bomberman, enemy)) {
                bomberman.kill();
            }
        });
        if (bomberman.isAlive() == false) {
            timer.stop();
            System.out.println("Game Over");
        }
    }

    public void enemyKill() {
        enemies.forEach(enemy -> {
            bombs.forEach(bomb -> {
                if (bomb.isExploded() && collide(enemy, bomb)) {
                    enemy.kill();
                }
            });
            flames.forEach(flame -> {
                if (collide(enemy, flame)) {
                    enemy.kill();
                }
            });
        });
    }

    public static void reset() {
        staticEntities = new ArrayList<>();
        entities = new ArrayList<>();
        items = new ArrayList<>();
        enemies = new ArrayList<>();
        bombs = new ArrayList<>();
        flames = new ArrayList<>();
    }
}
