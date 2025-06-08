package ui;

import Entitys.Origin;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import repository.Repository;

public class OriginPane {
    private StackPane root;
    private ComboBox<Origin> originComboBox;
    private Origin ChosenOrigin;

    private Text vitalityText;
    private Text enduranceText;
    private Text strengthText;
    private Text skillText;
    private Text bloodTingeText;
    private Text arcaneText;
    private Text vitalityValue;
    private Text enduranceValue;
    private Text strengthValue;
    private Text skillValue;
    private Text bloodTingeValue;
    private Text arcaneValue;
    private Text levelValue;
    private Text insightValue;
    private Text bloodEchoesValue;

    public OriginPane(Repository repo, Runnable Continue) {

        root = new StackPane();
        root.getStyleClass().add("menu-pane");

        ImageView banner = new ImageView(new Image(getClass().getResourceAsStream("/images/originpane.png")));
        banner.setPreserveRatio(true);
        banner.setFitWidth(2050);

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Gothical.ttf"), 40);

        Label titleLabel = new Label("Origin");
        titleLabel.setFont(customFont);
        titleLabel.getStyleClass().add("title-label");

        originComboBox = new ComboBox<>();
        originComboBox.setPromptText("Choose an origin");
        originComboBox.setPrefWidth(350);
        originComboBox.getStyleClass().add("origin-combo-box");

        originComboBox.setConverter(new StringConverter<Origin>() {
            @Override
            public String toString(Origin origin) {
                return origin != null ? origin.getName() : "";
            }

            @Override
            public Origin fromString(String string) {
                return null;
            }
        });

        originComboBox.setItems(FXCollections.observableArrayList(repo.getOrigins()));

        originComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                ChosenOrigin = newVal;

                vitalityValue.setText(String.valueOf(ChosenOrigin.getVitality()));
                enduranceValue.setText(String.valueOf(ChosenOrigin.getEndurence()));
                strengthValue.setText(String.valueOf(ChosenOrigin.getStrength()));
                skillValue.setText(String.valueOf(ChosenOrigin.getSkill()));
                bloodTingeValue.setText(String.valueOf(ChosenOrigin.getBloodtinge()));
                arcaneValue.setText(String.valueOf(ChosenOrigin.getArcane()));
            }
        });

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        HBox.setMargin(titleLabel, new Insets(0, 100, 0, 0));
        hBox.getChildren().addAll(titleLabel, originComboBox);

        VBox leftStats = new VBox(0);
        VBox settedStats = new VBox(0);

        Text levelText = new Text("Level: ");
        Text insightText = new Text("Insight: ");
        Text bloodEchoesText = new Text("Blood Echoes: ");
        vitalityText = new Text("Vitality: ");
        enduranceText = new Text("Endurance: ");
        strengthText = new Text("Strength: ");
        skillText = new Text("Skill: ");
        bloodTingeText = new Text("BloodTinge: ");
        arcaneText = new Text("Arcane: ");

        levelValue = new Text("10");
        insightValue = new Text("0");
        bloodEchoesValue = new Text("300");
        vitalityValue = new Text("-");
        enduranceValue = new Text("-");
        strengthValue = new Text("-");
        skillValue = new Text("-");
        bloodTingeValue = new Text("-");
        arcaneValue = new Text("-");

        // CSS

        levelText.getStyleClass().add("text-level");    // CSS dos textos
        insightText.getStyleClass().add("text-level");
        bloodEchoesText.getStyleClass().add("text-level");
        vitalityText.getStyleClass().add("text-level");
        enduranceText.getStyleClass().add("text-level");
        strengthText.getStyleClass().add("text-level");
        skillText.getStyleClass().add("text-level");
        bloodTingeText.getStyleClass().add("text-level");
        arcaneText.getStyleClass().add("text-level");


        levelValue.getStyleClass().add("numbers-especial");
        insightValue.getStyleClass().add("numbers-especial");
        bloodEchoesValue.getStyleClass().add("numbers-especial");
        vitalityValue.getStyleClass().add("numbers-especial");    // CSS dos valores
        enduranceValue.getStyleClass().add("numbers-especial");
        strengthValue.getStyleClass().add("numbers-especial");
        skillValue.getStyleClass().add("numbers-especial");
        bloodTingeValue.getStyleClass().add("numbers-especial");
        arcaneValue.getStyleClass().add("numbers-especial");


        HBox levelBox = createStatBox1(levelText, levelValue);
        HBox insightBox = createStatBox1(insightText, insightValue);
        HBox bloodEchoesBox = createStatBox1(bloodEchoesText,bloodEchoesValue);
        HBox vitalityBox = createStatBox2(vitalityText, vitalityValue);
        HBox enduranceBox = createStatBox2(enduranceText, enduranceValue);
        HBox strengthBox = createStatBox2(strengthText, strengthValue);
        HBox skillBox = createStatBox2(skillText, skillValue);
        HBox bloodTingeBox = createStatBox2(bloodTingeText, bloodTingeValue);
        HBox arcaneBox = createStatBox2(arcaneText, arcaneValue);

        leftStats.getChildren().addAll(
                vitalityBox, enduranceBox, strengthBox,
                skillBox, bloodTingeBox, arcaneBox
        );

        settedStats.getChildren().addAll(
                levelBox,insightBox,bloodEchoesBox
        );

        leftStats.setMouseTransparent(true);

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            Continue.run();
        });

        VBox contentOverlay = new VBox(hBox, continueButton);
        contentOverlay.setAlignment(Pos.TOP_CENTER);
        contentOverlay.setPadding(new Insets(50));


        Pane statsContainer = new Pane(leftStats, settedStats);
        statsContainer.setPickOnBounds(false);

        leftStats.setLayoutX(1410);
        leftStats.setLayoutY(540);

        settedStats.setLayoutX(1420);
        settedStats.setLayoutY(263);

        root.getChildren().addAll(banner, contentOverlay, statsContainer);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }


    private HBox createStatBox1(Text status, Text value) {
        HBox box = new HBox(2, status, value);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(0,0,-5,0));
        return box;
    }


    // caixa dos status de origem
    private HBox createStatBox2(Text status, Text value) {
        HBox box = new HBox(7, status, value);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    public Origin getChosenOrigin() { return ChosenOrigin; }

    public StackPane getRoot() {
        return root;
    }
}