import edu.jhu.espresso.client.domain.CharacterNames;
import edu.jhu.espresso.client.domain.Suggestion;
import edu.jhu.espresso.client.domain.Weapon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerSuggestion {
    Suggestion suggestion = new Suggestion();

    @FXML private ToggleGroup sugWeap1;
    @FXML private ToggleGroup sugChar1;

    //For suggestions, no buttons should be restricted from selection. All choices are valid
    @FXML private RadioButton COLONEL_MUSTARD;
    @FXML private RadioButton PROFESSOR_PLUM;
    @FXML private RadioButton MR_GREEN;
    @FXML private RadioButton MRS_WHITE;
    @FXML private RadioButton MRS_PEACOCK;
    @FXML private RadioButton MISS_SCARLET;
    @FXML private RadioButton REVOLVER;
    @FXML private RadioButton DAGGER;
    @FXML private RadioButton LEAD_PIPE;
    @FXML private RadioButton ROPE;
    @FXML private RadioButton CANDLESTICK;
    @FXML private RadioButton WRENCH;
    @FXML private Button suggest;
    @FXML private Button cancelSuggestion;

    @FXML private Text suggestRoom;

    public void setSuggestText(String text) { // doesn't work, breaks it
        //suggestRoom = new Text();
        suggestRoom.setText("text");
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
        // set suggested room to active player's current location
        suggestion.printToString();
        Parent root;        // TEMPORARY, to test Disprove Suggestion window
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("DisproveSuggestion.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Suggestion");
            stage.setScene(new Scene(root, 1000, 364));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}