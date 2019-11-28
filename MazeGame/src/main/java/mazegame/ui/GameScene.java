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

import mazegame.domain.MazeGameService;

public class GameScene {

    private MazeGameUi ui;
    private MazeGameService service;

    public GameScene(MazeGameUi ui) {
        this.ui = ui;
        this.service = ui.getService();
    }

    public Scene createAndGet() {

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
            this.service.endGame();
        });

        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: black;");
        layout.setCenter(canvas);
        layout.setTop(timer);
        layout.setBottom(leave);

        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());

        int[][] l = this.service.getLayoutAsIntsForDrawing();
        int width = l.length;
        int height = l[0].length;
        int relativeSize = size / Math.max(width, height);
        double wallSize = 1.2;

        new AnimationTimer() {
            long prevMoment1 = 0;
            long prevMoment2 = 0;

            @Override
            public void handle(long curMoment) {
                if (service.gameEnded()) {
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
                            int type = l[x][y];
                            switch (type) {
                                case 0:
                                    marker.fillRect((x * relativeSize), (y * relativeSize), relativeSize / wallSize, relativeSize / wallSize);
                                    break;
                                case 1:
                                    marker.fillRect((x * relativeSize), (y * relativeSize), relativeSize, relativeSize / wallSize);
                                    break;
                                case 2:
                                    marker.fillRect((x * relativeSize), (y * relativeSize), relativeSize / wallSize, relativeSize);
                                    break;
                                default:
                                    marker.fillRect((x * relativeSize), (y * relativeSize), relativeSize / wallSize, relativeSize);
                                    marker.fillRect((x * relativeSize), (y * relativeSize), relativeSize, relativeSize / wallSize);
                                    break;
                            }
                            
                            if (service.mazeGoal() == service.getCellAtPos(x, y)) {
                                marker.setFill(Color.GREEN);
                                marker.fillRect((x * relativeSize), (y * relativeSize), relativeSize / wallSize, relativeSize / wallSize);
                                marker.setFill(Color.WHITE);
                            }

                            if (service.mazeCurrentCell() == service.getCellAtPos(x, y)) {
                                marker.setFill(Color.RED);
                                marker.fillOval((x * relativeSize), (y * relativeSize), relativeSize / wallSize, relativeSize / wallSize);
                                marker.setFill(Color.WHITE);
                            }
                            
                            if(service.goalReached()) {
                                service.endGame();
                            }
                             
                        }
                    }
                    this.prevMoment2 = curMoment;
                }

            }
        }.start();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                this.service.goUp();
            }
            if (e.getCode() == KeyCode.DOWN) {
                this.service.goDown();
            }
            if (e.getCode() == KeyCode.LEFT) {
                this.service.goLeft();
            }
            if (e.getCode() == KeyCode.RIGHT) {
                this.service.goRight();
            }
        });

        return scene;
    }
}
