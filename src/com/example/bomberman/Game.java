package com.example.bomberman;

import com.example.bomberman.entities.Character.Bomber;
import com.example.bomberman.entities.Entity;
import com.example.bomberman.graphics.Sprite;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Platform.exit;

public class Game extends Application {
    private static final int width = 1000;
    private static final int height = 600;
    private static Button play = new Button();
    private static Button exit = new Button();
    private static ImageView background = new ImageView(new Image("Lobby/BG.png",width, height, false, false));
    private static Stage stage;

    public static void main(String[] args) {
        Application.launch(Game.class);
    }

    @Override
    public void start(Stage entryStage) {
        stage = entryStage;

        // create background
        loadBackground();
        Group bg = new Group();
        bg.getChildren().addAll(background, play, exit);
        Scene bg_scene = new Scene(bg);
        loadBackground();
        stage.setScene(bg_scene);
        // create game
        Canvas canvas = new Canvas(width,height);
        VBox root = new VBox();

        Group group = new Group();
        group.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.requestFocus();
        canvas.setFocusTraversable(true);

        Scene scene = new Scene(group);
        Map.loadNewGame(group,scene);

        root.getChildren().addAll(canvas);

        play.setOnAction(e -> stage.setScene(scene));

        exit.setOnAction(e -> exit());

        stage.show();

    }

    public void loadBackground() {
        // create imageView play and exit
        ImageView imgPlay = new ImageView(new Image("Lobby/Play.png"));
        ImageView imgExit = new ImageView(new Image("Lobby/Exit.png"));

        // set button play
        play.setGraphic(imgPlay);
        play.setLayoutX(270);
        play.setLayoutY(280);

        //set button exit
        exit.setGraphic(imgExit);
        exit.setLayoutX(270);
        exit.setLayoutY(450);
    }
}
