package mazegame.ui;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        Text alert = new Text("");
        inputPane.getChildren().addAll(usernameInput, passwordInput, alert);

        HBox buttonPane = new HBox(10);
        Button login = new Button("login");
        Button register = new Button("register");
        buttonPane.getChildren().addAll(login, register);

        register.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            if (username.length() < 3) {
                alert.setText("Username must be at least 3 charecters");
            } else if (password.length() < 5) {
                alert.setText("Password must be at least 5 charecters");
            } else if (!ui.register(username, password)) {
                alert.setText("Username taken");
            } else {
                alert.setText("You can now login with the username and password you just chose");
                usernameInput.setText("");
                passwordInput.setText("");
            }
        });

        login.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            if (!ui.login(username, password)) {
                alert.setText("Username and/or password are/is incorrect");
            } else {
                alert.setText("Login in ...");
                usernameInput.setText("");
                passwordInput.setText("");
            }
        });

        layout.getChildren().addAll(inputPane, buttonPane);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());

        return scene;
    }
}
