/*
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

public class BattlePane {
    private VBox root;
    private Button atackButton;
    private Button blockButton;
    private Button shootButton;


    public BattlePane(Runnable NewGame, Runnable Continue, Runnable Exit) throws IOException {
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menu-pane");

        ImageView banner = new ImageView(new Image(getClass().getResourceAsStream("/images/dialoguebox.png")));
        banner.setPreserveRatio(true);
        banner.setFitWidth(2000);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/GothicPixels.ttf"), 10);

        shootButton = new Button("New Game");
        shootButton.getStyleClass().add("button");
        shootButton.setFont(customFont);
        shootButton.setOnAction(e -> NewGame.run());

        atackButton = new Button("Continue");
        atackButton.getStyleClass().add("button");
        atackButton.setFont(customFont);
        atackButton.setOnAction(e -> Continue.run());

        blockButton = new Button("Exit");
        blockButton.getStyleClass().add("button");
        blockButton.setFont(customFont);
        blockButton.setOnAction(e -> Exit.run());

        StackPane overlayPane = new StackPane(banner, shootButton, atackButton, blockButton);
        StackPane.setAlignment(shootButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(shootButton, new Insets(0, 0, 200, 0));
        StackPane.setAlignment(atackButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(atackButton, new Insets(0, 0, 300, 0));
        StackPane.setAlignment(blockButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(blockButton, new Insets(0, 0, 100, 0));
        root.getChildren().add(overlayPane);
    }

    public VBox getRoot() {
        return root;
    }
}
*/