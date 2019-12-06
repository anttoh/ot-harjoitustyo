package mazegame.ui;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class LobbyScene {

    private MazeGameUi ui;

    public LobbyScene(MazeGameUi ui) {
        this.ui = ui;

    }

    public Scene createAndGet() {

        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(200));

        VBox vbox = new VBox(10);
        TextField widthInput = new TextField("20");
        widthInput.setPromptText("width (2 - 90)");
        TextField heightInput = new TextField("20");
        heightInput.setPromptText("height (2 - 90)");
        Text alert = new Text("Width and height must be natural numbers "
                + "between 2 and 90");
        alert.setVisible(false);
        Text welcome = new Text("Logged in as " + this.ui.getService().getLoggedInUser().getUsername());

        Button logout = new Button("logout");
        Button play = new Button("start");

        HBox options = new HBox(10);
        Button veryeasy = new Button("very easy");
        Button easy = new Button("easy");
        Button medium = new Button("medium");
        Button hard = new Button("hard");
        Button ultrahard = new Button("ultra hard");
        options.getChildren().addAll(veryeasy, easy, medium, hard, ultrahard);

        veryeasy.setOnAction(e -> {
            String size = "5";
            widthInput.setText(size);
            heightInput.setText(size);

        });

        easy.setOnAction(e -> {
            String size = "10";
            widthInput.setText(size);
            heightInput.setText(size);

        });

        medium.setOnAction(e -> {
            String size = "20";
            widthInput.setText(size);
            heightInput.setText(size);

        });

        hard.setOnAction(e -> {
            String size = "40";
            widthInput.setText(size);
            heightInput.setText(size);

        });

        ultrahard.setOnAction(e -> {
            String size = "80";
            widthInput.setText(size);
            heightInput.setText(size);

        });

        play.setOnAction(e -> {
            try {
                int width = Integer.parseInt(widthInput.getText());
                int height = Integer.parseInt(heightInput.getText());
                if (width < 2 || width > 90 || height < 2 || height > 90) {
                    throw new NumberFormatException();
                }
                widthInput.setText("");
                heightInput.setText("");
                ui.play(width, height);
            } catch (NumberFormatException exception) {
                alert.setVisible(true);
            }

        });

        logout.setOnAction(e -> {
            ui.logout();
        });

        vbox.getChildren().addAll(options, widthInput, heightInput, play, alert);

        VBox bottom = new VBox(10);
        bottom.getChildren().addAll(welcome, logout);
        layout.setBottom(bottom);
        layout.setCenter(vbox);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());

        return scene;
    }
}
