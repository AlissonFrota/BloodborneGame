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
import javafx.util.StringConverter;
import repository.Repository;

public class OriginPane {
    private StackPane root;
    private ComboBox<Origin> originComboBox;

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

            }
        });


        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        HBox.setMargin(titleLabel, new Insets(0, 100, 0, 0));
        hBox.getChildren().addAll(titleLabel, originComboBox);


        VBox contentOverlay = new VBox(hBox);
        contentOverlay.setAlignment(Pos.TOP_CENTER);
        contentOverlay.setPadding(new Insets(50)); // Padding opcional para espaçamento interno


        root.getChildren().addAll(banner, contentOverlay);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public StackPane getRoot() {
        return root;
    }
}


