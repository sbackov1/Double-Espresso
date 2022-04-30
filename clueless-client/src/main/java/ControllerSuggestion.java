import edu.jhu.espresso.client.domain.CharacterNames;
import edu.jhu.espresso.client.domain.RoomNames;
import edu.jhu.espresso.client.domain.Suggestion;
import edu.jhu.espresso.client.domain.Weapon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerSuggestion {
    Suggestion suggestion = new Suggestion();
    private int columnIndex;
    private int rowIndex;

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

    public void setSuggestText(String text) {  // Used in GameboardController to set room text of SuggestionMenu
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
        suggestion.setRoomNames(RoomNames.BALLROOM); // temporary, set location here with EnumMap
        suggestion.printToString();
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(getClass().getResource("DisproveSuggestion.fxml"));
            Pane disprovePane = fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Suggestion");
            stage.setScene(new Scene(disprovePane, 1000, 364));
            stage.show();
            GameboardController board = fxml.getController(); // throw NullPointerException but still runs?
            columnIndex = GridPane.getColumnIndex(board.MISS_SCARLET);
            rowIndex = GridPane.getRowIndex(board.MISS_SCARLET);
            board.moveSuggested(board.MR_GREEN, columnIndex, rowIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}