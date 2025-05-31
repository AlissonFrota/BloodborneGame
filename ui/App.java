package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Bloodborne");
        primaryStage.setFullScreenExitHint("");

        String css = Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm();

        showStartMenu(css);
        primaryStage.show();
    }

    private void showStartMenu(String css) throws IOException {
        StartMenuPane menuPane = new StartMenuPane();
        Scene scene = new Scene(menuPane.getRoot(), 1920, 1080);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
    }

}
