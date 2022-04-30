package edu.jhu.espresso.client.fx;

import edu.jhu.espresso.client.domain.SuggestionResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControllerDisprove {
    SuggestionResponse response = new SuggestionResponse();

    @FXML private ToggleGroup disproveGroup;

    //only hand cards matching suggestion should be pickable for disproving suggestions
    @FXML private RadioButton REVOLVER;
    @FXML private RadioButton DAGGER;
    @FXML private RadioButton LEAD_PIPE;
    @FXML private RadioButton ROPE;
    @FXML private RadioButton CANDLESTICK;
    @FXML private RadioButton WRENCH;
    @FXML private RadioButton COLONEL_MUSTARD;
    @FXML private RadioButton PROFESSOR_PLUM;
    @FXML private RadioButton MR_GREEN;
    @FXML private RadioButton MRS_WHITE;
    @FXML private RadioButton MRS_PEACOCK;
    @FXML private RadioButton MISS_SCARLET;
    @FXML private RadioButton KITCHEN;
    @FXML private RadioButton HALL;
    @FXML private RadioButton BALLROOM;
    @FXML private RadioButton CONSERVATORY;
    @FXML private RadioButton DINING_ROOM;
    @FXML private RadioButton BILLARD_ROOM;
    @FXML private RadioButton LIBRARY;
    @FXML private RadioButton LOUNGE;
    @FXML private RadioButton STUDY;
    @FXML private Button disprove;
    @FXML private Button cancelDisprove;

    @FXML
    void pass(ActionEvent event) { // cannot disprove, next player
        response.setCantDisprove();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void sendDisprove(ActionEvent event) { // show card to suggesting player
        response.printToString();
    }

    @FXML
    void ballroomClicked(ActionEvent event) {       // ROOM START
        response.setCardString("BALLROOM");
    }

    @FXML
    void billardClicked(ActionEvent event) {
        response.setCardString("BILLARD_ROOM");

    }

    @FXML
    void conservatoryClicked(ActionEvent event) {
        response.setCardString("CONSERVATORY");
    }

    @FXML
    void diningClicked(ActionEvent event) {
        response.setCardString("DINING_ROOM");
    }

    @FXML
    void hallClicked(ActionEvent event) {
        response.setCardString("HALL");
    }

    @FXML
    void kitchenClicked(ActionEvent event) {
        response.setCardString("KITCHEN");
    }

    @FXML
    void libraryClicked(ActionEvent event) {
        response.setCardString("LIBRARY");
    }

    @FXML
    void loungeClicked(ActionEvent event) {
        response.setCardString("LOUNGE");
    }

    @FXML
    void studyClicked(ActionEvent event) {
        response.setCardString("STUDY");
    }

    @FXML
    void greenClicked(ActionEvent event) {          // CHARACTER START
        response.setCardString("MR_GREEN");
    }

    @FXML
    void mustardClicked(ActionEvent event) {
        response.setCardString("COLONEL_MUSTARD");
    }

    @FXML
    void peacockClicked(ActionEvent event) {
        response.setCardString("MRS_PEACOCK");
    }

    @FXML
    void plumClicked(ActionEvent event) {
        response.setCardString("PROFESSOR_PLUM");
    }

    @FXML
    void scarletClicked(ActionEvent event) {
        response.setCardString("MISS_SCARLET");
    }

    @FXML
    void whiteClicked(ActionEvent event) {
        response.setCardString("MRS_WHITE");
    }

    @FXML
    void candlestickClicked(ActionEvent event) {   // WEAPON SELECT
        response.setCardString("CANDLESTICK");
    }

    @FXML
    void daggerClicked(ActionEvent event) {
        response.setCardString("DAGGER");
    }

    @FXML
    void leadpipeClicked(ActionEvent event) {
        response.setCardString("LEAD_PIPE");
    }

    @FXML
    void revolverClicked(ActionEvent event) {
        response.setCardString("REVOLVER");
    }

    @FXML
    void ropeClicked(ActionEvent event) {
        response.setCardString("ROPE");
    }

    @FXML
    void wrenchClicked(ActionEvent event) {
        response.setCardString("WRENCH");
    }

}