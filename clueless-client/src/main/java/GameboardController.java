import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
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
    private Rectangle STUDY;

    @FXML
    private Rectangle LOUNGE;

    @FXML
    private Rectangle HALL;

    @FXML
    private Rectangle LIBRARY;

    @FXML
    private Rectangle BILLARD_ROOM;

    @FXML
    private Rectangle DINING_ROOM;

    @FXML
    private Rectangle BALLROOM;

    @FXML
    private Rectangle CONSERVATORY;

    @FXML
    private Rectangle KITCHEN;

    @FXML
    private Rectangle H12;

    @FXML
    private Rectangle H9;

    @FXML
    private Rectangle H8;

    @FXML
    private Rectangle H10;

    @FXML
    private Rectangle H11;

    @FXML
    private Rectangle H6;

    @FXML
    private Rectangle H5;

    @FXML
    private Rectangle H4;

    @FXML
    private Rectangle H3;

    @FXML
    private Rectangle H2;

    @FXML
    private Rectangle H1;

    @FXML
    private Rectangle H7;

    @FXML
    void ballroomClicked(MouseEvent event) {

    }

    @FXML
    void billardClicked(MouseEvent event) {

    }

    @FXML
    void conservatoryClicked(MouseEvent event) {

    }

    @FXML
    void diningClicked(MouseEvent event) {

    }
    @FXML
    void studyClicked(MouseEvent event) {

    }

    @FXML
    void hallClicked(MouseEvent event) {

    }

    @FXML
    void kitchenClicked(MouseEvent event) {

    }

    @FXML
    void libraryClicked(MouseEvent event) {

    }

    @FXML
    void loungeClicked(MouseEvent event) {

    }

    @FXML
    void h1Clicked(MouseEvent event) {

    }

    @FXML
    void h2Clicked(MouseEvent event) {

    }

    @FXML
    void h3Clicked(MouseEvent event) {

    }

    @FXML
    void h4Clicked(MouseEvent event) {

    }

    @FXML
    void h5Clicked(MouseEvent event) {

    }

    @FXML
    void h6Clicked(MouseEvent event) {

    }

    @FXML
    void h7Clicked(MouseEvent event) {

    }

    @FXML
    void h8Clicked(MouseEvent event) {

    }

    @FXML
    void h9Clicked(MouseEvent event) {

    }

    @FXML
    void h10Clicked(MouseEvent event) {

    }

    @FXML
    void h11Clicked(MouseEvent event) {

    }

    @FXML
    void h12Clicked(MouseEvent event) {

    }

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
