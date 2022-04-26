import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GameboardController {


    @FXML
    private Button exit;

    @FXML
    private Button makeSuggestion;

    @FXML
    private Button makeAccusation;

    @FXML
    private Button move;

    @FXML
    public void exitGame(ActionEvent event) {  // leave the game : will either exit program completely, or boot to foyer

    }

    @FXML
    public void openAccusationWindow(ActionEvent event) {  // AccusationMenu2.fxml
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("AccusationMenu2.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Accusation");
            stage.setScene(new Scene(root, 1000, 364));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openSuggestionWindow(ActionEvent event) {  // SuggestionMenu.fxml
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("SuggestionMenu.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Suggestion");
            stage.setScene(new Scene(root, 1000, 364));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sendMove(ActionEvent event) {  // send move selected on gameboard to server

    }

}
