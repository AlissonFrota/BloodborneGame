package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;

public class StartMenuPane {
    private VBox root;
    private Button startButton;

    public StartMenuPane() throws IOException {
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menu-pane");

        ImageView banner = new ImageView(new Image(getClass().getResourceAsStream("/images/banner.png")));
        banner.setPreserveRatio(true);
        banner.setFitWidth(2500);

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/GothicPixels.ttf"), 10);

        startButton = new Button("Start Game");
        startButton.getStyleClass().add("start-button");
        startButton.setFont(customFont);
        startButton.setOnAction(e -> {

        });

        StackPane overlayPane = new StackPane(banner, startButton);
        StackPane.setAlignment(startButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(startButton, new Insets(0, 0, 300, 0));
        root.getChildren().add(overlayPane);
    }

    public VBox getRoot() {
        return root;
    }
}
