package mazegame.ui;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class MazeGameUi extends Application {

    private Stage stage;
    private LoginScene loginScene;
    private LobbyScene lobbyScene;
    private GameScene gameScene;

    @Override
    public void start(Stage s) {
        this.stage = s;
        this.stage.setMaximized(true);
        this.loginScene = new LoginScene(this);
        this.lobbyScene = new LobbyScene(this);
        this.gameScene = new GameScene(this);

        this.stage.setTitle("Login");
        this.stage.setScene(this.loginScene.createAndGet());
        this.stage.show();
    }

    public void login() {
        this.stage.setTitle("Lobby");
        this.stage.setScene(this.lobbyScene.createAndGet());
    }

    public void logout() {
        this.stage.setTitle("Login");
        this.stage.setScene(this.loginScene.createAndGet());
    }

    public void play(int width, int height) {
        this.stage.setTitle("Game");
        this.stage.setScene(this.gameScene.createAndGet(width, height));
        this.stage.setFullScreen(true);
    }

    public void exitGame() {
        this.stage.setTitle("Lobby");
        this.stage.setScene(this.lobbyScene.createAndGet());
        this.stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
