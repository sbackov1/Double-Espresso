package edu.jhu.espresso.client.fx;

import edu.jhu.espresso.client.domain.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class GameboardController {

    private final CardDeck cd = new CardDeck();
    private final Notebook notebook = new Notebook(cd);

    private int columnIndex;
    private int rowIndex;
    MoveOptions moveOptions = new MoveOptions();

    @FXML public GridPane gameBoard;

    @FXML public Button exit;
    @FXML public Button makeSuggestion;
    @FXML public Button makeAccusation;
    @FXML public Button move;
    @FXML public Button EndTurn;

    @FXML public Circle MISS_SCARLET;
    @FXML public Circle MRS_PEACOCK;
    @FXML public Circle PROFESSOR_PLUM;
    @FXML public Circle COLONEL_MUSTARD;
    @FXML public Circle MRS_WHITE;
    @FXML public Circle MR_GREEN;

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

    @FXML public Rectangle HomeSquareMS;
    @FXML public Rectangle HomeSquareMW;
    @FXML public Rectangle HomeSquareCM;
    @FXML public Rectangle HomeSquareMP;
    @FXML public Rectangle HomeSquareMG;
    @FXML public Rectangle HomeSquarePP;

    @FXML public Text textMustard;
    @FXML public Text textScarlet;
    @FXML public Text textPlum;
    @FXML public Text textWhite;
    @FXML public Text textGreen;
    @FXML public Text textPeacock;
    @FXML public Text textCandlestick;
    @FXML public Text textRope;
    @FXML public Text textDagger;
    @FXML public Text textRevolver;
    @FXML public Text textWrench;
    @FXML public Text textLeadPipe;
    @FXML public Text textStudy;
    @FXML public Text textHall;
    @FXML public Text textLounge;
    @FXML public Text textLibrary;
    @FXML public Text textBillard;
    @FXML public Text textDining;
    @FXML public Text textConservatory;
    @FXML public Text textBallroom;
    @FXML public Text textKitchen;

    private final ObjectProperty<Font> mustardFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "COLONEL_MUSTARD").getFont());
    private final ObjectProperty<Font> scarletFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "MISS_SCARLET").getFont());
    private final ObjectProperty<Font> plumFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "PROFESSOR_PLUM").getFont());
    private final ObjectProperty<Font> whiteFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "MRS_WHITE").getFont());
    private final ObjectProperty<Font> greenFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "MR_GREEN").getFont());
    private final ObjectProperty<Font> peacockFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "MRS_PEACOCK").getFont());
    private final ObjectProperty<Font> candlestickFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "CANDLESTICK").getFont());
    private final ObjectProperty<Font> ropeFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "ROPE").getFont());
    private final ObjectProperty<Font> daggerFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "DAGGER").getFont());
    private final ObjectProperty<Font> revolverFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "REVOLVER").getFont());
    private final ObjectProperty<Font> wrenchFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "WRENCH").getFont());
    private final ObjectProperty<Font> leadPipeFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "LEAD_PIPE").getFont());
    private final ObjectProperty<Font> studyFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "STUDY").getFont());
    private final ObjectProperty<Font> hallFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "HALL").getFont());
    private final ObjectProperty<Font> loungeFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "LOUNGE").getFont());
    private final ObjectProperty<Font> libraryFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "LIBRARY").getFont());
    private final ObjectProperty<Font> billiardRoomFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "BILLIARD_ROOM").getFont());
    private final ObjectProperty<Font> diningRoomFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "DINING_ROOM").getFont());
    private final ObjectProperty<Font> conservatoryFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "CONSERVATORY").getFont());
    private final ObjectProperty<Font> ballRoomFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "BALLROOM").getFont());
    private final ObjectProperty<Font> kitchenFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "KITCHEN").getFont());


    public void initialize()
    {

        textScarlet.fontProperty().bind(scarletFontObservable);
    }

    public void moveSuggested(Circle character, int columnIndex, int rowIndex) {
        GridPane.setRowIndex(character, rowIndex);
        GridPane.setColumnIndex(character, columnIndex);
    }

    @FXML void ballroomClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.BALLROOM);
        columnIndex = GridPane.getColumnIndex(BALLROOM);
        rowIndex = GridPane.getRowIndex(BALLROOM);
    }

    @FXML void billardClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.BILLIARD_ROOM);
        columnIndex = GridPane.getColumnIndex(BILLARD_ROOM);
        rowIndex = GridPane.getRowIndex(BILLARD_ROOM);
    }

    @FXML void conservatoryClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.CONSERVATORY);
        columnIndex = GridPane.getColumnIndex(CONSERVATORY);
        rowIndex = GridPane.getRowIndex(CONSERVATORY);
    }

    @FXML void diningClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.DINING_ROOM);
        columnIndex = GridPane.getColumnIndex(DINING_ROOM);
        rowIndex = GridPane.getRowIndex(DINING_ROOM);
    }

    @FXML void studyClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.STUDY);
        columnIndex = GridPane.getColumnIndex(STUDY);
        rowIndex = GridPane.getRowIndex(STUDY);
    }

    @FXML void hallClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALL);
        columnIndex = GridPane.getColumnIndex(HALL);
        rowIndex = GridPane.getRowIndex(HALL);
    }

    @FXML void kitchenClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.KITCHEN);
        columnIndex = GridPane.getColumnIndex(KITCHEN);
        rowIndex = GridPane.getRowIndex(KITCHEN);
    }

    @FXML void libraryClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.LIBRARY);
        columnIndex = GridPane.getColumnIndex(LIBRARY);
        rowIndex = GridPane.getRowIndex(LIBRARY);
    }

    @FXML void loungeClicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.LOUNGE);
        columnIndex = GridPane.getColumnIndex(LOUNGE);
        rowIndex = GridPane.getRowIndex(LOUNGE);
    }

    @FXML void h1Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY1);
        columnIndex = GridPane.getColumnIndex(H1);
        rowIndex = GridPane.getRowIndex(H1);
    }

    @FXML void h2Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY2);
        columnIndex = GridPane.getColumnIndex(H2);
        rowIndex = GridPane.getRowIndex(H2);
    }

    @FXML void h3Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY3);
        columnIndex = GridPane.getColumnIndex(H3);
        rowIndex = GridPane.getRowIndex(H3);
    }

    @FXML void h4Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY4);
        columnIndex = GridPane.getColumnIndex(H4);
        rowIndex = GridPane.getRowIndex(H4);
    }

    @FXML void h5Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY5);
        columnIndex = GridPane.getColumnIndex(H5);
        rowIndex = GridPane.getRowIndex(H5);
    }

    @FXML void h6Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY6);
        columnIndex = GridPane.getColumnIndex(H6);
        rowIndex = GridPane.getRowIndex(H6);
    }

    @FXML void h7Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY7);
        columnIndex = GridPane.getColumnIndex(H7);
        rowIndex = GridPane.getRowIndex(H7);
    }

    @FXML void h8Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY8);
        columnIndex = GridPane.getColumnIndex(H8);
        rowIndex = GridPane.getRowIndex(H8);
    }

    @FXML void h9Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY9);
        columnIndex = GridPane.getColumnIndex(H9);
        rowIndex = GridPane.getRowIndex(H9);
    }

    @FXML void h10Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY10);
        columnIndex = GridPane.getColumnIndex(H10);
        rowIndex = GridPane.getRowIndex(H10);
    }

    @FXML void h11Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY11);
        columnIndex = GridPane.getColumnIndex(H11);
        rowIndex = GridPane.getRowIndex(H11);
    }

    @FXML void h12Clicked(MouseEvent event) {
        moveOptions.setMove(LocationNames.HALLWAY12);
        columnIndex = GridPane.getColumnIndex(H12);
        rowIndex = GridPane.getRowIndex(H12);
    }

    @FXML public void exitGame(ActionEvent event) {  // leave the game : will either exit program completely, or boot to foyer
        //Platform.exit();
    }

    @FXML public void openAccusationWindow(ActionEvent event) {  // AccusationMenu2.fxml
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(getClass().getClassLoader().getResource("AccusationMenu2.fxml"));
            Pane accusationPane = fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Accusation");
            stage.setScene(new Scene(accusationPane, 1000, 364));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void openSuggestionWindow(ActionEvent event) {  // SuggestionMenu.fxml
        try {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(getClass().getClassLoader().getResource("SuggestionMenu.fxml"));
            Pane suggestionPane = fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Suggestion");
            stage.setScene(new Scene(suggestionPane, 1000, 364));
            stage.show();
            ControllerSuggestion suggest = fxml.getController();
            String test = "IWHBYD";
            suggest.suggestRoom.setText(test);
            //suggest.setFXMLLoader(fxml);
            //columnIndex = GridPane.getColumnIndex(MISS_SCARLET);
            //rowIndex = GridPane.getRowIndex(MISS_SCARLET);
            //moveSuggested(MR_GREEN, columnIndex, rowIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void sendMove(ActionEvent event) {  // send move selected on gameboard to server
        moveOptions.printToString();
        GridPane.setColumnIndex(MISS_SCARLET, columnIndex);
        GridPane.setRowIndex(MISS_SCARLET, rowIndex);
    }

    @FXML void endTurn(ActionEvent event) {

    }

    public void makeSampleNotebook(int num) {
        scarletFontObservable.set(statusForCard(notebook, "MISS_SCARLET").getFont());
        switch (num) {
            case 1:

                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "PROFESSOR_PLUM"));
                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "STUDY"));
                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "DAGGER"));

                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "BALLROOM"));
                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "LEAD_PIPE"));

                System.out.println(notebook);
                break;

            case 2:

                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "MRS_WHITE"));
                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "HALL"));
                notebook.makeHandCard(cd.getCard(cd.getCardsList(), "CANDLESTICK"));

                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "MISS_SCARLET"));
                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "COLONEL_MUSTARD"));
                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "CONSERVATORY"));
                notebook.makeKnownCard(cd.getCard(cd.getCardsList(), "WRENCH"));
                System.out.println(notebook);
                break;


        }
    }

    private CardNotebookStatus statusForCard(Notebook notebook, String cardName)
    {
        ObservableMap<Card, CardNotebookStatus> map = notebook.getCardNotebookStatusMap();
        Card cardWithName = notebook.getCardNotebookStatusMap().keySet().stream()
                .filter(card -> card.getName().equals(cardName))
                .findFirst()
                .orElseThrow(IllegalStateException::new);

        return map.get(cardWithName);
    }

}
