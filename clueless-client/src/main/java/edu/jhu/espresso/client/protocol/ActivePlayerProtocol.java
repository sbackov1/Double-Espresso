package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameEvents.*;
import edu.jhu.espresso.client.domain.GamePieces.CaseDetails;
import edu.jhu.espresso.client.domain.GamePieces.LocationNames;
import edu.jhu.espresso.client.domain.GamePieces.RoomNames;
import edu.jhu.espresso.client.domain.Menus.Menu;
import edu.jhu.espresso.client.domain.Menus.MenuItem;
import edu.jhu.espresso.client.domain.GameEvents.Accusation;
import edu.jhu.espresso.client.domain.GameEvents.Suggestion;
import edu.jhu.espresso.client.domain.GameEvents.MoveOptions;

import java.util.ArrayList;
import java.util.Collections;


public class ActivePlayerProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;
    private ServerActivePlayerProtocolOfferer gameOptions;
    private boolean canSuggest;
    private boolean canMove;
    private Suggestion suggestion;
    private Accusation accusation;
    private MoveOptions moveOptions;
    private TurnStart turnStart;

    private boolean endTurn;
    //private edu.jhu.espresso.client.domain.GameEvents.TurnStart turnStart;

    public ActivePlayerProtocol(ClueLessClient client)
    {
        this.client = client;
    }

    @Override
    public void execute(TurnStart turnStart)
    {

        this.turnStart = turnStart;
        this.endTurn = false;

    while(!endTurn) {
        gameOptions = client.waitForResponse(ServerActivePlayerProtocolOfferer.class);

        //Set canMove and canSuggest to false, they will be set to true during the unpackProtocolOffer method.
        this.setCanSuggest(false);
        this.setCanMove(false);

        this.unPackProtocolOffer(gameOptions);
        this.mainSelectionMenu();
    }
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

        if(isCanMove()){
            selectionMenu.addItem(new MenuItem("Make a Move", this, "goToMovementMenu"));
        }

        if(isCanSuggest()){
            selectionMenu.addItem(new MenuItem("Make Suggestion", this, "goToSuggestionMenu"));
        }

        selectionMenu.addItem(new MenuItem("Make Accusation", this, "goToAccusationMenu"));

        selectionMenu.addItem(new MenuItem("End Turn", this, "EndTurn"));

        selectionMenu.execute();
    }


    /**
     * goToSuggestionMenu, goToMovementMenu, and goToAccusationMenu create menus for their respective classes.
     * */

    public void goToSuggestionMenu(){

        client.write(makeSuggestionChoice(suggestion));

        SuggestionTestimonyResponse response = client.waitForResponse(SuggestionTestimonyResponse.class);
        updateNotebook(response);

    }

    public void goToMovementMenu(){
        client.write(makeMoveChoice(moveOptions));
    }

    public void goToAccusationMenu() {
        client.write(makeAccusationChoice(accusation));
    }


    /**
     * Getters and setters for instance fields
     * **/
    public boolean isCanSuggest() {
        return canSuggest;
    }

    public void setCanSuggest(boolean canSuggest) {
        this.canSuggest = canSuggest;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public Accusation getAccusation() {
        return accusation;
    }

    public void setAccusation(Accusation accusation) {
        this.accusation = accusation;
    }

    public MoveOptions getMoveOptions() {
        return moveOptions;
    }

    public void setMoveOptions(MoveOptions moveOptions) {
        this.moveOptions = moveOptions;
    }

    public TurnStart getTurnStart() {
        return turnStart;
    }

    public void setTurnStart(TurnStart turnStart) {
        this.turnStart = turnStart;
    }

    /**
     * unpackProtocolOffer unpacks the protocol offer.  For each field, it sets the ActivPlayerProtocol instance field.  It also sets the
     * instance boolean values for canMove and canSuggest.
     * ***/
    private void unPackProtocolOffer(ServerActivePlayerProtocolOfferer sp){

        sp.getOfferMoveOptions().ifPresent(moveOptions -> {
            this.setMoveOptions(moveOptions);
            this.setCanMove(true);
        });

        sp.getOfferSuggestion().ifPresent(suggestion -> {
            this.setSuggestion(suggestion);
            this.setCanSuggest(true);
        });

        sp.getOfferAccusation().ifPresent(this::setAccusation);

    }

    /**
     * EndTurn signals to the server to end the turn by sending an ActivPlayerProtocolSelector object with three null fields.
     *
    ***/

    public void EndTurn(){
        this.endTurn = true;
        client.write(new ActivePlayerProtocolSelector(null, null, null));
    }


}




