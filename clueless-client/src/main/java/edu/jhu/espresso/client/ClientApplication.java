package edu.jhu.espresso.client;

import edu.jhu.espresso.client.fx.GameboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientApplication extends Application
{
    public static boolean log = true;

    public static void logMessage(String message)
    {
        if(log)
        {
            System.out.println(message);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("Gameboard.fxml"));
        Pane gameBoardPane = fxmlLoader.load();
        primaryStage.setTitle("Clue-Less");
        primaryStage.setScene(new Scene(gameBoardPane, 1200, 800));
        primaryStage.show();
        GameboardController gameboardController = fxmlLoader.getController();
        gameboardController.initialize();
        gameboardController.statusBar.setEditable(false);

        new Thread(new ClueLessClient("localhost", 8080, gameboardController)).start();
    }
}
