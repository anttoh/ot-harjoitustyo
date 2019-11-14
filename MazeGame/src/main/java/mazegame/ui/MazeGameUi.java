package mazegame.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import mazegame.domain.Cell;
import mazegame.domain.Maze;

public class MazeGameUi extends Application {

    @Override
    public void start(Stage stage) {
        int w = 50;
        int h = 50;
        Maze maze = new Maze(w, h);

        Canvas canvas = new Canvas(1000, 1000);
        GraphicsContext marker = canvas.getGraphicsContext2D();
        marker.setFill(Color.WHITE);

        int r = 1000 / Math.max(w, h);

        new AnimationTimer() {

            long edellinen = 0;

            @Override
            public void handle(long nykyhetki) {

                if (nykyhetki - edellinen < 100000000) {

                    return;

                }
                for (int x = 0; x < w; x++) {
                    for (int y = 0; y < h; y++) {
                        marker.fillRect((x * r), (y * r), r / 1.2, r / 1.2);
                        Cell cur = maze.layout(x, y);
                        if (cur.getRight() != cur) {
                            marker.fillRect((x * r), (y * r), r, r / 1.2);
                        }
                        if (cur.getDown() != cur) {
                            marker.fillRect((x * r), (y * r), r / 1.2, r);
                        }

                        if (maze.getCurrentCell() == cur) {
                            marker.setFill(Color.RED);
                            marker.fillOval((x * r), (y * r), r / 1.2, r / 1.2);
                            marker.setFill(Color.WHITE);
                        }
                    }
                }

                this.edellinen = nykyhetki;

            }

        }.start();
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: black");
        layout.setCenter(canvas);

        Scene scene = new Scene(layout);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                maze.moveUp();
            }
            if (e.getCode() == KeyCode.DOWN) {
                maze.moveDown();
            }
            if (e.getCode() == KeyCode.LEFT) {
                maze.moveLeft();
            }
            if (e.getCode() == KeyCode.RIGHT) {
                maze.moveRight();
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
