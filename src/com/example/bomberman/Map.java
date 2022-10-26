package com.example.bomberman;

import com.example.bomberman.entities.Character.*;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.CarriableEntity.Bomb;
import com.example.bomberman.entities.staticEntity.CarriableEntity.BombItem;
import com.example.bomberman.entities.staticEntity.CarriableEntity.FlameItem;
import com.example.bomberman.entities.staticEntity.CarriableEntity.SpeedItem;
import com.example.bomberman.entities.staticEntity.StaticEntity.*;
import com.example.bomberman.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class Map {
    public static Bomber bomberman;
    public static boolean isWin = false;
    public static boolean ending = false;
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

        while ((line = sc.readLine()) != null) {
            ++i;
            for (int j = 0; j < line.length(); ++j) {
                if (line.charAt(j) == '#') {
                    staticEntities.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else {
                    staticEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                }
            }
            for (int j = 0; j < line.length(); ++j) {
                switch (line.charAt(j)) {
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
        checkWin();
        if (ending) {
            // TODO: add victory scene
        }
        if (isWin) {
            level++;
            try {
                mapLoading(level);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update(scene);
        }
        entities.removeIf(entity -> entity.isRemoved);
        for (int i = 0; i < items.size(); i++) {
            items.get(i).update(scene);
        }
        items.removeIf(item -> item.isRemoved);
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(scene);
        }
        enemies.removeIf(enemies -> enemies.isRemoved);
        bomberman.update(scene);
        if (bombs.size() != 0) {
            for (int i = 0; i < bombs.size(); i++) {
                bombs.get(i).update(scene);
            }
            bombs.removeIf(bomb -> bomb.isRemoved);
        }
        if (flames.size() != 0) {
            for (int i = 0; i < flames.size(); i++) {
                flames.get(i).update(scene);
            }
            flames.removeIf(flame -> flame.isRemoved);
        }
        if (bomberman.isAlive() == false) {
            ending = true;
            bombs.forEach(bomb -> bomb.breakEntity());
            flames.forEach(flame -> flame.breakEntity());
        }
        bomberKill();
        enemyKill();
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
                render(group);
                update(scene);
                remove();
                delta--;
                updates++;
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

    public static boolean collide(Entity e1, Entity e2) {
        Rectangle r1 = new Rectangle(e1.getX(), e1.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        Rectangle r2 = new Rectangle(e2.getX(), e2.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        return r1.intersects(r2);
    }

    public static void reset() {
        staticEntities = new ArrayList<>();
        entities = new ArrayList<>();
        items = new ArrayList<>();
        enemies = new ArrayList<>();
        bombs = new ArrayList<>();
        flames = new ArrayList<>();
        isWin = false;
        ending = false;
        mapPortal = null;
    }

    public static void remove() {
        entities.removeIf(e -> e.isRemoved);
        items.removeIf(e -> e.isRemoved);
        enemies.removeIf(e -> e.isRemoved);
        bombs.removeIf(e -> e.isRemoved);
        flames.removeIf(e -> e.isRemoved);
    }

    public static void bomberKill() {
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
            System.out.println("Game Over");
        }
    }

    public static void enemyKill() {
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

    public static void checkWin() {
        if (isWin) {
            return;
        } else {
            isWin = enemies.size() == 0 && collide(bomberman, mapPortal);
            if (isWin) {
                System.out.println("you Win");
            }
        }
    }
}
