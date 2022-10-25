package com.example.bomberman.entities;

import com.example.bomberman.graphics.Sprite;

import java.util.Stack;

public class Cell {
    public Stack<Entity> stack = new Stack<>();
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX() {
        this.x = x;
    }

    public void setY() {
        this.y = y;
    }

    public void add(Entity entity) {
        stack.push(entity);
    }

}
