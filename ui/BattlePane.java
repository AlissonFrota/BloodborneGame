// battle

package ui;

import Entitys.Personagem;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    private TextArea battleLog;
    private Label playerHPLabel;
    private Label monsterHPLabel;
    private Label ammoLabel;
    private Label phaseLabel;
    private TextArea battleTerminal;

    public BattlePane(Personagem Hunter, Runnable onAttack, Runnable onBlock, Runnable onShoot, Runnable onDogde) {
        root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("battle-pane");

        // Camada 1: Fundo da batalha
        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("/images/arena-visibilidade.png")));
        background.setPreserveRatio(true);
        background.setFitWidth(2030);
/*
        // Camada 1: Caçador
        ImageView hunter = new ImageView(new Image(getClass().getResourceAsStream("/images/hunter.png")));
        hunter.setPreserveRatio(true);
        hunter.setFitWidth(200);
        hunter.setFitHeight(150);
        hunter.setLayoutX(200);
        hunter.setTranslateY(100);
        hunter.setOpacity(0.75);
*/
        // Camada 2: Boss
        ImageView boss = new ImageView(new Image(getClass().getResourceAsStream("/images/amygdalaBoss.png")));
        boss.setPreserveRatio(true);
        boss.setFitWidth(2220);
        boss.setFitHeight(1460);
        boss.setMouseTransparent(true);
        boss.setOpacity(0.75);
        /*
        //camada 4: HUD Vida do Hunter
        ImageView enemy = new ImageView(new Image(getClass().getResourceAsStream("/images/amygdalaTitle.png")));
        enemy.setPreserveRatio(true);
        enemy.setFitHeight(300);
        enemy.setTranslateX(150); // Posicionamento à direita

        */
        // 5. Camada de UI (caixa de diálogo com áreas para botões)
        ImageView dialogueBox = new ImageView(new Image(getClass().getResourceAsStream("/images/dialoguebox2.png")));
        dialogueBox.setPreserveRatio(false);
        dialogueBox.setFitWidth(2040);  // molde do HUD
        dialogueBox.setFitHeight(1050);
        dialogueBox.setOpacity(0.65);


        //6. Camada de UI TextBox
        ImageView textBox = new ImageView(new Image(getClass().getResourceAsStream("/images/textbox.png")));
        textBox.setPreserveRatio(true);
        textBox.setFitWidth(620);
        textBox.setFitHeight(500);          //caixa de texto
        textBox.setLayoutY(100);
        textBox.setOpacity(0.45);



        VBox buttonContainer = new VBox(15);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setTranslateY(100);
        buttonContainer.setPadding(new Insets(20, 0, 120, 0));

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Gothical.ttf"), 16);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        attackButton = new Button("Attack");
        attackButton.getStyleClass().add("battle-button");
        attackButton.setFont(customFont);                   //Botão de ataque
        attackButton.setOnAction(e -> onAttack.run());
        StackPane.setAlignment(attackButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(attackButton, new Insets(0, 200, 100, 0));

        blockButton = new Button("Block");
        blockButton.getStyleClass().add("battle-button");
        blockButton.setFont(customFont);                    //Botão de Bloqueio
        blockButton.setOnAction(e -> onBlock.run());
        StackPane.setAlignment(blockButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(blockButton, new Insets(0, 0, 100, 0));

        shootButton = new Button("Shoot");
        shootButton.getStyleClass().add("battle-button");
        shootButton.setFont(customFont);                    //Botão de Tiro
        shootButton.setOnAction(e -> onShoot.run());
        StackPane.setAlignment(shootButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(shootButton, new Insets(0, 0, 100, 200));

        dogdeButton = new Button("Dogde");
        dogdeButton.getStyleClass().add("battle-button");   //Botão de esquiva
        dogdeButton.setFont(customFont);
        dogdeButton.setOnAction(e -> onDogde.run());
        StackPane.setAlignment(shootButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(shootButton, new Insets(0, 0, 40, 200));

        battleTerminal = new TextArea();        //texto do terminal in-game
        battleTerminal.setEditable(false);
        battleTerminal.setWrapText(true);
        battleTerminal.setPrefRowCount(4);
        battleTerminal.setMaxWidth(600);
        battleTerminal.setMaxHeight(130);
        battleTerminal.autosize();
        battleTerminal.setStyle("-fx-control-inner-background: #0a0a0a; " +
                "-fx-text-fill: #e0e0e0; " +
                "-fx-font-family: 'Times New Roman'; " +
                "-fx-font-size: 20px; " +
                "-fx-opacity: 0.9;");
        StackPane.setAlignment(battleTerminal, Pos.BOTTOM_CENTER); // Alinhamento base
        StackPane.setMargin(battleTerminal, new Insets(0, 0, 250, 0));

        StackPane terminalContainer = new StackPane(battleTerminal);
        terminalContainer.setAlignment(Pos.BOTTOM_CENTER);
        StackPane.setMargin(terminalContainer, new Insets(0, 0, 55, 0));

        VBox buttonBox = new VBox(15, attackButton, blockButton, shootButton, dogdeButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setTranslateY(180);
        buttonContainer.getChildren().addAll(attackButton, blockButton, shootButton, dogdeButton);

        root.getChildren().addAll(
                background,
                boss,                   //sobreposições da Pane
                /*
                enemy,
                player,
                */
                dialogueBox,
                terminalContainer,
                textBox,
                buttonContainer


        );

        StackPane overlayPane = new StackPane(shootButton, attackButton, blockButton, dogdeButton, textBox);
        StackPane.setAlignment(shootButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(shootButton, new Insets(500, 1520, 210, 0));
        StackPane.setAlignment(attackButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(attackButton, new Insets(500, 1520, 340, 0));
        StackPane.setAlignment(blockButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(blockButton, new Insets(500, 1000, 210, 0));
        StackPane.setAlignment(dogdeButton, Pos.BOTTOM_CENTER);
        StackPane.setMargin(dogdeButton, new Insets(500, 1000, 340, 0));
        StackPane.setAlignment(textBox, Pos.BOTTOM_CENTER);
        StackPane.setMargin(textBox, new Insets(500, 0, 300, 0));   // dialogueBox
        root.getChildren().add(overlayPane);
    }

    public void logToTerminal(String message) {
        Platform.runLater(() -> {
            battleTerminal.appendText(message + "\n");
            battleTerminal.setScrollTop(Double.MAX_VALUE);
        });
    }

    public StackPane getRoot() {
        return root;
    }
}
