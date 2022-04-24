package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameEvents.*;
import edu.jhu.espresso.client.domain.GamePieces.CaseDetails;
import edu.jhu.espresso.client.domain.GamePieces.LocationNames;
import edu.jhu.espresso.client.domain.GamePieces.RoomNames;
import edu.jhu.espresso.client.domain.Menus.Menu;
import edu.jhu.espresso.client.domain.Menus.MenuItem;

class ActivePlayerProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;

    private boolean canMove;

    private boolean canSuggest;

    public ActivePlayerProtocol(ClueLessClient client)
    {
        this.client = client;
    }

    @Override
    public void execute(TurnStart turnStart)
    {
        this.mainSelectionMenu();

    }

    private Accusation makeAccusationChoice(Accusation options)
    {
        options.mainAccMenu();
        options.setAccusationStatus(AccusationStatus.MAKING_ACCUSATION);
        return options;
    }

    private Suggestion makeSuggestionChoice(Suggestion options)
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
        return options;
    }

    private MoveChoice makeMoveChoice(MoveOptions options)
    {
        options.mainMoveMenu();
        return new MoveChoice(options.getLocation());
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
            MoveOptions moveOptions = client.waitForResponse(MoveOptions.class);
            selectionMenu.addItem(new MenuItem("Make a Move", this, "goToMovementMenu"));
        }

        if(canSuggest){
            selectionMenu.addItem(new MenuItem("Make Suggestion", this, "goToSuggestionMenu"));
        }

        selectionMenu.addItem(new MenuItem("MakeAccusation", this, "goToAccusationMenu"));

        selectionMenu.execute();
    }

    public void goToSuggestionMenu(){
        Suggestion suggestion = client.waitForResponse(Suggestion.class);
        client.write(makeSuggestionChoice(suggestion));

        SuggestionTestimonyResponse response = client.waitForResponse(SuggestionTestimonyResponse.class);
        updateNotebook(response);
    }

    public void goToMovementMenu(){
        MoveOptions moveOptions = client.waitForResponse(MoveOptions.class);
        client.write(makeMoveChoice(moveOptions));
    }

    public void goToAccusationMenu() {
        Accusation accusation = client.waitForResponse(Accusation.class);
        client.write(makeAccusationChoice(accusation));
    }

}
