package edu.jhu.espresso.client.fx;

import edu.jhu.espresso.client.domain.AllCardEnums;
import edu.jhu.espresso.client.domain.GameEvents.SuggestionResponse;
import edu.jhu.espresso.client.domain.GamePieces.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerDisprove {
    private final SuggestionResponse response = new SuggestionResponse();

    @FXML public ToggleGroup disproveGroup;

    //only hand cards matching suggestion should be pickable for disproving suggestions
    ArrayList<RadioButton> buttons = new ArrayList<>();
    List<AllCardEnums> allCards = Arrays.asList(AllCardEnums.values());

    @FXML public RadioButton REVOLVER;
    @FXML public RadioButton DAGGER;
    @FXML public RadioButton LEAD_PIPE;
    @FXML public RadioButton ROPE;
    @FXML public RadioButton CANDLESTICK;
    @FXML public RadioButton WRENCH;
    @FXML public RadioButton COLONEL_MUSTARD;
    @FXML public RadioButton PROFESSOR_PLUM;
    @FXML public RadioButton MR_GREEN;
    @FXML public RadioButton MRS_WHITE;
    @FXML public RadioButton MRS_PEACOCK;
    @FXML public RadioButton MISS_SCARLET;
    @FXML public RadioButton KITCHEN;
    @FXML public RadioButton HALL;
    @FXML public RadioButton BALLROOM;
    @FXML public RadioButton CONSERVATORY;
    @FXML public RadioButton DINING_ROOM;
    @FXML public RadioButton BILLIARD_ROOM;
    @FXML public RadioButton LIBRARY;
    @FXML public RadioButton LOUNGE;
    @FXML public RadioButton STUDY;
    @FXML public Button disprove;
    @FXML public Button cancelDisprove;

    @FXML
    void pass(ActionEvent event) { // cannot disprove, next player
        response.setCantDisprove();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void sendDisprove(ActionEvent event) { // show card to suggesting player
        response.printToString();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void initialize(List<Card> handCards, List<Card> suggestionCards) {
        response.setValidCards(handCards);
        List<Card> enabledCards = new ArrayList<>(handCards);
        enabledCards.retainAll(suggestionCards);

        if (enabledCards.isEmpty()) {
            disprove.setDisable(true);
        }
        else {
            cancelDisprove.setDisable(true);
        }

        for(AllCardEnums enums : allCards) {
            returnRadioButton(String.valueOf(enums)).setDisable(true);
            for(Card card : enabledCards) {
                if(card.getName().equals(String.valueOf(enums))) {
                    returnRadioButton(String.valueOf(enums)).setDisable(false);
                }

                }
            }
        }


    public RadioButton returnRadioButton(String rb){

        RadioButton btn;
        switch (rb) {

            case "PROFESSOR_PLUM":
                btn = PROFESSOR_PLUM;
                break;
            case "MRS_WHITE":
                btn = MRS_WHITE;
                break;
            case "MR_GREEN":
                btn = MR_GREEN;
                break;
            case "MRS_PEACOCK":
                btn = MRS_PEACOCK;
                break;
            case "MISS_SCARLET":
                btn = MISS_SCARLET;
                break;
            case "COLONEL_MUSTARD":
                btn = COLONEL_MUSTARD;
                break;
            case "KITCHEN":
                btn = KITCHEN;
                break;
            case "HALL":
                btn = HALL;
                break;
            case "BALLROOM":
                btn = BALLROOM;
                break;
            case "CONSERVATORY":
                btn = CONSERVATORY;
                break;
            case "DINING_ROOM":
                btn = DINING_ROOM;
                break;
            case "BILLIARD_ROOM":
                btn = BILLIARD_ROOM;
                break;
            case "LIBRARY":
                btn = LIBRARY;
                break;
            case "LOUNGE":
                btn = LOUNGE;
                break;
            case "STUDY":
                btn = STUDY;
                break;
            case "REVOLVER":
                btn = REVOLVER;
                break;
            case "DAGGER":
                btn = DAGGER;
                break;
            case "LEAD_PIPE":
                btn = LEAD_PIPE;
                break;
            case "ROPE":
                btn = ROPE;
                break;
            case "CANDLESTICK":
                btn = CANDLESTICK;
                break;
            case "WRENCH":
                btn = WRENCH;
                break;
            default:
                throw new IllegalArgumentException(this + "not mapped to button");
        }

        return btn;
    }

    @FXML
    void ballroomClicked(ActionEvent event) {       // ROOM START
        response.setCardString("BALLROOM");
    }

    @FXML
    void billardClicked(ActionEvent event) {
        response.setCardString("BILLIARD_ROOM");
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

    public SuggestionResponse getResponse()
    {
        return response;
    }
}