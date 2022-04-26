import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControllerSuggestion {

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
    private RadioButton MISS_SCARLETT;

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
    void exitWindow(ActionEvent event) { // cancel suggestion action button
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void sendSuggestion(ActionEvent event) { // confirm suggestion action button

    }

}