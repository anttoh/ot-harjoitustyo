package mazegame.ui;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import mazegame.domain.CellTypeForDrawing;
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

        CellTypeForDrawing[][] layoutForDrawing = this.service.getLayoutForDrawing();
        int width = layoutForDrawing.length;
        int height = layoutForDrawing[0].length;
        int relativeSize = size / Math.max(width, height);
        double wallSize = 1.2;

        new AnimationTimer() {
            long prevMoment1 = 0;
            long prevMoment2 = 0;

            @Override
            public void handle(long curMoment) {
                if (service.gameEnded()) {
                    stop();
                    ui.exitGame(time.get());
                }

                if (curMoment - prevMoment1 > 1000000000) {
                    timer.setText("Timer: " + time.incrementAndGet());
                    this.prevMoment1 = curMoment;
                }

                if (curMoment - prevMoment2 > 100000) {
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            CellTypeForDrawing type = layoutForDrawing[x][y];
                            switch (type) {
                                case HAS_NEITHER_RIGHT_NOR_DOWN_NEIGHBOUR:
                                    marker.fillRect((x * relativeSize), (y * relativeSize), relativeSize / wallSize, relativeSize / wallSize);
                                    break;
                                case HAS_RIGHT_NEIGHBOUR:
                                    marker.fillRect((x * relativeSize), (y * relativeSize), relativeSize, relativeSize / wallSize);
                                    break;
                                case HAS_DOWN_NEIGHBOUR:
                                    marker.fillRect((x * relativeSize), (y * relativeSize), relativeSize / wallSize, relativeSize);
                                    break;
                                case HAS_BOTH_RIGHT_AND_DOWN_NEIGHBOUR:
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

                            if (service.goalReached()) {
                                service.endGame();
                            }

                        }
                    }
                    this.prevMoment2 = curMoment;
                }

            }
        }.start();

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        service.goUp();
                        break;
                    case DOWN:
                        service.goDown();
                        break;
                    case LEFT:
                        service.goLeft();
                        break;
                    case RIGHT:
                        service.goRight();
                        break;

                }
            }
        });

        return scene;
    }
}
