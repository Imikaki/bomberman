package com.example.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Game extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(Map.height, Map.width);
        Map map = new Map();

        Group group = new Group();
        group.getChildren().add(canvas);
        map.renderMap(group);

        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
