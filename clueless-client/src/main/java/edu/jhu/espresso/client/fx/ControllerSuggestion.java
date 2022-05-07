package edu.jhu.espresso.client.fx;

import edu.jhu.espresso.client.domain.AllCardEnums;
import edu.jhu.espresso.client.domain.GameEvents.Suggestion;
import edu.jhu.espresso.client.domain.GamePieces.Card;
import edu.jhu.espresso.client.domain.GamePieces.CharacterNames;
import edu.jhu.espresso.client.domain.GamePieces.RoomNames;
import edu.jhu.espresso.client.domain.GamePieces.Weapon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ControllerSuggestion extends Thread {
    Suggestion suggestion = new Suggestion();
    private int columnIndex;
    private int rowIndex;
    private GameboardController gameboardController;
    boolean suggestionMade = false;
    List<AllCardEnums> allCards = Arrays.asList(AllCardEnums.values());

    @FXML public ToggleGroup sugWeap1;
    @FXML public ToggleGroup sugChar1;

    @FXML public RadioButton COLONEL_MUSTARD;
    @FXML public RadioButton PROFESSOR_PLUM;
    @FXML public RadioButton MR_GREEN;
    @FXML public RadioButton MRS_WHITE;
    @FXML public RadioButton MRS_PEACOCK;
    @FXML public RadioButton MISS_SCARLET;
    @FXML public RadioButton REVOLVER;
    @FXML public RadioButton DAGGER;
    @FXML public RadioButton LEAD_PIPE;
    @FXML public RadioButton ROPE;
    @FXML public RadioButton CANDLESTICK;
    @FXML public RadioButton WRENCH;
    @FXML public Button suggest;
    @FXML public Button cancelSuggestion;

    @FXML public Text suggestRoom;
    private FXMLLoader fxmlLoader;

    public void setSuggestText(String text) {  // Used in edu.jhu.espresso.client.fx.GameboardController to set room text of SuggestionMenu
        suggestRoom.setText("text");
    }

    public void disableExtraCards(List<String> extraCards) {
        for(AllCardEnums enums : allCards) {
            for(String cardName : extraCards) {
                if(cardName.equals(String.valueOf(enums))) {
                    returnRadioButton(String.valueOf(enums)).ifPresent(button -> button.setDisable(true));
                }
            }
        }
    }

    private Optional<RadioButton> returnRadioButton(String rb){

        RadioButton btn = null;
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
        }

        return Optional.ofNullable(btn);
    }

    @FXML
    void greenClicked(ActionEvent event) {
        suggestion.setCharacter(CharacterNames.MR_GREEN);
    }

    @FXML
    void mustardClicked(ActionEvent event) {
        suggestion.setCharacter(CharacterNames.COLONEL_MUSTARD);
    }

    @FXML
    void peacockClicked(ActionEvent event) {
        suggestion.setCharacter(CharacterNames.MRS_PEACOCK);
    }

    @FXML
    void plumClicked(ActionEvent event) {
        suggestion.setCharacter(CharacterNames.PROFESSOR_PLUM);
    }

    @FXML
    void scarletClicked(ActionEvent event) {
        suggestion.setCharacter(CharacterNames.MISS_SCARLET);
    }

    @FXML
    void whiteClicked(ActionEvent event) {
        suggestion.setCharacter(CharacterNames.MRS_WHITE);
    }

    @FXML
    void candlestickClicked(ActionEvent event) {
        suggestion.setWeapon(Weapon.CANDLESTICK);
    }

    @FXML
    void daggerClicked(ActionEvent event) {
        suggestion.setWeapon(Weapon.DAGGER);
    }

    @FXML
    void revolverClicked(ActionEvent event) {
        suggestion.setWeapon(Weapon.REVOLVER);
    }

    @FXML
    void ropeClicked(ActionEvent event) {
        suggestion.setWeapon(Weapon.ROPE);
    }

    @FXML
    void wrenchClicked(ActionEvent event) {
        suggestion.setWeapon(Weapon.WRENCH);
    }

    @FXML
    void leadpipeClicked(ActionEvent event) {
        suggestion.setWeapon(Weapon.LEAD_PIPE);
    }

    @FXML
    void exitWindow(ActionEvent event) { // cancel suggestion action button
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void sendSuggestion(ActionEvent event) { // confirm suggestion action button
        suggestion.setRoomNames(RoomNames.valueOf(gameboardController.getPlayerLocation().name()));
        gameboardController.move.setDisable(true);
        gameboardController.makeSuggestion.setDisable(true);
        suggestionMade = true;
        exitWindow(event);
    }

    public void setGameboardController(GameboardController gameboardController)
    {
        this.gameboardController = gameboardController;
    }

    public Suggestion getSuggestion()
    {
        return suggestion;
    }

    @Override
    public void run()
    {
        while(!suggestionMade)
        {
        }
    }
}