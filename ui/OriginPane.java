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

                vitalityText.setText("Vitality: " + ChosenOrigin.getVitality());
                enduranceText.setText("Endurance: " + ChosenOrigin.getEndurence());
                strengthText.setText("Strength: " + ChosenOrigin.getStrength());
                skillText.setText("Skill: " + ChosenOrigin.getSkill());
                bloodTingeText.setText("BloodTinge: " + ChosenOrigin.getBloodtinge());
                arcaneText.setText("Arcane: " + ChosenOrigin.getArcane());
            }
        });

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        HBox.setMargin(titleLabel, new Insets(0, 100, 0, 0));
        hBox.getChildren().addAll(titleLabel, originComboBox);

        VBox leftStats = new VBox(5);
        leftStats.setPadding(new Insets(0, 0, 300, 0));
        Text levelText = new Text("Level: 10");
        levelText.getStyleClass().add("text-level");
        Text insightText = new Text("Insight: 0");
        insightText.getStyleClass().add("text-level");
        vitalityText = new Text("Vitality: -");
        vitalityText.getStyleClass().add("text-level");
        enduranceText = new Text("Endurance: -");
        enduranceText.getStyleClass().add("text-level");
        strengthText = new Text("Strength: -");
        strengthText.getStyleClass().add("text-level");
        skillText = new Text("Skill: -");
        skillText.getStyleClass().add("text-level");
        bloodTingeText = new Text("BloodTinge: -");
        bloodTingeText.getStyleClass().add("text-level");
        arcaneText = new Text("Arcane: -");
        arcaneText.getStyleClass().add("text-level");

        leftStats.getChildren().addAll(
                levelText, insightText, vitalityText, enduranceText, strengthText,
                skillText, bloodTingeText, arcaneText
        );

        leftStats.setMouseTransparent(true);
        leftStats.setAlignment(Pos.CENTER);

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            Continue.run();
        });

        VBox contentOverlay = new VBox(hBox, continueButton);
        contentOverlay.setAlignment(Pos.TOP_CENTER);
        contentOverlay.setPadding(new Insets(50));

        root.getChildren().addAll(banner, contentOverlay, leftStats);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public Origin getChosenOrigin() { return ChosenOrigin; }

    public StackPane getRoot() {
        return root;
    }
}
