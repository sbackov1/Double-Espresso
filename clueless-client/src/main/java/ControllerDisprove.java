import edu.jhu.espresso.client.domain.SuggestionResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControllerDisprove {
    SuggestionResponse response = new SuggestionResponse();

    @FXML
    private ToggleGroup disproveGroup;

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
    private RadioButton BILLARD_ROOM;

    @FXML
    private RadioButton LIBRARY;

    @FXML
    private RadioButton LOUNGE;

    @FXML
    private RadioButton STUDY;

    @FXML
    private Button disprove;

    @FXML
    private Button cancelDisprove;

    @FXML
    void pass(ActionEvent event) { // cannot disprove, next player
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void sendDisprove(ActionEvent event) { // show card to suggesting player

    }
}