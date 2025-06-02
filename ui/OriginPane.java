package ui;

import Entitys.Origin;
import Entitys.Personagem;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.util.StringConverter;
import repository.Repository;

import java.io.IOException;

public class OriginPane {
    private VBox root;
    private ComboBox<Origin> originComboBox;

    public OriginPane(Repository repo) {

        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menu-pane");

        //ImageView banner = new ImageView(new Image(getClass().getResourceAsStream("/images/673124.png")));
        //banner.setPreserveRatio(true);
        //anner.setFitWidth(2500);


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

        originComboBox.setItems(FXCollections.observableArrayList(repo.getOrigins()));

        originComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                //Selecionar origin newVal
            }
        });


        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(titleLabel, new Insets(0, 300, 0, 0));
        hBox.getChildren().addAll(titleLabel, originComboBox);

        root.getChildren().addAll(
            hBox
        );

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public VBox getRoot() {
        return root;
    }
}