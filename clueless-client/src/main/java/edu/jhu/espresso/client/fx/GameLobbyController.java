package edu.jhu.espresso.client.fx;

import edu.jhu.espresso.client.domain.GamePieces.CharacterNames;
import edu.jhu.espresso.client.domain.GamePieces.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

public class GameLobbyController {

    @FXML public Text gameID;
    @FXML public Text playerID;
    @FXML public Text turnTimer;
    @FXML private ChoiceBox<Integer> timeSelect;
    @FXML private ChoiceBox<String> characterSelect;
    @FXML private Button charConfirm;
    @FXML private Button timeButton;
    @FXML private Button startButton;
    @FXML private TextField username;
    //ObservableList<String> charList = FXCollections.observableArrayList();
    List<CharacterNames> characterNamesList = Arrays.asList(CharacterNames.values());
    Player player = new Player(1, 1); // dummy player

    public void testMethod()
    {
        timeSelect.getItems().addAll(1, 2, 3);
        //charList.addAll(String.valueOf(characterNamesList));
        characterSelect.getItems().addAll(String.valueOf(characterNamesList));
    }

    @FXML
    void sendUsername(ActionEvent event) {

    }

    @FXML
    void startGame(ActionEvent event) {
        // create gameboard
    }

    @FXML
    void confirmChar(ActionEvent event) {
        player.setCharacter(CharacterNames.valueOf(characterSelect.getValue()));
        // remove character from choicebox
    }

    @FXML
    void timeSet(ActionEvent event) {

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
