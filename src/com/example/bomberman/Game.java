package com.example.bomberman;

import com.example.bomberman.entities.Character.Bomber;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Game extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        Application.launch(Game.class);
    }

    @Override
    public void start(Stage entryStage) {
        stage = entryStage;
        Canvas canvas = new Canvas(920,430);
        Group group = new Group();
        group.getChildren().add(canvas);
        Scene scene = new Scene(group);
        Map map = new Map();
        map.loadNewGame(group,scene);
        stage.setScene(scene);
        stage.show();

    }
}
