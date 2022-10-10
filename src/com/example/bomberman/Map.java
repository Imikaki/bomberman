package com.example.bomberman;

import com.example.bomberman.entities.Character.*;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.Object.Brick;
import com.example.bomberman.entities.Object.Grass;
import com.example.bomberman.entities.Object.Portal;
import com.example.bomberman.entities.Object.Wall;
import com.example.bomberman.entities.bomb.Bomb;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.KeyManager;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Map {
    public static Bomber bomberman;
    public static boolean isWin = false;
    public static int level = 1;
    private static List<Entity> staticEntities = new ArrayList<Entity>();
    private static List<Entity> entities = new ArrayList<Entity>();
    Stage windows;
    private int row;
    private int col;
    private KeyManager keys;
    private AnimationTimer timer;

    private GraphicsContext gc;
    private Canvas cv;
    private Group root;
    private Scene scene;
    private Stage mainStage;

    public Map() {
        loadNewGame();
        initializeMap();
        createKeyListener();
    }

    public void initializeMap() {
        cv = new Canvas(Sprite.SCALED_SIZE * getCol(), Sprite.SCALED_SIZE * getRow());
        gc = cv.getGraphicsContext2D();
        root = new Group();
        root.getChildren().add(cv);
        scene = new Scene(root, Sprite.SCALED_SIZE * Sprite.SCALED_SIZE * getCol(), Sprite.SCALED_SIZE * getRow());
        mainStage = new Stage();
        mainStage.setScene(scene);
    }

    public void mapLoading(int levels) {
        String mapPath = "res/levels/Level2.txt";
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
                        bomberman = new Bomber(i, j, Sprite.player_right.getFxImage(), keys, this);
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        staticEntities.add(object);
                    }
                    case '1': {
                        enemy = new Balloom(i, j, Sprite.balloom_left1.getFxImage());
                        entities.add(enemy);
                        break;
                    }
                    case '2': {
                        enemy = new Oneal(i, j, Sprite.oneal_left1.getFxImage());
                        entities.add(enemy);
                        break;
                    }
                    case '3': {
                        enemy = new Doll(i, j, Sprite.doll_left1.getFxImage());
                        entities.add(enemy);
                        break;
                    }
                    case '4': {
                        enemy = new Minvo(i, j, Sprite.minvo_left1.getFxImage());
                        entities.add(enemy);
                        break;
                    }
                    case '5': {
                        enemy = new Kondoria(i, j, Sprite.kondoria_left1.getFxImage());
                        entities.add(enemy);
                        break;
                    }
                    case 'x': {
                        object = new Portal(i, j, Sprite.portal.getFxImage());
                        staticEntities.add(object);
                        break;
                    }
                }
            }
        }

        for (Bomb bomb : bomberman.getBombs()) {
            entities.add(bomb);
        }
    }

    public void update() {
        bomberman.update();
        for (Entity e : entities) {
            e.update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, 1280, 720);
        for (Entity e : staticEntities) {
            e.render(gc);
        }
        for (Entity e : entities) {
            e.render(gc);
        }
        bomberman.render(gc);
    }

    private void createGameLoop() {
        timer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (now - lastTick > 1000000000 / 60) {
                    update();
                    render();
                    lastTick = now;
                    if (!bomberman.isAlive()) {
                        mainStage.close();
                        timer.stop();
                    }

                }
            }
        };
        timer.start();
    }

    private void createKeyListener() {
        scene.setOnKeyPressed(event -> keys.pressed(event));
        scene.setOnKeyReleased(event -> keys.released(event));
    }

    public void loadNewGame() {
        entities = new ArrayList<>();
        staticEntities = new ArrayList<>();
        mapLoading(1);
        createGameLoop();
    }

    public Scene getScene() {
        return scene;
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
