package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    private Stage primaryStage;
    private String css;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Bloodborne");
        primaryStage.setFullScreenExitHint("");

        css = Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm();

        showStartMenu();
        primaryStage.show();
    }

    private void showStartMenu() throws IOException {
        StartMenuPane menuPane = new StartMenuPane(()-> {
            try{

                this.showLoadingScreen(this::showOriginScreen);
            } catch(Exception e){

            }
        });

        Scene scene = new Scene(menuPane.getRoot(), 1920, 1080);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
    }

    private void showLoadingScreen(Runnable run) throws IOException, InterruptedException {
        LoadingPane LoadPane = new LoadingPane();
        Scene scene = new Scene(LoadPane.getRoot(), 1920, 1080);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        Thread.sleep(3000);
        run.run();
    }

    private void showOriginScreen() {
        OriginPane OriginPane = new OriginPane();
        Scene scene = new Scene(OriginPane.getRoot(), 1920, 1080);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
    }

}
