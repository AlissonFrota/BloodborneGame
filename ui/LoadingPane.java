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

public class LoadingPane {
    private VBox root;
    private Button loadingButton;

    public LoadingPane() throws IOException, InterruptedException {

        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menu-pane");

        ImageView banner = new ImageView(new Image(getClass().getResourceAsStream("/images/banner.png")));
        banner.setPreserveRatio(true);
        banner.setFitWidth(2500);

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/GothicPixels.ttf"), 10);

        Button loadingButton = new Button("Loading...");
        loadingButton.getStyleClass().add("loading-button");
        loadingButton.getStyleClass().add("loading-button");
        loadingButton.setFont(customFont);
        Timeline dotsAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0),    e -> loadingButton.setText("Carregando")),
                new KeyFrame(Duration.seconds(0.5), e -> loadingButton.setText("Carregando.")),
                new KeyFrame(Duration.seconds(1.0), e -> loadingButton.setText("Carregando..")),
                new KeyFrame(Duration.seconds(1.5),  e -> loadingButton.setText("Carregando..."))
        );
        dotsAnimation.setCycleCount(Timeline.INDEFINITE);
        dotsAnimation.play();

        StackPane overlayPane = new StackPane(banner, loadingButton);
        StackPane.setAlignment(loadingButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(loadingButton, new Insets(0, 0, 100, 0));
        root.getChildren().add(overlayPane);
    }


    public VBox getRoot() {
        return root;
    }
}