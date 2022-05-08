package edu.jhu.espresso.client.fx;

import edu.jhu.espresso.client.domain.CardNotebookStatus;
import edu.jhu.espresso.client.domain.GameEvents.Accusation;
import edu.jhu.espresso.client.domain.GameEvents.MoveOptions;
import edu.jhu.espresso.client.domain.GameEvents.Suggestion;
import edu.jhu.espresso.client.domain.GameEvents.SuggestionResponse;
import edu.jhu.espresso.client.domain.GamePieces.*;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang3.EnumUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class GameboardController
{
    private Player player;
    private Notebook notebook;
    private int columnIndex;
    private int rowIndex;
    MoveOptions moveOptions;
    List<RoomNames> locationRooms = Arrays.asList(RoomNames.values());
    private GameboardControllerStatus status = GameboardControllerStatus.WAITING;
    private CompletableFuture<GameboardControllerStatus> futureStatus = CompletableFuture.supplyAsync(() -> {
        while (true)
        {
        }
    });
    private Map<CharacterNames, LocationNames> characterLocations = new EnumMap<>(CharacterNames.class);
    private Suggestion suggestion;
    private SuggestionResponse suggestionResponse;
    private List<String> extraCardsNames;

    @FXML
    public GridPane gameBoard;

    @FXML
    public Button exit;
    @FXML
    public Button makeSuggestion;
    @FXML
    public Button makeAccusation;
    @FXML
    public Button move;
    @FXML
    public Button EndTurn;
    @FXML
    public TextArea statusBar;

    @FXML
    public Circle MISS_SCARLET;
    @FXML
    public Circle MRS_PEACOCK;
    @FXML
    public Circle PROFESSOR_PLUM;
    @FXML
    public Circle COLONEL_MUSTARD;
    @FXML
    public Circle MRS_WHITE;
    @FXML
    public Circle MR_GREEN;

    ArrayList<Rectangle> rooms = new ArrayList<>();
    List<Rectangle> rectangles = new ArrayList<>();
    @FXML
    private Rectangle STUDY;
    @FXML
    private Rectangle LOUNGE;
    @FXML
    private Rectangle HALL;
    @FXML
    private Rectangle LIBRARY;
    @FXML
    private Rectangle BILLIARD_ROOM;
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
    public Rectangle MISS_SCARLET_HS;
    @FXML
    public Rectangle MRS_WHITE_HS;
    @FXML
    public Rectangle COLONEL_MUSTARD_HS;
    @FXML
    public Rectangle MRS_PEACOCK_HS;
    @FXML
    public Rectangle MR_GREEN_HS;
    @FXML
    public Rectangle PROFESSOR_PLUM_HS;

    @FXML
    public Text textMustard;
    @FXML
    public Text textScarlet;
    @FXML
    public Text textPlum;
    @FXML
    public Text textWhite;
    @FXML
    public Text textGreen;
    @FXML
    public Text textPeacock;
    @FXML
    public Text textCandlestick;
    @FXML
    public Text textRope;
    @FXML
    public Text textDagger;
    @FXML
    public Text textRevolver;
    @FXML
    public Text textWrench;
    @FXML
    public Text textLeadPipe;
    @FXML
    public Text textStudy;
    @FXML
    public Text textHall;
    @FXML
    public Text textLounge;
    @FXML
    public Text textLibrary;
    @FXML
    public Text textBilliard;
    @FXML
    public Text textDining;
    @FXML
    public Text textConservatory;
    @FXML
    public Text textBallroom;
    @FXML
    public Text textKitchen;

    @FXML
    private Text player1;

    @FXML
    private Text player2;

    @FXML
    private Text player3;

    @FXML
    private Text player4;

    @FXML
    private Text player5;

    @FXML
    private Text player6;

    private ObjectProperty<Font> mustardFontObservable;
    private ObjectProperty<Font> scarletFontObservable;
    private ObjectProperty<Font> plumFontObservable;
    private ObjectProperty<Font> whiteFontObservable;
    private ObjectProperty<Font> greenFontObservable;
    private ObjectProperty<Font> peacockFontObservable;
    private ObjectProperty<Font> candlestickFontObservable;
    private ObjectProperty<Font> ropeFontObservable;
    private ObjectProperty<Font> daggerFontObservable;
    private ObjectProperty<Font> revolverFontObservable;
    private ObjectProperty<Font> wrenchFontObservable;
    private ObjectProperty<Font> leadPipeFontObservable;
    private ObjectProperty<Font> studyFontObservable;
    private ObjectProperty<Font> hallFontObservable;
    private ObjectProperty<Font> loungeFontObservable;
    private ObjectProperty<Font> libraryFontObservable;
    private ObjectProperty<Font> billiardRoomFontObservable;
    private ObjectProperty<Font> diningRoomFontObservable;
    private ObjectProperty<Font> conservatoryFontObservable;
    private ObjectProperty<Font> ballRoomFontObservable;
    private ObjectProperty<Font> kitchenFontObservable;
    private Accusation accusation;

    public void initialize()
    {
        rectangles.addAll(new ArrayList<>(Arrays.asList(STUDY,
                LOUNGE,
                HALL,
                LIBRARY,
                BILLIARD_ROOM,
                DINING_ROOM,
                BALLROOM,
                CONSERVATORY,
                KITCHEN,
                H12,
                H9,
                H8,
                H10,
                H11,
                H6,
                H5,
                H4,
                H3,
                H2,
                H1,
                H7,
                MISS_SCARLET_HS,
                MRS_WHITE_HS,
                COLONEL_MUSTARD_HS,
                MRS_PEACOCK_HS,
                MR_GREEN_HS,
                PROFESSOR_PLUM_HS
        )));

        player1.setText(CharacterNames.MISS_SCARLET.name());
        player2.setText(CharacterNames.COLONEL_MUSTARD.name());
        player3.setText(CharacterNames.MRS_WHITE.name());
        player4.setText(CharacterNames.MR_GREEN.name());
        player5.setText(CharacterNames.MRS_PEACOCK.name());
        player6.setText(CharacterNames.PROFESSOR_PLUM.name());
    }

    private boolean isRoom()
    {
        return !isHallway();
    }

    public void setDisableForAllButtons(boolean disable)
    {
        move.setDisable(disable);
        EndTurn.setDisable(disable);
        makeSuggestion.setDisable(disable);
        makeAccusation.setDisable(disable);
    }

    public void updateActivePlayerButtons() {

        /***Needs to follow the following:
         If a player can move, they must move and cannot end turn.
         If a player has already moved AND is in a room, they can suggest, accuse, or endTurn
         If a player is in a room and has not moved, they cannot suggest or end turn, they must move
        ***/
        boolean disableMakeSuggestion;
        if(player.isPulledFromSuggestion())
        {
            disableMakeSuggestion = player.isHasSuggested();
        }
        else
        {
            disableMakeSuggestion = isHallway() || player.isHasSuggested() || !player.isHasMoved();
        }

        makeSuggestion.setDisable(disableMakeSuggestion);

        boolean disableEndTurn;
        if (player.isPulledFromSuggestion())
        {
            disableEndTurn = !player.isHasSuggested();
        }
        else
        {
            disableEndTurn = (!player.isHasMoved() && !moveOptions.getValidMoves().isEmpty()) ||
                    (isRoom() && !player.isHasSuggested());
        }

        EndTurn.setDisable(disableEndTurn);
        move.setDisable(moveOptions.getValidMoves().isEmpty() || player.isHasSuggested());
    }

    public void updateStatusBar(String s)
    {
        statusBar.setWrapText(true);
        statusBar.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        statusBar.appendText(s);
        statusBar.appendText("\n");
    }

    public void setPlayer(Player player)
    {
        this.player = player;
        this.notebook = player.getNotebook();

        mustardFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "COLONEL_MUSTARD").getFont());
        scarletFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "MISS_SCARLET").getFont());
        plumFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "PROFESSOR_PLUM").getFont());
        whiteFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "MRS_WHITE").getFont());
        greenFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "MR_GREEN").getFont());
        peacockFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "MRS_PEACOCK").getFont());
        candlestickFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "CANDLESTICK").getFont());
        ropeFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "ROPE").getFont());
        daggerFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "DAGGER").getFont());
        revolverFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "REVOLVER").getFont());
        wrenchFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "WRENCH").getFont());
        leadPipeFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "LEAD_PIPE").getFont());
        studyFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "STUDY").getFont());
        hallFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "HALL").getFont());
        loungeFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "LOUNGE").getFont());
        libraryFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "LIBRARY").getFont());
        billiardRoomFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "BILLIARD_ROOM").getFont());
        diningRoomFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "DINING_ROOM").getFont());
        conservatoryFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "CONSERVATORY").getFont());
        ballRoomFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "BALLROOM").getFont());
        kitchenFontObservable = new ReadOnlyObjectWrapper<>(statusForCard(notebook, "KITCHEN").getFont());

        textMustard.fontProperty().bind(mustardFontObservable);
        textScarlet.fontProperty().bind(scarletFontObservable);
        textPlum.fontProperty().bind(plumFontObservable);
        textWhite.fontProperty().bind(whiteFontObservable);
        textGreen.fontProperty().bind(greenFontObservable);
        textPeacock.fontProperty().bind(peacockFontObservable);
        textCandlestick.fontProperty().bind(candlestickFontObservable);
        textRope.fontProperty().bind(ropeFontObservable);
        textDagger.fontProperty().bind(daggerFontObservable);
        textRevolver.fontProperty().bind(revolverFontObservable);
        textWrench.fontProperty().bind(wrenchFontObservable);
        textLeadPipe.fontProperty().bind(leadPipeFontObservable);
        textStudy.fontProperty().bind(studyFontObservable);
        textHall.fontProperty().bind(hallFontObservable);
        textLounge.fontProperty().bind(loungeFontObservable);
        textLibrary.fontProperty().bind(libraryFontObservable);
        textBilliard.fontProperty().bind(billiardRoomFontObservable);
        textDining.fontProperty().bind(diningRoomFontObservable);
        textConservatory.fontProperty().bind(conservatoryFontObservable);
        textBallroom.fontProperty().bind(ballRoomFontObservable);
        textKitchen.fontProperty().bind(kitchenFontObservable);

        updateNotebookObservables();
    }

    public List<Rectangle> getRectangles()
    {
        return rectangles;
    }

    public void disprove()
    {
        try
        {
            FXMLLoader fxml = new FXMLLoader(); // for DisproveSuggestion.fxml
            fxml.setLocation(getClass().getClassLoader().getResource("DisproveSuggestion.fxml"));
            Pane disprovePane = fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Suggestion");
            stage.setScene(new Scene(disprovePane, 1000, 364));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void resetRectangleColors()
    {
        for (Rectangle rectangle : rectangles)
        {
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(1);
            if (String.valueOf(locationRooms).contains(rectangle.getId()))
            {
                rectangle.setFill(Color.BLANCHEDALMOND);
            }
            else
            {
                rectangle.setFill(Color.DARKORCHID);
            }
        }
    }

    public void setMoveOptions(MoveOptions moveOptions)
    {
        this.moveOptions = moveOptions;
        updateValidRectangleColors();
    }

    private void updateValidRectangleColors()
    {
        resetRectangleColors();

        List<String> validRectangleIds = moveOptions.getValidMoves().stream()
                .map(Enum::name)
                .collect(Collectors.toList());

        List<Rectangle> validRectangles = rectangles.stream()
                .filter(rectangle -> validRectangleIds.contains(rectangle.getId()))
                .collect(Collectors.toList());

        validRectangles.forEach(rectangle -> {
            rectangle.setStrokeWidth(5);
            rectangle.setFill(Color.YELLOW);
        });
    }

    public void moveSuggested(Circle character, int columnIndex, int rowIndex)
    {
        GridPane.setRowIndex(character, rowIndex);
        GridPane.setColumnIndex(character, columnIndex);
    }

    @FXML
    void ballroomClicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.BALLROOM));
        moveOptions.setMove(LocationNames.BALLROOM);
        columnIndex = GridPane.getColumnIndex(BALLROOM);
        rowIndex = GridPane.getRowIndex(BALLROOM);
        BALLROOM.setStrokeWidth(10);
        BALLROOM.setStroke(Color.RED);
        rooms.add(BALLROOM);
    }

    @FXML
    void billiardClicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.BILLIARD_ROOM));
        moveOptions.setMove(LocationNames.BILLIARD_ROOM);
        columnIndex = GridPane.getColumnIndex(BILLIARD_ROOM);
        rowIndex = GridPane.getRowIndex(BILLIARD_ROOM);
        BILLIARD_ROOM.setStrokeWidth(10);
        BILLIARD_ROOM.setStroke(Color.RED);
        rooms.add(BILLIARD_ROOM);
    }

    @FXML
    void conservatoryClicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.CONSERVATORY));
        moveOptions.setMove(LocationNames.CONSERVATORY);
        columnIndex = GridPane.getColumnIndex(CONSERVATORY);
        rowIndex = GridPane.getRowIndex(CONSERVATORY);
        CONSERVATORY.setStrokeWidth(10);
        CONSERVATORY.setStroke(Color.RED);
        rooms.add(CONSERVATORY);
    }

    @FXML
    void diningClicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.DINING_ROOM));
        moveOptions.setMove(LocationNames.DINING_ROOM);
        columnIndex = GridPane.getColumnIndex(DINING_ROOM);
        rowIndex = GridPane.getRowIndex(DINING_ROOM);
        DINING_ROOM.setStrokeWidth(10);
        DINING_ROOM.setStroke(Color.RED);
        rooms.add(DINING_ROOM);
    }

    @FXML
    void studyClicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.STUDY));
        moveOptions.setMove(LocationNames.STUDY);
        columnIndex = GridPane.getColumnIndex(STUDY);
        rowIndex = GridPane.getRowIndex(STUDY);
        STUDY.setStrokeWidth(10);
        STUDY.setStroke(Color.RED);
        rooms.add(STUDY);
    }

    @FXML
    void hallClicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.HALL));
        moveOptions.setMove(LocationNames.HALL);
        columnIndex = GridPane.getColumnIndex(HALL);
        rowIndex = GridPane.getRowIndex(HALL);
        HALL.setStrokeWidth(10);
        HALL.setStroke(Color.RED);
        rooms.add(HALL);
    }

    @FXML
    void kitchenClicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.KITCHEN));
        moveOptions.setMove(LocationNames.KITCHEN);
        columnIndex = GridPane.getColumnIndex(KITCHEN);
        rowIndex = GridPane.getRowIndex(KITCHEN);
        KITCHEN.setStrokeWidth(10);
        KITCHEN.setStroke(Color.RED);
        rooms.add(KITCHEN);
    }

    @FXML
    void libraryClicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.LIBRARY));
        moveOptions.setMove(LocationNames.LIBRARY);
        columnIndex = GridPane.getColumnIndex(LIBRARY);
        rowIndex = GridPane.getRowIndex(LIBRARY);
        LIBRARY.setStrokeWidth(10);
        LIBRARY.setStroke(Color.RED);
        rooms.add(LIBRARY);
    }

    @FXML
    void loungeClicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.LOUNGE));
        moveOptions.setMove(LocationNames.LOUNGE);
        columnIndex = GridPane.getColumnIndex(LOUNGE);
        rowIndex = GridPane.getRowIndex(LOUNGE);
        LOUNGE.setStrokeWidth(10);
        LOUNGE.setStroke(Color.RED);
        rooms.add(LOUNGE);
    }

    @FXML
    void h1Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H1));
        moveOptions.setMove(LocationNames.H1);
        columnIndex = GridPane.getColumnIndex(H1);
        rowIndex = GridPane.getRowIndex(H1);
        H1.setStrokeWidth(10);
        H1.setStroke(Color.RED);
        rooms.add(H1);
    }

    @FXML
    void h2Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H2));
        moveOptions.setMove(LocationNames.H2);
        columnIndex = GridPane.getColumnIndex(H2);
        rowIndex = GridPane.getRowIndex(H2);
        H2.setStrokeWidth(10);
        H2.setStroke(Color.RED);
        rooms.add(H2);
    }

    @FXML
    void h3Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H3));
        moveOptions.setMove(LocationNames.H3);
        columnIndex = GridPane.getColumnIndex(H3);
        rowIndex = GridPane.getRowIndex(H3);
        H3.setStrokeWidth(10);
        H3.setStroke(Color.RED);
        rooms.add(H3);
    }

    @FXML
    void h4Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H4));
        moveOptions.setMove(LocationNames.H4);
        columnIndex = GridPane.getColumnIndex(H4);
        rowIndex = GridPane.getRowIndex(H4);
        H4.setStrokeWidth(10);
        H4.setStroke(Color.RED);
        rooms.add(H4);
    }

    @FXML
    void h5Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H5));
        moveOptions.setMove(LocationNames.H5);
        columnIndex = GridPane.getColumnIndex(H5);
        rowIndex = GridPane.getRowIndex(H5);
        H5.setStrokeWidth(10);
        H5.setStroke(Color.RED);
        rooms.add(H5);
    }

    @FXML
    void h6Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H6));
        moveOptions.setMove(LocationNames.H6);
        columnIndex = GridPane.getColumnIndex(H6);
        rowIndex = GridPane.getRowIndex(H6);
        H6.setStrokeWidth(10);
        H6.setStroke(Color.RED);
        rooms.add(H6);
    }

    @FXML
    void h7Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H7));
        moveOptions.setMove(LocationNames.H7);
        columnIndex = GridPane.getColumnIndex(H7);
        rowIndex = GridPane.getRowIndex(H7);
        H7.setStrokeWidth(10);
        H7.setStroke(Color.RED);
        rooms.add(H7);
    }

    @FXML
    void h8Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H8));
        moveOptions.setMove(LocationNames.H8);
        columnIndex = GridPane.getColumnIndex(H8);
        rowIndex = GridPane.getRowIndex(H8);
        H8.setStrokeWidth(10);
        H8.setStroke(Color.RED);
        rooms.add(H8);
    }

    @FXML
    void h9Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H9));
        moveOptions.setMove(LocationNames.H9);
        columnIndex = GridPane.getColumnIndex(H9);
        rowIndex = GridPane.getRowIndex(H9);
        H9.setStrokeWidth(10);
        H9.setStroke(Color.RED);
        rooms.add(H9);
    }

    @FXML
    void h10Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H10));
        moveOptions.setMove(LocationNames.H10);
        columnIndex = GridPane.getColumnIndex(H10);
        rowIndex = GridPane.getRowIndex(H10);
        H10.setStrokeWidth(10);
        H10.setStroke(Color.RED);
        rooms.add(H10);
    }

    @FXML
    void h11Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H11));
        moveOptions.setMove(LocationNames.H11);
        columnIndex = GridPane.getColumnIndex(H11);
        rowIndex = GridPane.getRowIndex(H11);
        H11.setStrokeWidth(10);
        H11.setStroke(Color.RED);
        rooms.add(H11);
    }

    @FXML
    void h12Clicked(MouseEvent event)
    {
        move.setDisable(!moveOptions.getValidMoves().contains(LocationNames.H12));
        moveOptions.setMove(LocationNames.H12);
        columnIndex = GridPane.getColumnIndex(H12);
        rowIndex = GridPane.getRowIndex(H12);
        H12.setStrokeWidth(10);
        H12.setStroke(Color.RED);
        rooms.add(H12);
    }

    @FXML
    public void exitGame(ActionEvent event)
    {  // leave the game : will either exit program completely, or boot to foyer
        Platform.exit();
    }

    @FXML
    public void openAccusationWindow(ActionEvent event)
    {  // AccusationMenu2.fxml
        try
        {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(getClass().getClassLoader().getResource("AccusationMenu2.fxml"));
            Pane accusationPane = fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Accusation");
            stage.setScene(new Scene(accusationPane, 1000, 364));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(makeAccusation.getScene().getWindow());

            ControllerAccusation controllerAccusation = fxml.getController();
            controllerAccusation.disableExtraCards(extraCardsNames);

            stage.showAndWait();

            accusation = controllerAccusation.getAccusation();
            futureStatus.complete(GameboardControllerStatus.ACCUSATION);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void openSuggestionWindow(ActionEvent event)
    {  // SuggestionMenu.fxml
        try
        {
            FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(getClass().getClassLoader().getResource("SuggestionMenu.fxml"));
            Pane suggestionPane = fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Suggestion");
            stage.setScene(new Scene(suggestionPane, 1000, 364));
            ControllerSuggestion controllerSuggestion = fxml.getController();
            String test = "IWHBYD";
            controllerSuggestion.setGameboardController(this);
            controllerSuggestion.suggestRoom.setText("       " + getPlayerLocation());
            controllerSuggestion.disableExtraCards(extraCardsNames);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(makeSuggestion.getScene().getWindow());
            stage.showAndWait();

            suggestion = controllerSuggestion.getSuggestion();

            final boolean[] buttonSelectStatus = {false, false};
            controllerSuggestion.suggest.setDisable(true);
            controllerSuggestion.sugWeap1.selectedToggleProperty().addListener((new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> ov, Toggle oldvalue, Toggle newvalue) {
                    if (controllerSuggestion.sugWeap1.getSelectedToggle() != null) {
                        buttonSelectStatus[0] = true;
                        if (buttonSelectStatus[0] && buttonSelectStatus[1]) controllerSuggestion.suggest.setDisable(true);
                    }
                }
            }));
            controllerSuggestion.sugChar1.selectedToggleProperty().addListener((new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> ov, Toggle oldvalue, Toggle newvalue) {
                    if (controllerSuggestion.sugChar1.getSelectedToggle() != null) {
                        buttonSelectStatus[1] = true;
                        if (buttonSelectStatus[0] && buttonSelectStatus[1]) controllerSuggestion.suggest.setDisable(false);
                    }
                }
            }));
            futureStatus.complete(GameboardControllerStatus.SUGGESTION);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private Circle getCircle(CharacterNames characterNames)
    {
        Circle circle;
        switch (characterNames)
        {
            case PROFESSOR_PLUM:
                circle = PROFESSOR_PLUM;
                break;
            case MRS_WHITE:
                circle = MRS_WHITE;
                break;
            case MR_GREEN:
                circle = MR_GREEN;
                break;
            case MRS_PEACOCK:
                circle = MRS_PEACOCK;
                break;
            case MISS_SCARLET:
                circle = MISS_SCARLET;
                break;
            case COLONEL_MUSTARD:
                circle = COLONEL_MUSTARD;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return circle;
    }

    public void updateCharacterLocation(CharacterNames characterNames, LocationNames locationNames)
    {
        Rectangle rectangle = locationNames.getBoardRectangle(this);
        Circle characterCircleAvatar = getCircle(characterNames);

        GridPane.setColumnIndex(characterCircleAvatar, GridPane.getColumnIndex(rectangle));
        GridPane.setRowIndex(characterCircleAvatar, GridPane.getRowIndex(rectangle));

        if (locationNames.isRoom())
        {
            GridPane.setHalignment(characterCircleAvatar, characterNames.getHorizontalPosition());
            GridPane.setValignment(characterCircleAvatar, characterNames.getVerticalPosition());
        }
        else
        {
            GridPane.setHalignment(characterCircleAvatar, HPos.CENTER);
            GridPane.setValignment(characterCircleAvatar, VPos.CENTER);
        }

        characterLocations.put(characterNames, locationNames);
    }

    @FXML
    public void sendMove(ActionEvent event)
    {  // send move selected on gameboard to server
        moveOptions.printToString();
        GridPane.setColumnIndex(player.getCircle(), columnIndex);
        GridPane.setRowIndex(player.getCircle(), rowIndex);
        resetRectangleColors();
        move.setDisable(true);
        makeSuggestion.setDisable(isHallway());
        EndTurn.setDisable(!isHallway());

        updateCharacterLocation(player.getCharacter(), moveOptions.getLocation());

        moveOptions.getValidMoves().clear();
        futureStatus.complete(GameboardControllerStatus.MOVE);
    }

    public void disproveSuggestionWindow(List<Card> suggestedCards)
    {
        try
        {
            FXMLLoader fxml = new FXMLLoader(); // for DisproveSuggestion.fxml
            fxml.setLocation(getClass().getClassLoader().getResource("DisproveSuggestion.fxml"));
            Pane disprovePane = fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Clue-Less Suggestion");
            stage.setScene(new Scene(disprovePane, 1000, 364));
            ControllerDisprove controllerDisprove = fxml.getController();
            controllerDisprove.initialize(player.getNotebook().getHandCards(), suggestedCards);
            stage.showAndWait();

            suggestionResponse = controllerDisprove.getResponse();
            futureStatus.complete(GameboardControllerStatus.SUGGESTION_RESPONSE);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void endTurn(ActionEvent event)
    {
        resetRectangleColors();
        futureStatus.complete(GameboardControllerStatus.END_TURN);
    }

    public void updateNotebookObservables()
    {
        mustardFontObservable.set(statusForCard(notebook, "COLONEL_MUSTARD").getFont());
        scarletFontObservable.set(statusForCard(notebook, "MISS_SCARLET").getFont());
        plumFontObservable.set(statusForCard(notebook, "PROFESSOR_PLUM").getFont());
        whiteFontObservable.set(statusForCard(notebook, "MRS_WHITE").getFont());
        greenFontObservable.set(statusForCard(notebook, "MR_GREEN").getFont());
        peacockFontObservable.set(statusForCard(notebook, "MRS_PEACOCK").getFont());
        candlestickFontObservable.set(statusForCard(notebook, "CANDLESTICK").getFont());
        daggerFontObservable.set(statusForCard(notebook, "DAGGER").getFont());
        revolverFontObservable.set(statusForCard(notebook, "REVOLVER").getFont());
        leadPipeFontObservable.set(statusForCard(notebook, "LEAD_PIPE").getFont());
        ropeFontObservable.set(statusForCard(notebook, "ROPE").getFont());
        wrenchFontObservable.set(statusForCard(notebook, "WRENCH").getFont());
        studyFontObservable.set(statusForCard(notebook, "STUDY").getFont());
        libraryFontObservable.set(statusForCard(notebook, "LIBRARY").getFont());
        hallFontObservable.set(statusForCard(notebook, "HALL").getFont());
        conservatoryFontObservable.set(statusForCard(notebook, "CONSERVATORY").getFont());
        diningRoomFontObservable.set(statusForCard(notebook, "DINING_ROOM").getFont());
        ballRoomFontObservable.set(statusForCard(notebook, "BALLROOM").getFont());
        loungeFontObservable.set(statusForCard(notebook, "LOUNGE").getFont());
        kitchenFontObservable.set(statusForCard(notebook, "KITCHEN").getFont());
        billiardRoomFontObservable.set(statusForCard(notebook, "BILLIARD_ROOM").getFont());
    }

    private CardNotebookStatus statusForCard(Notebook notebook, String cardName)
    {
        ObservableMap<Card, CardNotebookStatus> map = notebook.getCardNotebookStatusMap();
        Card cardWithName = notebook.getCardNotebookStatusMap().keySet().stream()
                .filter(card -> card.getName().equals(cardName))
                .findFirst()
                .orElseThrow(IllegalStateException::new); // called

        return map.get(cardWithName);
    }

    public void setCharacters()
    {
        player.setCharacter(CharacterNames.MISS_SCARLET);
        player1.setText("Player1: " + player.getCharacter());
    }

    public CompletableFuture<GameboardControllerStatus> getStatusFuture()
    {
        return futureStatus;
    }

    public MoveOptions getMoveOptions()
    {
        return moveOptions;
    }

    public void resetStatus()
    {
        futureStatus = CompletableFuture.supplyAsync(() -> {
            while (true)
            {
            }
        });
    }

    public Player getPlayer()
    {
        return player;
    }

    public LocationNames getPlayerLocation()
    {
        return characterLocations.get(player.getCharacter());
    }

    public Suggestion getSuggestion()
    {
        return suggestion;
    }

    public SuggestionResponse getSuggestionResponse()
    {
        return suggestionResponse;
    }

    public Accusation getAccusation()
    {
        return accusation;
    }

    public void setNumberOfPlayers(int numberOfPlayers)
    {
        player1.setText("player1:" + player1.getText());
        player2.setText("player2:" + player2.getText());
        player3.setText("player3:" + player3.getText());

        switch (numberOfPlayers)
        {
            case 4:
                player4.setText("player4:" + player4.getText());
                break;
            case 5:
                player4.setText("player4:" + player4.getText());
                player5.setText("player5:" + player5.getText());
                break;
            case 6:
                player4.setText("player4:" + player4.getText());
                player5.setText("player5:" + player5.getText());
                player6.setText("player6:" + player6.getText());
                break;
        }
    }

    public void setExtraCardsNames(List<String> extraCardsNames)
    {
        this.extraCardsNames = extraCardsNames;
    }

    public boolean isHallway(){

        boolean hallway = true;

        for (RoomNames location : locationRooms){
            if (EnumUtils.isValidEnum(RoomNames.class, String.valueOf(moveOptions.getLocation()))){
                hallway = false;
            }
        }
        return hallway;
    }
}
