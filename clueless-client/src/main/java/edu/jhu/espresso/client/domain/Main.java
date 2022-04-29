package edu.jhu.espresso.client.domain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("gameboardTest.fxml"));
        primaryStage.setTitle("Clue-Less");
        primaryStage.setScene(new Scene(root, 1000, 750));
        primaryStage.show();
    }



    public static void main(String[] args)  {

        launch(args);

        /*


        int num = 1;
        while (true){
            makeSampleNotebook(num);
            Thread.sleep(5000);
            if (num > 1) { num = 1; }
            else num++;

         */
        }
    }

