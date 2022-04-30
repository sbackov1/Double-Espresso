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

public class WinningScreenController {

    @FXML public Text Winner;
    @FXML public Button returnLobby;
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

    public void setWinner(String winner) {
        Winner.setText(winner);
    }

    @FXML void goToFoyer(ActionEvent event) {
        try {
            FXMLLoader fxmlFoyer = new FXMLLoader();
            fxmlFoyer.setLocation(getClass().getClassLoader().getResource("GameFoyer.fxml"));
            Pane foyerPane = fxmlFoyer.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Foyer");
            stage.setScene(new Scene(foyerPane, 700, 364));
            stage.show();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void goToLobby(ActionEvent event) {
        try {
            FXMLLoader fxmlLobby = new FXMLLoader();
            fxmlLobby.setLocation(getClass().getClassLoader().getResource("GameLobby.fxml"));
            Pane lobbyPane = fxmlLobby.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Lobby");
            stage.setScene(new Scene(lobbyPane, 600, 500));
            stage.show();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
