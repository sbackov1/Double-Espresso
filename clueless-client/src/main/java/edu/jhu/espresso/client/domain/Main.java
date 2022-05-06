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
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("Gameboard.fxml"));
        Pane gameBoardPane = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(gameBoardPane, 1200, 750));
        stage.show();
        GameboardController gameboardController = fxmlLoader.getController();
        gameboardController.initialize();
        gameboardController.setCharacters();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

