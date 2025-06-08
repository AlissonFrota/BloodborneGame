package ui;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;

public class StartMenuPane {
    private VBox root;
    private Button startButton;
    private Button continueButton;
    private Button exitButton;
    private Button hiddenButton;



    public StartMenuPane(Runnable NewGame, Runnable Continue, Runnable Exit, Runnable hidden) throws IOException {
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menu-pane");

        ImageView banner = new ImageView(new Image(getClass().getResourceAsStream("/images/amygdalaTitle.png")));
        banner.setPreserveRatio(true);
        banner.setFitWidth(2000);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Gothical.ttf"), 10);

        startButton = new Button("New Game");
        startButton.getStyleClass().add("button-title");
        startButton.setFont(customFont);
        startButton.setOnAction(e -> NewGame.run());

        continueButton = new Button("Continue");
        continueButton.getStyleClass().add("button-title");
        continueButton.setFont(customFont);
        continueButton.setOnAction(e -> Continue.run());

        exitButton = new Button("Exit");
        exitButton.getStyleClass().add("button-title");
        exitButton.setFont(customFont);
        exitButton.setOnAction(e -> Exit.run());

        hiddenButton = new Button("");
        hiddenButton.getStyleClass().add("easter-egg");
        hiddenButton.setPrefSize(20, 230);
        hiddenButton.setFont(customFont);



        StackPane overlayPane = new StackPane(banner, startButton, continueButton, exitButton, hiddenButton);
        StackPane.setAlignment(startButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(startButton, new Insets(0, 0, 200, 0));
        StackPane.setAlignment(continueButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(continueButton, new Insets(0, 0, 300, 0));
        StackPane.setAlignment(exitButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(exitButton, new Insets(0, 0, 100, 0));
        StackPane.setAlignment(hiddenButton,Pos.CENTER);
        StackPane.setMargin(hiddenButton, new Insets(260, 0, 0, 770));
        ScaleTransition grow = new ScaleTransition(Duration.millis(200), hiddenButton);
        grow.setToX(1.3);
        grow.setToY(1.7);

        ScaleTransition shrink = new ScaleTransition(Duration.millis(150), hiddenButton);
        shrink.setToX(1);
        shrink.setToY(1);

        hiddenButton.setOnMouseEntered(e -> grow.playFromStart());
        hiddenButton.setOnMouseExited(e -> shrink.playFromStart());
        root.getChildren().add(overlayPane);
    }

    public VBox getRoot() {
        return root;
    }
}
