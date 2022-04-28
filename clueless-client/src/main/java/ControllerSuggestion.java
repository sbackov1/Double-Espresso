import edu.jhu.espresso.client.domain.CharacterNames;
import edu.jhu.espresso.client.domain.Suggestion;
import edu.jhu.espresso.client.domain.Weapon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControllerSuggestion {
    Suggestion suggestion = new Suggestion();

    @FXML
    private RadioButton COLONEL_MUSTARD;

    @FXML
    private ToggleGroup sugChar1;

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
    private ToggleGroup sugWeap1;

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
    private Button suggest;

    @FXML
    private Button cancelSuggestion;

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
    }

}