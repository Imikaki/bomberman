package com.example.bomberman;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        Application.launch(Game.class);
    }

    @Override
    public void start(Stage entryStage) {
        stage = entryStage;
        com.example.bomberman.Map newMap = new Map();
        newMap.mapLoading(1);
        stage.setScene(newMap.getScene());
        stage.show();
    }
}
