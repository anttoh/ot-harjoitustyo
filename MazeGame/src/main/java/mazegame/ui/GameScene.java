package mazegame.ui;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import mazegame.domain.Cell;
import mazegame.domain.Maze;

public class GameScene {

    private MazeGameUi ui;

    public GameScene(MazeGameUi ui) {
        this.ui = ui;
    }

    public Scene createAndGet(int width, int height) {
        Maze maze = new Maze(width, height);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        int size = (int) (0.90 * screenBounds.getHeight());

        Canvas canvas = new Canvas(size, size); 

        GraphicsContext marker = canvas.getGraphicsContext2D();
        marker.setFill(Color.WHITE);

        Text timer = new Text();
        timer.setFill(Color.WHITE);
        timer.setFont(Font.font(null, FontWeight.BOLD, 36));

        AtomicInteger time = new AtomicInteger();
        timer.setText("Timer: " + time.toString());

        Button leave = new Button("exit game");
        leave.setOnAction(e -> {
            ui.exitGame();
        });

        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: black;");
        layout.setCenter(canvas);
        layout.setTop(timer);
        layout.setBottom(leave);

        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());

        int r = size / Math.max(width, height);

        new AnimationTimer() {
            long prevMoment1 = 0;
            long prevMoment2 = 0;

            @Override
            public void handle(long curMoment) {
                if (maze.reachedGoal()) {
                    stop();
                    ui.exitGame();
                }

                if (curMoment - prevMoment1 > 1000000000) {
                    timer.setText("Timer: " + time.incrementAndGet());
                    this.prevMoment1 = curMoment;
                }

                if (curMoment - prevMoment2 > 1000000) {
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
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
                    this.prevMoment2 = curMoment;
                }

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
