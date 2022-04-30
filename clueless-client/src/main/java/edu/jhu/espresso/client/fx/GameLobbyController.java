package edu.jhu.espresso.client.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

public class GameLobbyController {

    @FXML public Text gameID;
    @FXML public Text playerID;
    @FXML public Text turnTimer;
    @FXML private ChoiceBox<?> timeSelect;
    @FXML private ChoiceBox<?> characterSelect;
    @FXML private Button charConfirm;

    @FXML
    void confirmChar(ActionEvent event) {

    }
    public void setGameIDText(String gID) {
        gameID.setText(gID);
    }

    public void setPlayerIDText(String pID) {
        playerID.setText(pID);
    }

    public void setTurnTimerText(String time) {
        turnTimer.setText(time);
    }
}
