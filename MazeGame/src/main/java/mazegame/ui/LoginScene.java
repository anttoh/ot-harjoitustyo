package mazegame.ui;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class LoginScene {

    private MazeGameUi ui;

    public LoginScene(MazeGameUi ui) {
        this.ui = ui;
    }

    public Scene createAndGet() {

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(200));

        VBox inputPane = new VBox(10);
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("username");
        TextField passwordInput = new TextField();
        passwordInput.setPromptText("password");
        inputPane.getChildren().addAll(usernameInput, passwordInput);

        HBox buttonPane = new HBox(10);
        Button login = new Button("login");
        Button register = new Button("register");
        buttonPane.getChildren().addAll(login, register);

        login.setOnAction(e -> {
            usernameInput.setText("");
            passwordInput.setText("");
            ui.login();
        });

        layout.getChildren().addAll(inputPane, buttonPane);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());

        return scene;
    }
}
