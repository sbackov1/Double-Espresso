package edu.jhu.espresso.client.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LosingScreenController {

    @FXML public Button returnToGame;
    @FXML public Button returnFoyer;
    @FXML public Text murderer;
    @FXML public Text murderWeapon;
    @FXML public Text murderLocation;

    public void setMurdererText(String character) {
        murderer.setText(character);
    }

    public void setMurderWeapon(String weapon) {
        murderWeapon.setText(weapon);
    }

    public void setMurderLocation(String room) {
        murderLocation.setText(room);
    }

    @FXML void returnFoyer(ActionEvent event) {
        try {
            FXMLLoader fxmlF = new FXMLLoader();
            fxmlF.setLocation(getClass().getClassLoader().getResource("GameFoyer.fxml"));
            Pane foyerPane = fxmlF.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Foyer");
            stage.setScene(new Scene(foyerPane, 700, 364));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        // would exit game completely and return to the gameFoyer
        // would operate the same way as Leave Game button in edu.jhu.espresso.client.fx.GameboardController
    }

    @FXML void returnGame(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
