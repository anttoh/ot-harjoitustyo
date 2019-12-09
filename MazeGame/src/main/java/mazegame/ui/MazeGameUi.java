package mazegame.ui;

import javafx.application.Application;

import javafx.stage.Stage;
import mazegame.domain.MazeGameService;

/**
 * Class responsible for being the main UI class. It contains "extended"
 * versions of some of the methods of MazeGameService, such as login, that
 * require switching the scene that the user is seeing. In short, these methods
 * handle switching scenes.
 */
public class MazeGameUi extends Application {

    private Stage stage;
    private LoginScene loginScene;
    private LobbyScene lobbyScene;
    private GameScene gameScene;
    private MazeGameService mazeGameService;

    @Override
    public void start(Stage s) {
        this.stage = s;
        this.mazeGameService = new MazeGameService();
        this.loginScene = new LoginScene(this);
        this.lobbyScene = new LobbyScene(this);
        this.gameScene = new GameScene(this);

        this.stage.setTitle("Login");
        this.stage.setScene(this.loginScene.createAndGet());
        this.stage.show();
    }

    /**
     * Method returns the one and only instance of MazeGameService that this
     * class initiates.
     *
     * @return MazeGameService
     */
    public MazeGameService getService() {
        return this.mazeGameService;
    }

    /**
     * Method registers user and returns whether or not it was successful.
     *
     * @param username users chosen username
     * @param password users chosen password
     *
     * @see mazegame.domain.MazeGameService#register(String, String)
     *
     * @return false if username was taken and true if it was available
     */
    public boolean register(String username, String password) {
        return this.mazeGameService.register(username, password);
    }

    /**
     * Method logs in the user and returns whether or not it was successful.
     * Method switches scene to lobby scene if login was successful.
     *
     * @param username users chosen username
     * @param password users chosen password
     *
     * @see mazegame.domain.MazeGameService#login(String, String)
     *
     * @return true if username and password were correct and false otherwise
     */
    public boolean login(String username, String password) {
        boolean succes = this.mazeGameService.login(username, password);
        if (succes) {
            this.stage.setTitle("Lobby");
            this.stage.setScene(this.lobbyScene.createAndGet());
        }
        return succes;
    }

    /**
     * Method logs out the user and switches scene to login scene.
     *
     * @see mazegame.domain.MazeGameService#logout()
     */
    public void logout() {
        this.mazeGameService.logout();
        this.stage.setTitle("Login");
        this.stage.setScene(this.loginScene.createAndGet());
    }

    /**
     * Method starts the game and switches scene to game scene.
     *
     * @param width users chosen width of the maze
     * @param height users chosen height of the maze
     *
     * @see mazegame.domain.MazeGameService#startGame(int, int)
     */
    public void startGame(int width, int height) {
        this.mazeGameService.startGame(width, height);
        this.stage.setTitle("Game");
        this.stage.setScene(this.gameScene.createAndGet());
        this.stage.setFullScreen(true);
    }

    /**
     * Method exits the game and switches scene back to lobby scene
     *
     * @param time timers time before leaving the game
     *
     * @see mazegame.domain.MazeGameService#exitGame(int)
     */
    public void exitGame(int time) {
        this.mazeGameService.exitGame(time);
        this.stage.setTitle("Lobby");
        this.stage.setScene(this.lobbyScene.createAndGet());
        this.stage.setFullScreen(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
