package mazegame.ui;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class MazeGameUi extends Application {

    @Override
    public void start(Stage stage) {
        Scene gameScene = new GameScene(10, 10).get();

        stage.setScene(gameScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
