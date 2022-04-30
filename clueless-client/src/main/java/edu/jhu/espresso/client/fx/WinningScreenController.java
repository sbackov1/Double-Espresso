package edu.jhu.espresso.client.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

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

    }

    @FXML void goToLobby(ActionEvent event) {

    }
}
