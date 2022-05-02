package edu.jhu.espresso.client.domain;

import edu.jhu.espresso.client.fx.GameboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("gameboardTest.fxml"));
        Pane gameBoardPane = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Clue-Less Suggestion");
        stage.setScene(new Scene(gameBoardPane, 1200, 750));
        stage.show();
        GameboardController gameboardController = fxmlLoader.getController();
        gameboardController.initialize();
        gameboardController.setCharacters();

        Thread thread = new Thread(() -> {
            int num = 1;
            for (int i = 0; i < 5; i++) {
                gameboardController.makeSampleNotebook(num);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (num > 1) {
                    num = 1;
                } else num++;
            }
        });

        thread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

