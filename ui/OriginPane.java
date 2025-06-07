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
    private String VitalityValue;
    private String EnduranceValue;
    private String StrengthValue;
    private String SkillValue;
    private String BloodTingeValue;
    private String ArcaneValue;

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

                VitalityValue =(String.valueOf(ChosenOrigin.getVitality()));
                EnduranceValue =(String.valueOf(ChosenOrigin.getEndurence()));
                StrengthValue =(String.valueOf(ChosenOrigin.getStrength()));
                SkillValue =(String.valueOf(ChosenOrigin.getSkill()));
                BloodTingeValue = (String.valueOf(ChosenOrigin.getBloodtinge()));
                ArcaneValue =(String.valueOf(ChosenOrigin.getArcane()));
            }
        });

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        HBox.setMargin(titleLabel, new Insets(0, 100, 0, 0));
        hBox.getChildren().addAll(titleLabel, originComboBox);

        VBox leftStats = new VBox(5);
        leftStats.setPadding(new Insets(0, 0, 300, 0));
        leftStats.getChildren().addAll(
                new Text("Level: " + "10"),
                new Text("Insight: " + "0"),
                new Text("Vitality: " + VitalityValue),
                new Text("Endurance: " + EnduranceValue),
                new Text("Strength: " + StrengthValue),
                new Text("Skill: " + SkillValue),
                new Text("BloodTinge: " + BloodTingeValue),
                new Text("Arcane: " + ArcaneValue)
        );

        leftStats.setMouseTransparent(true);
        leftStats.getStyleClass().add("text-level");
        leftStats.setAlignment(Pos.CENTER);

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            Continue.run();
        });

        VBox contentOverlay = new VBox(hBox, continueButton);
        contentOverlay.setAlignment(Pos.TOP_CENTER);
        contentOverlay.setPadding(new Insets(50)); // Padding opcional para espaçamento interno

        root.getChildren().addAll(leftStats);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public Origin getChosenOrigin() { return ChosenOrigin; }

    public StackPane getRoot() {
        return root;
    }
}


