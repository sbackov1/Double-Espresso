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

    }

    @FXML
    void ballroomClicked(ActionEvent event) {       // ROOM START
        //response.setCardString(String BALLROOM);
    }

    @FXML
    void billardClicked(ActionEvent event) {

    }

    @FXML
    void conservatoryClicked(ActionEvent event) {

    }

    @FXML
    void diningClicked(ActionEvent event) {

    }

    @FXML
    void hallClicked(ActionEvent event) {

    }

    @FXML
    void kitchenClicked(ActionEvent event) {

    }

    @FXML
    void libraryClicked(ActionEvent event) {

    }

    @FXML
    void loungeClicked(ActionEvent event) {

    }

    @FXML
    void studyClicked(ActionEvent event) {

    }

    @FXML
    void greenClicked(ActionEvent event) {          // CHARACTER START

    }

    @FXML
    void mustardClicked(ActionEvent event) {

    }

    @FXML
    void peacockClicked(ActionEvent event) {

    }

    @FXML
    void plumClicked(ActionEvent event) {

    }

    @FXML
    void scarletClicked(ActionEvent event) {

    }

    @FXML
    void whiteClicked(ActionEvent event) {

    }

    @FXML
    void candlestickClicked(ActionEvent event) {   // WEAPON SELECT

    }

    @FXML
    void daggerClicked(ActionEvent event) {

    }

    @FXML
    void leadpipeClicked(ActionEvent event) {

    }

    @FXML
    void revolverClicked(ActionEvent event) {

    }

    @FXML
    void ropeClicked(ActionEvent event) {

    }

    @FXML
    void wrenchClicked(ActionEvent event) {

    }

}