package mazegame.ui;

import javafx.application.Application;

import javafx.stage.Stage;
import mazegame.domain.MazeGameService;

public class MazeGameUi extends Application {

    private Stage stage;
    private LoginScene loginScene;
    private LobbyScene lobbyScene;
    private GameScene gameScene;
    private MazeGameService mazeGameService;

    @Override
    public void start(Stage s) {
        this.mazeGameService = new MazeGameService();
        this.stage = s;
        this.stage.setMaximized(true);
        this.loginScene = new LoginScene(this);
        this.lobbyScene = new LobbyScene(this);
        this.gameScene = new GameScene(this);

        this.stage.setTitle("Login");
        this.stage.setScene(this.loginScene.createAndGet());
        this.stage.show();
    }

    public MazeGameService getService() {
        return this.mazeGameService;
    }

    public boolean register(String username, String password) {
        return this.mazeGameService.register(username, password);
    }

    public boolean login(String username, String password) {
        boolean succes = this.mazeGameService.login(username, password);
        if (succes) {
            this.stage.setTitle("Lobby");
            this.stage.setScene(this.lobbyScene.createAndGet());
        }
        return succes;
    }

    public void logout() {
        this.stage.setTitle("Login");
        this.stage.setScene(this.loginScene.createAndGet());
    }

    public void play(int width, int height) {
        this.stage.setTitle("Game");
        this.mazeGameService.startGame(width, height);
        this.stage.setScene(this.gameScene.createAndGet());
        this.stage.setFullScreen(true);
    }

    public void exitGame() {
        this.stage.setTitle("Lobby");
        this.mazeGameService.endGame();
        this.stage.setScene(this.lobbyScene.createAndGet());
        this.stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
