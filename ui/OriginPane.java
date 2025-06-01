package ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;

public class OriginPane {
    private VBox root;
    private Button loadingButton;

    public OriginPane() {

        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menu-pane");

        ImageView banner = new ImageView(new Image(getClass().getResourceAsStream("/images/673124.png")));
        banner.setPreserveRatio(true);
        banner.setFitWidth(2500);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/GothicPixels.ttf"), 10);


        StackPane overlayPane = new StackPane(banner);

        root.getChildren().add(overlayPane);
    }

    public VBox getRoot() {
        return root;
    }
}