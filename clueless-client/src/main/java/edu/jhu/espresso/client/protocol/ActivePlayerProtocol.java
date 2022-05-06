package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameEvents.*;
import edu.jhu.espresso.client.domain.GamePieces.CaseDetails;
import edu.jhu.espresso.client.domain.GameEvents.Accusation;
import edu.jhu.espresso.client.domain.GameEvents.Suggestion;
import edu.jhu.espresso.client.domain.GameEvents.MoveOptions;
import edu.jhu.espresso.client.fx.GameboardController;
import edu.jhu.espresso.client.fx.GameboardControllerStatus;

import java.util.Collections;


public class ActivePlayerProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;
    private final GameboardController gameboardController;

    private ServerActivePlayerProtocolOfferer gameOptions;
    private boolean canSuggest;
    private boolean canMove;
    private Suggestion suggestion;
    private Accusation accusation;
    private MoveOptions moveOptions;
    private TurnStart turnStart;

    private boolean endTurn;

    public ActivePlayerProtocol(GameboardController gameboardController, ClueLessClient client)
    {
        this.client = client;
        this.gameboardController = gameboardController;
    }

    @Override
    public void execute(TurnStart turnStart)
    {
        this.turnStart = turnStart;
        this.endTurn = false;

        while (!endTurn)
        {
            gameOptions = client.waitForResponse(ServerActivePlayerProtocolOfferer.class);

            //Set canMove and canSuggest to false, they will be set to true during the unpackProtocolOffer method.
            this.setCanSuggest(false);
            this.setCanMove(false);

            this.unPackProtocolOffer(gameOptions);
            GameboardControllerStatus status = gameboardController.getStatusFuture().join();

            System.out.println("here");

            ActivePlayerProtocolSelector selector;
            switch (status)
            {
                case MOVE:
                    selector = ActivePlayerProtocolSelector.FromMoveChoice(
                            new MoveChoice(gameboardController.getMoveOptions().getLocation())
                    );
                    break;
                case SUGGESTION:
                    selector = ActivePlayerProtocolSelector.FromSuggestion(
                            gameboardController.getSuggestion()
                    );
                    break;
                case ACCUSATION:
                    selector = ActivePlayerProtocolSelector.FromAccusation(
                            gameboardController.getAccusation()
                    );
                    break;
                case END_TURN:
                    endTurn = true;
                    selector = ActivePlayerProtocolSelector.EndTurn();
                    break;
                default:
                    throw new IllegalStateException();
            }

            client.write(selector);

            if(status == GameboardControllerStatus.SUGGESTION)
            {
                TurnStart gameUpdateTurnStart = client.waitForResponse(TurnStart.class);
                client.updateCharactersOnBoard(gameUpdateTurnStart);
                client.write(gameUpdateTurnStart);
                SuggestionTestimonyResponse suggestionTestimonyResponse = client.waitForResponse(SuggestionTestimonyResponse.class);
                gameboardController.updateStatusBar("testimony response: " + suggestionTestimonyResponse);
                //System.out.println("testimony response: " + suggestionTestimonyResponse);
                updateNotebook(suggestionTestimonyResponse);
                gameboardController.updateNotebookObservables();
            }

            if(status == GameboardControllerStatus.ACCUSATION)
            {
                TurnStart gameUpdateTurnStart = client.waitForResponse(TurnStart.class);
                if(gameUpdateTurnStart.getAnnouncement().contains("incorrect"))
                {
                    endTurn = true;
                    gameboardController.getMoveOptions().setValidMoves(Collections.emptyList());
                }
                gameboardController.updateStatusBar(gameUpdateTurnStart.getAnnouncement());
               // System.out.println(gameUpdateTurnStart.getAnnouncement());
                client.write(gameUpdateTurnStart);
            }

            gameboardController.resetStatus();
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
        options.setSuggestionStatus(SuggestionStatus.MAKING_SUGGESTION);

        CaseDetails caseDetails = new CaseDetails();
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

    private void updateNotebook(SuggestionTestimonyResponse response)
    {
        response.optionalResponse().ifPresent(value -> client.getPlayer().getNotebook().makeKnownCard(value));
    }

    /**
     * goToSuggestionMenu, goToMovementMenu, and goToAccusationMenu create menus for their respective classes.
     */

    public void goToSuggestionMenu()
    {

        client.write(makeSuggestionChoice(suggestion));
        SuggestionTestimonyResponse response = client.waitForResponse(SuggestionTestimonyResponse.class);
        updateNotebook(response);

    }

    public void goToMovementMenu()
    {
        client.write(makeMoveChoice(moveOptions));
    }

    public void goToAccusationMenu()
    {
        client.write(makeAccusationChoice(accusation));

        //End the turn if you have made an accusation.
        this.endTurn = true;

    }


    /**
     * Getters and setters for instance fields
     **/
    public boolean isCanSuggest()
    {
        return canSuggest;
    }

    public void setCanSuggest(boolean canSuggest)
    {
        this.canSuggest = canSuggest;
    }

    public boolean isCanMove()
    {
        return canMove;
    }

    public void setCanMove(boolean canMove)
    {
        this.canMove = canMove;
    }

    public Suggestion getSuggestion()
    {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion)
    {
        this.suggestion = suggestion;
    }

    public Accusation getAccusation()
    {
        return accusation;
    }

    public void setAccusation(Accusation accusation)
    {
        this.accusation = accusation;
    }

    public MoveOptions getMoveOptions()
    {
        return moveOptions;
    }

    public void setMoveOptions(MoveOptions moveOptions)
    {
        this.moveOptions = moveOptions;
    }

    public TurnStart getTurnStart()
    {
        return turnStart;
    }

    public void setTurnStart(TurnStart turnStart)
    {
        this.turnStart = turnStart;
    }

    /**
     * unpackProtocolOffer unpacks the protocol offer.  For each field, it sets the ActivPlayerProtocol instance field.  It also sets the
     * instance boolean values for canMove and canSuggest.
     ***/
    private void unPackProtocolOffer(ServerActivePlayerProtocolOfferer sp)
    {
        sp.getOfferMoveOptions().ifPresent(moveOptions -> {
            this.setMoveOptions(moveOptions);
            this.setCanMove(true);
            gameboardController.setMoveOptions(moveOptions);
        });

        sp.getOfferSuggestion().ifPresent(suggestion -> {
            this.setSuggestion(suggestion);
            this.setCanSuggest(true);
        });

        sp.getOfferAccusation().ifPresent(this::setAccusation);

    }

    /**
     * EndTurn signals to the server to end the turn by sending an ActivPlayerProtocolSelector object with three null fields.
     ***/

    public void EndTurn()
    {
        this.endTurn = true;
        client.write(new ActivePlayerProtocolSelector(null, null, null));
    }


}




