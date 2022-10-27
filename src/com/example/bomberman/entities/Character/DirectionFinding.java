package com.example.bomberman.entities.Character;

import com.example.bomberman.Map;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.entities.staticEntity.StaticEntity.Grass;
import com.example.bomberman.graphics.Sprite;
import com.example.bomberman.system.Direction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * A* algorithm for enemy to find the shortest path to the player
 */

public class DirectionFinding {
    // LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3
    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};
    public static final int MAX = 1000000;
    public static int[][] bestDirection = new int[Map.getRow()][Map.getCol()];
    public static int[][] visited = new int[Map.getRow()][Map.getCol()];
    private int distanceBomb = 10000;
    private int distanceTarget = 30;

    public static long getDistance(Entity a, Entity b) {
        return (long) ((a.getX() - b.getX()) * (a.getX() - b.getX()) + (long) (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }


    public static void doBfs(int _x, int _y) {
        int x = _x / Sprite.SCALED_SIZE;
        int y = _y / Sprite.SCALED_SIZE;

        bestDirection = new int[Map.getRow()][Map.getCol()];
        visited = new int[Map.getRow()][Map.getCol()];

        for (int i = 0; i < Map.getRow(); i++) {
            for (int j = 0; j < Map.getCol(); j++) {
                bestDirection[i][j] = MAX;
                visited[i][j] = 0;
            }
        }

        bestDirection[x][y] = 0;
        visited[x][y] = 1;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(x * Map.getCol() + y);
        while (!q.isEmpty()) {
            int u = q.peek();
            int ux = u / Map.getCol();
            int uy = u % Map.getCol();
            q.remove();
            for (int i = 0; i < 4; i++) {
                int vx = ux + dx[i];
                int vy = uy + dy[i];
                if (vx >= 0 && vx < Map.getRow() && vy >= 0 && vy < Map.getCol() && visited[vx][vy] == 0
                        && (Map.getStaticEntity(vx * Sprite.SCALED_SIZE, vy * Sprite.SCALED_SIZE) instanceof Grass)) {
                    visited[vx][vy] = 1;
                    bestDirection[vx][vy] = i + 1;
                    q.add(vx * Map.getCol() + vy);
                }
            }
        }
    }

    public static Direction getDirection(Enemies e) {
        int x = e.getX();
        int y = e.getY();
        doBfs(Map.bomberman.getX(), Map.bomberman.getY());
        x = x / Sprite.SCALED_SIZE;
        y = y / Sprite.SCALED_SIZE;
        if (bestDirection[x][y] == 1) {
            return Direction.LEFT;
        } else if (bestDirection[x][y] == 2) {
            return Direction.RIGHT;
        } else if (bestDirection[x][y] == 3) {
            return Direction.UP;
        } else if (bestDirection[x][y] == 4) {
            return Direction.DOWN;
        }
        return Direction.NONE;
    }

    public static Direction getRandomDirection(Enemies e) {
        /**
         * return valid and can move direction
         */
        int x = e.getX();
        int y = e.getY();
        if (x % Sprite.SCALED_SIZE != 0 || y % Sprite.SCALED_SIZE != 0) {
            return e.getCurDirection();
        }

        int _x = x / Sprite.SCALED_SIZE;
        int _y = y / Sprite.SCALED_SIZE;
        List<Direction> movableDirection = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            int vx = x + dx[i];
            int vy = y + dy[i];
            if (vx >= 0 && vx < Map.getRow() && vy >= 0 && vy < Map.getCol() && visited[vx][vy] == 0
                    && (Map.getEntity(vx * Sprite.SCALED_SIZE, vy * Sprite.SCALED_SIZE) instanceof Grass
                    || Map.getEntity(vx * Sprite.SCALED_SIZE, vy * Sprite.SCALED_SIZE) instanceof Enemies)) {
                movableDirection.add(Direction.values()[i]);
            }
        }
        if (movableDirection.size() == 0) {
            return Direction.NONE;
        }
        int i = (int) (Math.random() * (movableDirection.size() - 1));

        return movableDirection.get(i);
    }

    public static boolean hasPath(int x, int y) {
        return bestDirection[x][y] != MAX;
    }
}
