package mazegame.ui;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
        TextField widthInput = new TextField();
        widthInput.setPromptText("width (2 - 90)");
        TextField heightInput = new TextField();
        heightInput.setPromptText("height (2 - 90)");
        Text alert = new Text("Width and height must be natural numbers "
                + "between 2 and 90");
        alert.setVisible(false);
        Text welcome = new Text("Logged in as " + this.ui.getService().getLoggedInUser().getUsername());

        VBox statistics = new VBox();
        Text statsText = new Text("Your statistics");
        statsText.setVisible(false);
        Text averege = new Text();
        Text best = new Text();
        Text worst = new Text();
        statistics.getChildren().addAll(statsText, averege, best, worst);

        Button logout = new Button("logout");
        Button play = new Button("start");

        HBox options = new HBox(10);
        Button veryeasy = new Button("very easy");
        Button easy = new Button("easy");
        Button medium = new Button("medium");
        Button hard = new Button("hard");
        Button ultrahard = new Button("ultra hard");
        CheckBox showPath = new CheckBox("highlight visited cells");
        showPath.fire();
        options.getChildren().addAll(veryeasy, easy, medium, hard, ultrahard, showPath);

        veryeasy.setOnAction(e -> {
            String size = "5";
            widthInput.setText(size);
            heightInput.setText(size);
            statsText.setVisible(true);
            if (this.ui.getService().getLoggedInUsersAveregeSolveTimes()[1] == 0) {
                averege.setText("You haven't finished a game in very easy difficulty yet");
                best.setText("");
                worst.setText("");
            } else {
                averege.setText("averege: " + this.ui.getService().getLoggedInUsersAveregeSolveTimes()[1] + " seconds");
                best.setText("best: " + this.ui.getService().getLoggedInUsersBestSolveTimes()[1] + " seconds");
                worst.setText("worst: " + this.ui.getService().getLoggedInUsersWorstSolveTimes()[1] + " seconds");
            }
        });

        easy.setOnAction(e -> {
            String size = "10";
            widthInput.setText(size);
            heightInput.setText(size);
            statsText.setVisible(true);
            if (this.ui.getService().getLoggedInUsersAveregeSolveTimes()[2] == 0) {
                averege.setText("You haven't finished a game in easy difficulty yet");
                best.setText("");
                worst.setText("");
            } else {
                averege.setText("averege: " + this.ui.getService().getLoggedInUsersAveregeSolveTimes()[2] + " seconds");
                best.setText("best: " + this.ui.getService().getLoggedInUsersBestSolveTimes()[2] + " seconds");
                worst.setText("worst: " + this.ui.getService().getLoggedInUsersWorstSolveTimes()[2] + " seconds");
            }
        });

        medium.setOnAction(e -> {
            String size = "20";
            widthInput.setText(size);
            heightInput.setText(size);
            statsText.setVisible(true);
            if (this.ui.getService().getLoggedInUsersAveregeSolveTimes()[3] == 0) {
                averege.setText("You haven't finished a game in medium difficulty yet");
                best.setText("");
                worst.setText("");
            } else {
                averege.setText("averege: " + this.ui.getService().getLoggedInUsersAveregeSolveTimes()[3] + " seconds");
                best.setText("best: " + this.ui.getService().getLoggedInUsersBestSolveTimes()[3] + " seconds");
                worst.setText("worst: " + this.ui.getService().getLoggedInUsersWorstSolveTimes()[3] + " seconds");
            }
        });

        hard.setOnAction(e -> {
            String size = "40";
            widthInput.setText(size);
            heightInput.setText(size);
            statsText.setVisible(true);
            if (this.ui.getService().getLoggedInUsersAveregeSolveTimes()[4] == 0) {
                averege.setText("You haven't finished a game in hard difficulty yet");
                best.setText("");
                worst.setText("");
            } else {
                averege.setText("averege: " + this.ui.getService().getLoggedInUsersAveregeSolveTimes()[4] + " seconds");
                best.setText("best: " + this.ui.getService().getLoggedInUsersBestSolveTimes()[4] + " seconds");
                worst.setText("worst: " + this.ui.getService().getLoggedInUsersWorstSolveTimes()[4] + " seconds");
            }
        });

        ultrahard.setOnAction(e -> {
            String size = "80";
            widthInput.setText(size);
            heightInput.setText(size);
            statsText.setVisible(true);
            if (this.ui.getService().getLoggedInUsersAveregeSolveTimes()[5] == 0) {
                averege.setText("You haven't finished a game in ultra hard difficulty yet");
                best.setText("");
                worst.setText("");
            } else {
                averege.setText("averege: " + this.ui.getService().getLoggedInUsersAveregeSolveTimes()[5] + " seconds");
                best.setText("best: " + this.ui.getService().getLoggedInUsersBestSolveTimes()[5] + " seconds");
                worst.setText("worst: " + this.ui.getService().getLoggedInUsersWorstSolveTimes()[5] + " seconds");
            }
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
                ui.getService().setShowPath(showPath.isSelected());
                ui.startGame(width, height);
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
        layout.setTop(statistics);
        layout.setBottom(bottom);
        layout.setCenter(vbox);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(layout, screenBounds.getWidth(), screenBounds.getHeight());

        return scene;
    }
}
