
package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import repository.Repository;

public class BattlePane {
    private StackPane root;
    private Button attackButton;
    private Button blockButton;
    private Button shootButton;
    private Button dogdeButton;

    public BattlePane(Runnable onAttack, Runnable onBlock, Runnable onShoot, Runnable onDogde) {
        root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("battle-pane");

        // Camada 1: Fundo da batalha
        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("/images/arena.png")));
        background.setPreserveRatio(true);
        background.setFitWidth(2010);
/*
        // Camada 2: Personagem inimigo
        ImageView enemy = new ImageView(new Image(getClass().getResourceAsStream("/images/amygdalaTitle.png")));
        enemy.setPreserveRatio(true);
        enemy.setFitHeight(300);
        enemy.setTranslateX(150); // Posicionamento à direita

        // Camada 3: Personagem do jogador
        ImageView player = new ImageView(new Image(getClass().getResourceAsStream("/images/hunterjpg.png")));
        player.setPreserveRatio(true);
        player.setFitHeight(300);
        player.setTranslateX(-150); // Posicionamento à esquerda
*/
        // 4. Camada de UI (caixa de diálogo com áreas para botões)
        ImageView dialogueBox = new ImageView(new Image(getClass().getResourceAsStream("/images/dialoguebox.png")));
        dialogueBox.setPreserveRatio(true);
        dialogueBox.setFitWidth(1500);
        dialogueBox.setFitHeight(2000);
        dialogueBox.setLayoutY(1500);
        dialogueBox.setOpacity(0.75);


        VBox buttonContainer = new VBox(15);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setTranslateY(100); // Ajuste para alinhar com a caixa de diálogo
        buttonContainer.setPadding(new Insets(20, 0, 40, 0));

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/GothicPixels.ttf"), 16);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        attackButton = new Button("Attack");
        attackButton.getStyleClass().add("button");
        attackButton.setFont(customFont);
        attackButton.setOnAction(e -> onAttack.run());
        StackPane.setAlignment(attackButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(attackButton, new Insets(0, 200, 100, 0));

        blockButton = new Button("Block");
        blockButton.getStyleClass().add("button");
        blockButton.setFont(customFont);
        blockButton.setOnAction(e -> onBlock.run());
        StackPane.setAlignment(blockButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(blockButton, new Insets(0, 0, 100, 0));

        shootButton = new Button("Shoot");
        shootButton.getStyleClass().add("button");
        shootButton.setFont(customFont);
        shootButton.setOnAction(e -> onShoot.run());
        StackPane.setAlignment(shootButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(shootButton, new Insets(0, 0, 100, 200));

        dogdeButton = new Button("Dogde");
        dogdeButton.getStyleClass().add("button");
        dogdeButton.setFont(customFont);
        dogdeButton.setOnAction(e -> onDogde.run());
        StackPane.setAlignment(shootButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(shootButton, new Insets(0, 0, 100, 200));

        VBox buttonBox = new VBox(15, attackButton, blockButton, shootButton, dogdeButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setTranslateY(180);
        buttonContainer.getChildren().addAll(attackButton, blockButton, shootButton, dogdeButton);

        root.getChildren().addAll(
                background,
                /*
                enemy,
                player,
                */
                dialogueBox,
                buttonContainer
        );

        StackPane overlayPane = new StackPane(shootButton, attackButton, blockButton, dogdeButton);
        StackPane.setAlignment(shootButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(shootButton, new Insets(500, 1000, 100, 0));
        StackPane.setAlignment(attackButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(attackButton, new Insets(500, 1000, 250, 0));
        StackPane.setAlignment(blockButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(blockButton, new Insets(500, 500, 100, 0));
        StackPane.setAlignment(dogdeButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(dogdeButton, new Insets(500, 500, 250, 0));
        root.getChildren().add(overlayPane);
    }

    public BattlePane(Repository repo) {
    }

    private Button createTransparentButton(String text, Font font) {
        Button btn = new Button(text);
        btn.setFont(font);
        btn.getStyleClass().add("transparent-button");
        btn.setPrefSize(120, 40); // Ajuste conforme as áreas no seu PNG
        return btn;

    }

    public StackPane getRoot() {
        return root;
    }
}