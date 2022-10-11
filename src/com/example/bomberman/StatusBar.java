package com.example.bomberman;

public class StatusBar {
    private int time;
    private int score;
    private int level;
    private int life;

    public StatusBar(int time, int score, int level, int life) {
        this.time = time;
        this.score = score;
        this.level = level;
        this.life = life;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
