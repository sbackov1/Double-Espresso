package edu.jhu.espresso.client.fx;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GameLobbyController {

    @FXML public Text gameID;
    @FXML public Text playerID;
    @FXML public Text turnTimer;

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
