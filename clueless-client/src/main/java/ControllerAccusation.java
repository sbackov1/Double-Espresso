import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class ControllerAccusation {

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
    private RadioButton MISS_SCARLETT;

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
    void cancelAccusation(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void makeAccusation(ActionEvent event) {

    }

}