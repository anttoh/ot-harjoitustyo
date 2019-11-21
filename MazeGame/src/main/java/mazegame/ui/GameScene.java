package mazegame.ui;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import mazegame.domain.Cell;
import mazegame.domain.Maze;

public class GameScene {

    private int width;
    private int height;
    private int canvasSize;

    public GameScene(int width, int height) {
        this.width = width;
        this.height = height;
        this.canvasSize = 950;
    }

    public Scene get() {
        int w = this.width;
        int h = this.height;
        Maze maze = new Maze(w, h);

        int S = this.canvasSize;
        Canvas canvas = new Canvas(S, S);

        GraphicsContext marker = canvas.getGraphicsContext2D();
        marker.setFill(Color.WHITE);

        Text timer = new Text();
        timer.setFill(Color.WHITE);
        timer.setFont(Font.font(null, FontWeight.BOLD, 36));

        AtomicInteger time = new AtomicInteger();
        timer.setText("Timer: " + time.toString());

        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: black;");
        layout.setCenter(canvas);
        layout.setTop(timer);

        Scene scene = new Scene(layout);

        int r = S / Math.max(w, h);

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
                        if (maze.getGoal() == cur) {
                            marker.setFill(Color.GREEN);
                            marker.fillRect((x * r), (y * r), r / 1.2, r / 1.2);
                            marker.setFill(Color.WHITE);
                        }

                        if (maze.getCurrentCell() == cur) {
                            marker.setFill(Color.RED);
                            marker.fillOval((x * r), (y * r), r / 1.2, r / 1.2);
                            marker.setFill(Color.WHITE);
                        }

                    }
                }
                if (maze.reachedGoal()) {
                    stop();
                }
                this.edellinen = nykyhetki;

            }
        }.start();

        new AnimationTimer() {
            long edellinen = 0;

            @Override
            public void handle(long nykyhetki) {

                if (maze.reachedGoal()) {
                    stop();
                }

                if (nykyhetki - edellinen < 1000000000) {
                    return;
                }

                timer.setText("Timer: " + time.incrementAndGet());
                this.edellinen = nykyhetki;
            }
        }.start();

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

        return scene;
    }
}
