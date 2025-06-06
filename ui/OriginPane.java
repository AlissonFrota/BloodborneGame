/*
package ui;

import Entitys.Origin;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import repository.Repository;

public class OriginPane {
    private VBox root;
    private ComboBox<Origin> originComboBox;
    private StackPane;

    public OriginPane(Repository repo) {

        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menu-pane");

        ImageView banner = new ImageView(new Image(getClass().getResourceAsStream("/images/originpane.png")));
        banner.setPreserveRatio(true);
        banner.setFitWidth(2100);


        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/GothicPixels.ttf"), 10);

        Label titleLabel = new Label("Origin");
        titleLabel.setFont(customFont);
        titleLabel.getStyleClass().add("title-label");

        originComboBox = new ComboBox<>();
        originComboBox.setPromptText("Choose an origin");
        originComboBox.setPrefWidth(300);
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


        VBox contentOverlay = new VBox(hbox);
        StackPane.setAlignment(contentOverlay, Pos.TOP_CENTER);
        contentOverlay.setPadding(new Insets(60));

        originComboBox.setItems(FXCollections.observableArrayList(repo.getOrigins()));

        originComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                //Selecionar origin newVal
            }
        });


        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        HBox.setMargin(titleLabel, new Insets(0, 100, 0, 0));
        hBox.getChildren().addAll(titleLabel, originComboBox);

        root.getChildren().addAll(
                banner,hBox
        );

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public VBox getRoot() {
        return root;
    }
}

 */

package ui;

import Entitys.Origin;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private Text VitalityValue;
    private Text EnduranceValue;
    private Text StrengthValue;
    private Text SkillValue;
    private Text BloodTingeValue;
    private Text ArcaneValue;

    public OriginPane(Repository repo) {

        root = new StackPane();
        root.getStyleClass().add("menu-pane");

        ImageView banner = new ImageView(new Image(getClass().getResourceAsStream("/images/originpane.png")));
        banner.setPreserveRatio(true);
        banner.setFitWidth(2050);

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/GothicPixels.ttf"), 40);


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

                VitalityValue.setText(String.valueOf(ChosenOrigin.getVitality()));
                EnduranceValue.setText(String.valueOf(ChosenOrigin.getEndurence()));
                StrengthValue.setText(String.valueOf(ChosenOrigin.getStrength()));
                SkillValue.setText(String.valueOf(ChosenOrigin.getSkill()));
                BloodTingeValue.setText(String.valueOf(ChosenOrigin.getBloodtinge()));
                ArcaneValue.setText(String.valueOf(ChosenOrigin.getArcane()));
            }
        });

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        HBox.setMargin(titleLabel, new Insets(0, 100, 0, 0));
        hBox.getChildren().addAll(titleLabel, originComboBox);

        //Nivel

        Text levelText = new Text("Level");
        levelText.getStyleClass().add("text-level");

        Text levelValue = new Text("10");
        levelValue.getStyleClass().add("numbers-especial");

        HBox levelHBox = new HBox(levelText, levelValue);
        levelHBox.setAlignment(Pos.CENTER);
        levelHBox.setSpacing(30);

        //Blood Echoes

        Text BloodEchoesText = new Text("Blood Echoes");
        BloodEchoesText.getStyleClass().add("text-level");

        Text BloodEchoesValue = new Text("300");
        BloodEchoesValue.getStyleClass().add("numbers-especial");

        HBox BloodEchoesHBox = new HBox(BloodEchoesText, BloodEchoesValue);
        BloodEchoesHBox.setAlignment(Pos.CENTER);
        BloodEchoesHBox.setSpacing(30);

        //Vitality

        Text VitalityText = new Text("Vitality");
        VitalityText.getStyleClass().add("text-level");

        VitalityValue = new Text("#");
        VitalityValue.getStyleClass().add("numbers-especial");

        HBox VitalityHBox = new HBox(VitalityText, VitalityValue);
        VitalityHBox.setAlignment(Pos.CENTER);
        VitalityHBox.setSpacing(30);

        // Endurance
        Text EnduranceText = new Text("Endurance");
        EnduranceText.getStyleClass().add("text-level");

        EnduranceValue = new Text("#");
        EnduranceValue.getStyleClass().add("numbers-especial");

        HBox EnduranceHBox = new HBox(EnduranceText, EnduranceValue);
        EnduranceHBox.setAlignment(Pos.CENTER);
        EnduranceHBox.setSpacing(30);

        // Strength
        Text StrengthText = new Text("Strength");
        StrengthText.getStyleClass().add("text-level");

        StrengthValue = new Text("#");
        StrengthValue.getStyleClass().add("numbers-especial");

        HBox StrengthHBox = new HBox(StrengthText, StrengthValue);
        StrengthHBox.setAlignment(Pos.CENTER);
        StrengthHBox.setSpacing(30);

        // Skill
        Text SkillText = new Text("Skill");
        SkillText.getStyleClass().add("text-level");

        SkillValue = new Text("#");
        SkillValue.getStyleClass().add("numbers-especial");

        HBox SkillHBox = new HBox(SkillText, SkillValue);
        SkillHBox.setAlignment(Pos.CENTER);
        SkillHBox.setSpacing(30);

        // Bloodtinge
        Text BloodTingeText = new Text("Bloodtinge");
        BloodTingeText.getStyleClass().add("text-level");

        BloodTingeValue = new Text("#");
        BloodTingeValue.getStyleClass().add("numbers-especial");

        HBox BloodTingeHBox = new HBox(BloodTingeText, BloodTingeValue);
        BloodTingeHBox.setAlignment(Pos.CENTER);
        BloodTingeHBox.setSpacing(30);

        // Arcane
        Text ArcaneText = new Text("Arcane");
        ArcaneText.getStyleClass().add("text-level");

        ArcaneValue = new Text("#");
        ArcaneValue.getStyleClass().add("numbers-especial");

        HBox ArcaneHBox = new HBox(ArcaneText, ArcaneValue);
        ArcaneHBox.setAlignment(Pos.CENTER);
        ArcaneHBox.setSpacing(30);

        VBox levelContents = new VBox(levelHBox, BloodEchoesHBox, VitalityHBox, EnduranceHBox, StrengthHBox, SkillHBox, BloodTingeHBox, ArcaneHBox);
        levelContents.setAlignment(Pos.CENTER_RIGHT);

        levelContents.setMouseTransparent(true);

        VBox contentOverlay = new VBox(hBox);
        contentOverlay.setAlignment(Pos.TOP_CENTER);
        contentOverlay.setPadding(new Insets(50)); // Padding opcional para espaçamento interno


        root.getChildren().addAll(banner, contentOverlay, levelContents);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public StackPane getRoot() {
        return root;
    }
}


