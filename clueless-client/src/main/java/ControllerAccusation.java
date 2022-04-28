import edu.jhu.espresso.client.domain.Accusation;
import edu.jhu.espresso.client.domain.Character;
import edu.jhu.espresso.client.domain.RoomNames;
import edu.jhu.espresso.client.domain.Weapon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class ControllerAccusation {
    Accusation accusation = new Accusation();

    @FXML
    private RadioButton COLONEL_MUSTARD;

    @FXML
    private RadioButton PROFESSOR_PLUM;

    @FXML
    private RadioButton MR_GREEN;

    @FXML
    private RadioButton MRS_WHITE;

    @FXML
    private RadioButton MRS_PEACOCK;

    @FXML
    private RadioButton MISS_SCARLET;

    @FXML
    private RadioButton REVOLVER;

    @FXML
    private RadioButton DAGGER;

    @FXML
    private RadioButton LEAD_PIPE;

    @FXML
    private RadioButton ROPE;

    @FXML
    private RadioButton CANDLESTICK;

    @FXML
    private RadioButton WRENCH;

    @FXML
    private RadioButton KITCHEN;

    @FXML
    private RadioButton HALL;

    @FXML
    private RadioButton BALLROOM;

    @FXML
    private RadioButton CONSERVATORY;

    @FXML
    private RadioButton DINING_ROOM;

    @FXML
    private RadioButton BILLIARD_ROOM;

    @FXML
    private RadioButton LIBRARY;

    @FXML
    private RadioButton LOUNGE;

    @FXML
    private RadioButton STUDY;

    @FXML
    private Button accuse;

    @FXML
    private Button exit;

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
        accusation.printToString();
    }

}