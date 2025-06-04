package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private Button continueButton;
    private Button exitButton;
    private Button battleButton;


    public StartMenuPane(Runnable NewGame, Runnable Continue,Runnable Battle, Runnable Exit) throws IOException {
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menu-pane");

        ImageView banner = new ImageView(new Image(getClass().getResourceAsStream("/images/amygdalaTitle.png")));
        banner.setPreserveRatio(true);
        banner.setFitWidth(2000);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/GothicPixels.ttf"), 10);

        startButton = new Button("New Game");
        startButton.getStyleClass().add("button");
        startButton.setFont(customFont);
        startButton.setOnAction(e -> NewGame.run());

        continueButton = new Button("Continue");
        continueButton.getStyleClass().add("button");
        continueButton.setFont(customFont);
        continueButton.setOnAction(e -> Continue.run());

        battleButton = new Button("Battle");
        battleButton.getStyleClass().add("button");
        battleButton.setFont(customFont);
        battleButton.setOnAction(e -> Battle.run());

        exitButton = new Button("Exit");
        exitButton.getStyleClass().add("button");
        exitButton.setFont(customFont);
        exitButton.setOnAction(e -> Exit.run());


        StackPane overlayPane = new StackPane(banner, startButton, continueButton, battleButton, exitButton);
        StackPane.setAlignment(battleButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(battleButton, new Insets(0, 0, 400, 0));
        StackPane.setAlignment(startButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(startButton, new Insets(0, 0, 200, 0));
        StackPane.setAlignment(continueButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(continueButton, new Insets(0, 0, 300, 0));
        StackPane.setAlignment(exitButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(exitButton, new Insets(0, 0, 100, 0));
        root.getChildren().add(overlayPane);
    }

    public VBox getRoot() {
        return root;
    }
}
