package edu.jhu.espresso.client.domain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception, IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("gameboardTest.fxml"));
        primaryStage.setTitle("Clue-Less");
        primaryStage.setScene(new Scene(root, 785, 594));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
