package com.example.bomberman;

import com.example.bomberman.entities.Items.FlameItem;
import com.example.bomberman.entities.Object.Brick;
import com.example.bomberman.entities.Object.Grass;
import com.example.bomberman.entities.Object.Portal;
import com.example.bomberman.entities.Object.Wall;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.Character.*;
import com.example.bomberman.graphics.*;
import com.example.bomberman.system.KeyManager;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Map {
    private static List<Entity> staticEntities = new ArrayList<Entity>();
    private static List<Entity> entities = new ArrayList<Entity>();

    private KeyManager keys;
    public static Bomber bomberman;
    public static boolean isWin = false;
    Stage windows;
    public static int level = 1;
    public void mapLoading(int levels) {
        String mapPath = "./res/levels/Level" + levels + ".txt";
        File map = new File(mapPath);
        Scanner sc = null;
        try {
            sc = new Scanner(map);
        } catch(FileNotFoundException e) {
            System.out.println("File reading error: Error loading map");
            e.printStackTrace();
        }

        int level = sc.nextInt();
        int row = sc.nextInt();
        int col = sc.nextInt();

        Entity object;
        Entity enemy;

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
                    case 'x': {
                        object = new Portal(i, j, Sprite.portal.getFxImage());
                        staticEntities.add(object);
                        break;
                    }
                }
            }
        }
    }
}
