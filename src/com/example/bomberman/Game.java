package com.example.bomberman;

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
import com.example.bomberman.graphics.Sound;

import static javafx.application.Platform.exit;

public class Game extends Application {
    public static final int width = 1000;
    public static final int height = 420;
    private static Button play = new Button();
    private static Button exit = new Button();
    private static ImageView background = new ImageView(new Image("Lobby/BG.png", width, height, false, false));
    private static Stage stage;

    public static void main(String[] args) {
        Application.launch(Game.class);
    }

    @Override
    public void start(Stage entryStage) {
        stage = entryStage;

        Sound.menu.loop();

        // create background
        loadBackground();
        Group bg = new Group();
        bg.getChildren().addAll(background, play, exit);
        Scene bg_scene = new Scene(bg);
        stage.setScene(bg_scene);
        // create game
        Canvas canvas = new Canvas(width, height);
        VBox root = new VBox();

        Group group = new Group();
        group.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.requestFocus();
        canvas.setFocusTraversable(true);

        Scene scene = new Scene(group);
        Map.loadNewGame(group, scene);

        root.getChildren().addAll(canvas);

        play.setOnAction(e -> {
            Sound.menuSelect.play();
            Sound.menu.stop();
            Sound.playGame.loop();
            Sound.attack.play();
            stage.setScene(scene);
        });

        exit.setOnAction(e -> {
            Sound.menuSelect.play();
            exit();
        });

        stage.show();

    }

    public void loadBackground() {
        // create imageView play and exit
        ImageView imgPlay = new ImageView(new Image("Lobby/Play.png",280, 80,false, false));
        ImageView imgExit = new ImageView(new Image("Lobby/Exit.png", 280, 80,false, false));

        // set button play
        play.setGraphic(imgPlay);
        play.setLayoutX(350);
        play.setLayoutY(200);

        //set button exit
        exit.setGraphic(imgExit);
        exit.setLayoutX(350);
        exit.setLayoutY(310);
    }
}
