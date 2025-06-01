package ui;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    private Scene mainScene;
    private StackPane rootPane;


    @Override
    public void start(Stage primaryStage) throws IOException {
        this.rootPane = new StackPane();
        this.mainScene = new Scene(rootPane, 1920, 1080);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Bloodborne");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        showStartMenu();
        primaryStage.show();
    }

    private void showStartMenu() throws IOException {
        StartMenuPane menuPane = new StartMenuPane(()-> {
            try{

                this.showOriginScreen();
            } catch(Exception _){

            }
        });

        rootPane.getChildren().setAll(menuPane.getRoot());
    }

    //private void showLoadingScreen(Runnable run) throws IOException, InterruptedException {
    //    LoadingPane LoadPane = new LoadingPane();
    //    Scene scene = new Scene(LoadPane.getRoot(), 1920, 1080);
    //    scene.getStylesheets().add(css);
    //    primaryStage.setScene(scene);
    //    primaryStage.setFullScreen(true);
    //    Thread.sleep(3000);
    //    run.run();
    //}

    private void showOriginScreen() {
        OriginPane OriginPane = new OriginPane();
        transitionTo(OriginPane.getRoot());
    }


    private void transitionTo(Node newRoot) {

        Rectangle blackOverlay = new Rectangle(mainScene.getWidth(), mainScene.getHeight(), Color.BLACK);
        blackOverlay.setOpacity(0);


        rootPane.getChildren().add(blackOverlay);

        FadeTransition fadeToBlack = new FadeTransition(Duration.millis(300), blackOverlay);
        fadeToBlack.setFromValue(0.0);
        fadeToBlack.setToValue(1.0);

        fadeToBlack.setOnFinished(e -> {

            rootPane.getChildren().remove(blackOverlay);
            rootPane.getChildren().setAll(newRoot);
            rootPane.getChildren().add(blackOverlay);

            FadeTransition fadeFromBlack = new FadeTransition(Duration.millis(300), blackOverlay);
            fadeFromBlack.setFromValue(1.0);
            fadeFromBlack.setToValue(0.0);

            fadeFromBlack.setOnFinished(event -> {
                rootPane.getChildren().remove(blackOverlay);
            });
            fadeFromBlack.play();
        });

        fadeToBlack.play();
    }

}
