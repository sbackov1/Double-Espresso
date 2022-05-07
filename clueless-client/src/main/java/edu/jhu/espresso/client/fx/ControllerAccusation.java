package edu.jhu.espresso.client.fx;

import edu.jhu.espresso.client.domain.AllCardEnums;
import edu.jhu.espresso.client.domain.GameEvents.Accusation;
import edu.jhu.espresso.client.domain.GamePieces.Character;
import edu.jhu.espresso.client.domain.GamePieces.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ControllerAccusation {
    Accusation accusation = new Accusation();
    CaseDetails file = new CaseDetails(); // dummy case file
    Player player = new Player(1, 1); // dummy player
    List<AllCardEnums> allCards = Arrays.asList(AllCardEnums.values());

    @FXML public ToggleGroup charGroup;
    @FXML public ToggleGroup roomGroup;
    @FXML public ToggleGroup weapGroup;

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
    @FXML public RadioButton KITCHEN;
    @FXML public RadioButton HALL;
    @FXML public RadioButton BALLROOM;
    @FXML public RadioButton CONSERVATORY;
    @FXML public RadioButton DINING_ROOM;
    @FXML public RadioButton BILLIARD_ROOM;
    @FXML public RadioButton LIBRARY;
    @FXML public RadioButton LOUNGE;
    @FXML public RadioButton STUDY;
    @FXML public Button accuse;
    @FXML public Button exit;

    public void disableExtraCards(List<String> extraCards) {
        for(AllCardEnums enums : allCards) {
            for(String cardName : extraCards) {
                if(cardName.equals(String.valueOf(enums))) {
                    returnRadioButton(String.valueOf(enums)).setDisable(true);
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
                throw new IllegalArgumentException(rb + "not mapped to button");
        }

        return btn;
    }

    @FXML
    void ballroomClicked(ActionEvent event) {
        accusation.setRoomNames(RoomNames.BALLROOM);
    }

    @FXML
    void billardClicked(ActionEvent event) {
        accusation.setRoomNames(RoomNames.BILLIARD_ROOM);
    }

    @FXML
    void conservatoryClicked(ActionEvent event) {   // ROOM START
        accusation.setRoomNames(RoomNames.CONSERVATORY);
    }

    @FXML
    void diningClicked(ActionEvent event) {
        accusation.setRoomNames(RoomNames.DINING_ROOM);
    }

    @FXML
    void hallClicked(ActionEvent event) {
        accusation.setRoomNames(RoomNames.HALL);
    }

    @FXML
    void kitchenClicked(ActionEvent event) {
        accusation.setRoomNames(RoomNames.KITCHEN);
    }

    @FXML
    void libraryClicked(ActionEvent event) {
        accusation.setRoomNames(RoomNames.LIBRARY);
    }

    @FXML
    void loungeClicked(ActionEvent event) {
        accusation.setRoomNames(RoomNames.LOUNGE);
    }

    @FXML
    void studyClicked(ActionEvent event) {
        accusation.setRoomNames(RoomNames.STUDY);
    }

    @FXML
    void candlestickClicked(ActionEvent event) { // WEAPON START
        accusation.setWeapon(Weapon.CANDLESTICK);
    }

    @FXML
    void daggerClicked(ActionEvent event) {
        accusation.setWeapon(Weapon.DAGGER);
    }

    @FXML
    void leadpipeClicked(ActionEvent event) {
        accusation.setWeapon(Weapon.LEAD_PIPE);
    }

    @FXML
    void revolverClicked(ActionEvent event) {
        accusation.setWeapon(Weapon.REVOLVER);
    }

    @FXML
    void ropeClicked(ActionEvent event) {
        accusation.setWeapon(Weapon.ROPE);
    }

    @FXML
    void wrenchClicked(ActionEvent event) {
        accusation.setWeapon(Weapon.WRENCH);
    }

    @FXML
    void greenClicked(ActionEvent event) {  // CHARACTER START
        accusation.setCharacter(Character.MR_GREEN);
    }

    @FXML
    void mustardClicked(ActionEvent event) {
        accusation.setCharacter(Character.COLONEL_MUSTARD);
    }

    @FXML
    void peacockClicked(ActionEvent event) {
        accusation.setCharacter(Character.MRS_PEACOCK);
    }

    @FXML
    void plumClicked(ActionEvent event) {
        accusation.setCharacter(Character.PROFESSOR_PLUM);
    }

    @FXML
    void scarletClicked(ActionEvent event) {
        accusation.setCharacter(Character.MISS_SCARLET);
    }

    @FXML
    void whiteClicked(ActionEvent event) {
        accusation.setCharacter(Character.MRS_WHITE);
    }

    @FXML
    void cancelAccusation(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void makeAccusation(ActionEvent event) {
        file.setWeapon(Weapon.CANDLESTICK); // Dummy values
        file.setRoom(RoomNames.LIBRARY);
        file.setCharacterNames(CharacterNames.MRS_PEACOCK);
        player.setCharacter(CharacterNames.MISS_SCARLET);
        // show case file to player
        // determine if accusation true
        // call either incorrectAcc() or correctAcc(), below is testing menus call correctly
        //correctAcc();
//        incorrectAcc();
        accusation.printToString();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void incorrectAcc() { // Shows LosingScreen.fxml
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(getClass().getClassLoader().getResource("LosingScreen.fxml"));
            Pane losePane = fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less");
            stage.setScene(new Scene(losePane, 750, 600));
            LosingScreenController lose = fxml.getController();
            lose.setMurdererText(String.valueOf(file.getCharacterNames()));
            lose.setMurderLocation(String.valueOf(file.getRoom()));
            lose.setMurderWeapon(String.valueOf(file.getWeapon()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void correctAcc() { // Shows WinningScreen.fxml
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(getClass().getClassLoader().getResource("WinningScreen.fxml"));
            Pane winPane = fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less");
            stage.setScene(new Scene(winPane, 750, 750));
            WinningScreenController win = fxml.getController();
            win.setMurdererText(String.valueOf(file.getCharacterNames()));
            win.setMurderLocation(String.valueOf(file.getRoom()));
            win.setMurderWeapon(String.valueOf(file.getWeapon()));
            win.setWinner(String.valueOf(player.getCharacter()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Accusation getAccusation()
    {
        return accusation;
    }
}