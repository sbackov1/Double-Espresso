package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameEvents.*;
import edu.jhu.espresso.client.domain.GamePieces.CaseDetails;
import edu.jhu.espresso.client.domain.GamePieces.CharacterNames;
import edu.jhu.espresso.client.domain.GamePieces.LocationNames;
import edu.jhu.espresso.client.domain.GamePieces.RoomNames;
import edu.jhu.espresso.client.domain.Menus.Menu;
import edu.jhu.espresso.client.domain.Menus.MenuItem;
import edu.jhu.espresso.server.protocol.ClueLessTurnProtocol;
import java.util.HashMap.*;

public class ActivePlayerProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;

    private ServerProtocolOfferInterpreter gameOptions;
    private MoveOptions moveOptions;

    private TurnStart turnStart;
    //private edu.jhu.espresso.client.domain.GameEvents.TurnStart turnStart;

    public ActivePlayerProtocol(ClueLessClient client)
    {
        this.client = client;
    }

    @Override
    public void execute(TurnStart turnStart)
    {

        this.turnStart = turnStart;
        gameOptions = client.waitForResponse(ServerProtocolOfferInterpreter.class);
        this.mainSelectionMenu();

    }

    private ActivePlayerProtocolSelector makeAccusationChoice(Accusation options)
    {
        options.mainAccMenu();
        options.setAccusationStatus(AccusationStatus.MAKING_ACCUSATION);
        return ActivePlayerProtocolSelector.FromAccusation(options);
    }

    private ActivePlayerProtocolSelector makeSuggestionChoice(Suggestion options)
    {
        options.mainSugMenu();
        options.setRoomNames(RoomNames.BILLIARD_ROOM);
        options.setSuggestionStatus(SuggestionStatus.MAKING_SUGGESTION);

        CaseDetails caseDetails = new CaseDetails();
        caseDetails.setRoom(RoomNames.BILLIARD_ROOM);
        caseDetails.setCharacterNames(options.getCharacter());
        caseDetails.setWeapon(options.getWeapon());
        caseDetails.setCharacterNames(options.getCharacter());

        options.setCaseDetails(
                caseDetails
        );
        return ActivePlayerProtocolSelector.FromSuggestion(options);
    }

    private ActivePlayerProtocolSelector makeMoveChoice(MoveOptions options)
    {
        options.mainMoveMenu();
        return ActivePlayerProtocolSelector.FromMoveChoice(new MoveChoice(options.getLocation()));
    }

    private void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            throw new IllegalStateException(e);
        }
    }

    private void updateNotebook(SuggestionTestimonyResponse response)
    {
        response.optionalResponse().ifPresent(value -> client.getPlayer().getNotebook().makeKnownCard(value));
    }


    /**
     * Main selection menu creates the menu that provides the player with the valid move options.
     * It uses the boolean values for canMove and canSuggest to ascertain whether these are valid or not.
     * */
    public void mainSelectionMenu()
    {
        Menu selectionMenu = new Menu();
        selectionMenu.setTitle("*** Current Choices ***");

        if(canMove){
            selectionMenu.addItem(new MenuItem("Make a Move", this, "goToMovementMenu"));
        }

        if(canSuggest){
            selectionMenu.addItem(new MenuItem("Make Suggestion", this, "goToSuggestionMenu"));
        }

        selectionMenu.addItem(new MenuItem("Make Accusation", this, "goToAccusationMenu"));

        selectionMenu.execute();
    }


    /**
     * goToSuggestionMenu, goToMovementMenu, and goToAccusationMenu create menus for their respective classes.
     * */

    public void goToSuggestionMenu(){

        Suggestion suggestion = client.waitForResponse(Suggestion.class);
        client.write(makeSuggestionChoice(suggestion));

        SuggestionTestimonyResponse response = client.waitForResponse(SuggestionTestimonyResponse.class);
        updateNotebook(response);

        canSuggest = false;

    }

    public void goToMovementMenu(){
        //MoveOptions moveOptions = client.waitForResponse(MoveOptions.class);
        client.write(makeMoveChoice(moveOptions));
        canMove = false;
    }

    public void goToAccusationMenu() {
        Accusation accusation = client.waitForResponse(Accusation.class);
        client.write(makeAccusationChoice(accusation));
    }


}




