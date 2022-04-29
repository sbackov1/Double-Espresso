import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LosingScreenController {

    @FXML public Button returnToGame;
    @FXML public Button returnFoyer;
    @FXML public Text murderer;
    @FXML public Text murderWeapon;
    @FXML public Text murderLocation;

    public void setMurdererText(String character) {
        murderer.setText(character);
    }

    public void setMurderWeapon(String weapon) {
        murderWeapon.setText(weapon);
    }

    public void setMurderLocation(String room) {
        murderLocation.setText(room);
    }

    @FXML void returnFoyer(ActionEvent event) {
        // would exit game completely and return to the gameFoyer
        // would operate the same way as Leave Game button in GameboardController
    }

    @FXML void returnGame(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
