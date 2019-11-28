package mazegame.ui;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
        TextField widthInput = new TextField("5");
        widthInput.setPromptText("width");
        TextField heightInput = new TextField("5");
        heightInput.setPromptText("height");
        Text alert = new Text("Width and height must be natural numbers "
                + "between 2 and 90");
        alert.setVisible(false);

        Button logout = new Button("logout");
        Button play = new Button("start");

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

        vbox.getChildren().addAll(widthInput, heightInput, play, alert);

        layout.setBottom(logout);
        layout.setCenter(vbox);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());

        return scene;
    }
}
