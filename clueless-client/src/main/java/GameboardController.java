import edu.jhu.espresso.client.domain.CardDeck;
import edu.jhu.espresso.client.domain.LocationNames;
import edu.jhu.espresso.client.domain.MoveOptions;
import edu.jhu.espresso.client.domain.Notebook;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class GameboardController extends Thread {

  //  private boolean exit1 = false;
    MoveOptions moveOptions = new MoveOptions();
    //ControllerSuggestion control = new ControllerSuggestion();

    @FXML private Button exit;
    @FXML private Button makeSuggestion;
    @FXML private Button makeAccusation;
    @FXML private Button move;

    @FXML private Circle MISS_SCARLET;
    @FXML private Circle MRS_PEACOCK;
    @FXML private Circle PROFESSOR_PLUM;
    @FXML private Circle COLONEL_MUSTARD;
    @FXML private Circle MRS_WHITE;
    @FXML private Circle MR_GREEN;

    @FXML private Rectangle STUDY;
    @FXML private Rectangle LOUNGE;
    @FXML private Rectangle HALL;
    @FXML private Rectangle LIBRARY;
    @FXML private Rectangle BILLARD_ROOM;
    @FXML private Rectangle DINING_ROOM;
    @FXML private Rectangle BALLROOM;
    @FXML private Rectangle CONSERVATORY;
    @FXML private Rectangle KITCHEN;
    @FXML private Rectangle H12;
    @FXML private Rectangle H9;
    @FXML private Rectangle H8;
    @FXML private Rectangle H10;
    @FXML private Rectangle H11;
    @FXML private Rectangle H6;
    @FXML private Rectangle H5;
    @FXML private Rectangle H4;
    @FXML private Rectangle H3;
    @FXML private Rectangle H2;
    @FXML private Rectangle H1;
    @FXML private Rectangle H7;

    @FXML private Rectangle HomeSquareMS;
    @FXML private Rectangle HomeSquareMW;
    @FXML private Rectangle HomeSquareCM;
    @FXML private Rectangle HomeSquareMP;
    @FXML private Rectangle HomeSquareMG;
    @FXML private Rectangle HomeSquarePP;

    @FXML private Text textMustard;
    @FXML private Text textScarlet;
    @FXML private Text textPlum;
    @FXML private Text textWhite;
    @FXML private Text textGreen;
    @FXML private Text textPeacock;
    @FXML private Text textCandlestick;
    @FXML private Text textRope;
    @FXML private Text textDagger;
    @FXML private Text textRevolver;
    @FXML private Text textWrench;
    @FXML private Text textLeadPipe;
    @FXML private Text textStudy;
    @FXML private Text textHall;
    @FXML private Text textLounge;
    @FXML private Text textLibrary;
    @FXML private Text textBillard;
    @FXML private Text textDining;
    @FXML private Text textConservatory;
    @FXML private Text textBallroom;
    @FXML private Text textKitchen;

    @FXML void ballroomClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.BALLROOM);
    }

    @FXML void billardClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.BILLIARD_ROOM);
    }

    @FXML void conservatoryClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.CONSERVATORY);
    }

    @FXML void diningClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.DINING_ROOM);
    }

    @FXML void studyClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.STUDY);
    }

    @FXML void hallClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALL);
    }

    @FXML void kitchenClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.KITCHEN);
    }

    @FXML void libraryClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.LIBRARY);
    }

    @FXML void loungeClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.LOUNGE);
    }

    @FXML void h1Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY1);
    }

    @FXML void h2Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY2);
    }

    @FXML void h3Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY3);
    }

    @FXML void h4Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY4);
    }

    @FXML void h5Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY5);
    }

    @FXML void h6Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY6);
    }

    @FXML void h7Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY7);
    }

    @FXML void h8Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY8);
    }

    @FXML void h9Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY9);
    }

    @FXML void h10Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY10);
    }

    @FXML void h11Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY11);
    }

    @FXML void h12Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY12);
    }

    @FXML public void exitGame(ActionEvent event) {  // leave the game : will either exit program completely, or boot to foyer
        run();
    }


    @FXML public void openAccusationWindow(ActionEvent event) {  // AccusationMenu2.fxml
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

    @FXML public void openSuggestionWindow(ActionEvent event) {  // SuggestionMenu.fxml
        Parent root;
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(getClass().getResource("SuggestionMenu.fxml"));
            Pane suggestionPane = fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Suggestion");
            stage.setScene(new Scene(suggestionPane, 1000, 364));
            stage.show();
            ControllerSuggestion suggest = fxml.getController();
            String test = "Hi!";
            suggest.suggestRoom.setText(test);
            //suggest.setSuggestText("this is a test!");// Exception thown
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void sendMove(ActionEvent event) {  // send move selected on gameboard to server
        moveOptions.printToString();
    }

    /*
    public void setKnownCard(){

        textScarlet.textProperty().
    }

     */
    public static void makeSampleNotebook(int num) {

        CardDeck cd = new CardDeck();
        Notebook notebook = new Notebook(cd);

        switch (num) {

            case 1:

                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "PROFESSOR_PLUM"));
                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "STUDY"));
                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "DAGGER"));

                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "MISS_SCARLET"));
                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "BALLROOM"));
                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "LEAD_PIPE"));

                System.out.println(notebook);
                break;

            case 2:

                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "MRS_WHITE"));
                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "HALL"));
                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "CANDLESTICK"));

                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "COLONEL_MUSTARD"));
                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "CONSERVATORY"));
                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "WRENCH"));
                System.out.println(notebook);
                break;


        }
    }

 /*
    public void run() {

        int num = 1;
        for (int i = 0; i < 5; i++){
            makeSampleNotebook(num);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (num > 1) {
                num = 1;
            } else num++;

            }
        }
     */

    }
