package ui;

import Entitys.Origin;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import repository.Repository;

import java.io.IOException;

public class App extends Application {
    private Scene mainScene;
    private StackPane rootPane;
    private Repository repo;


    @Override
    public void start(Stage primaryStage) throws IOException {
        this.rootPane = new StackPane();
        this.mainScene = new Scene(rootPane, 1920, 1080);

        //Inicializar todos os dados
        repo = new Repository();
        repo.LoadAll();

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Bloodborne");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        showStartMenu();
        primaryStage.show();
    }

    private void showStartMenu() throws IOException {
        StartMenuPane menuPane = new StartMenuPane(
                () -> {
                    this.showOriginScreen();
                },
                () -> {
                    this.showBattlePane();
                },
                () -> {

                },
                () -> {
                    Platform.exit();
                }
        );

        rootPane.getChildren().setAll(menuPane.getRoot());
    }

    private void showBattlePane() {
        BattlePane battlePane = new BattlePane(
                () -> {

                },
                () -> {

                },
                () -> {

                },
                () -> {

                }
        );

        transitionTo(battlePane.getRoot());
    }

    //private void showInvetoryScreen() {
    //    InventoryPane inventoryPane = new InventoryPane(repo);
    //     transitionTo(inventoryPane.getRoot());
    //}

    private void showLoadingScreen() {
        LoadingPane LoadPane = new LoadingPane();
        transitionTo(LoadPane.getRoot());
    }

    private void showOriginScreen() {
        final OriginPane[] originPaneHolder = new OriginPane[1];

        originPaneHolder[0] = new OriginPane(repo, () -> {
            Origin chosenOrigin = originPaneHolder[0].getChosenOrigin();

            if (chosenOrigin != null) {
                InventoryPane inventoryPane = new InventoryPane(repo, chosenOrigin);
                transitionTo(inventoryPane.getRoot());
            } else {
                System.out.println("AAAAAAAA");
            }
        });

        transitionTo(originPaneHolder[0].getRoot());
    }

    private void showBattleScreen() throws IOException {
        BattlePane BattlePane = new BattlePane(repo);
        transitionTo(BattlePane.getRoot());
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
