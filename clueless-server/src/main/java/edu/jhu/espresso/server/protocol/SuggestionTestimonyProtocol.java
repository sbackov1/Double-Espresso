package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.domain.*;
import edu.jhu.espresso.server.domain.builder.SuggestionTestimonyResponseBuilder;
import edu.jhu.espresso.server.domain.gameEvents.Suggestion;
import edu.jhu.espresso.server.domain.gameEvents.SuggestionStatus;
import edu.jhu.espresso.server.domain.gamepieces.*;

import edu.jhu.espresso.server.domain.gamepieces.CharacterCard;

import edu.jhu.espresso.server.domain.builder.SuggestionBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static edu.jhu.espresso.server.domain.gameEvents.SuggestionStatus.CANNOT_DISPROVE;
import static edu.jhu.espresso.server.domain.gameEvents.SuggestionStatus.PROVING_SUGGESTION_FALSE;

public class SuggestionTestimonyProtocol
{
    private final List<Player> waitingPlayers;
    private final Player activePlayer;
    private final Suggestion suggestion;
    private final Game game;

    private Suggestion response;
    private Player currentWaitingPlayer;

    public SuggestionTestimonyProtocol(List<Player> waitingPlayers, Player activePlayer, Suggestion suggestion, Game game)
    {
        this.waitingPlayers = waitingPlayers;
        this.activePlayer = activePlayer;
        this.suggestion = suggestion;
        this.game = game;
        this.currentWaitingPlayer = activePlayer.getNextPlayer();
        this.response = new Suggestion();
    }

    public void execute()
    {
        System.out.println("Server suggestion logic executes");
        boolean suggestionDisproven = false;
        int playersAsked = 0;


        SuggestionTestimonyResponseBuilder suggestionTestimonyResponseBuilder = SuggestionTestimonyResponseBuilder.aSuggestionTestimonyResponse();

        while(!suggestionDisproven && playersAsked < waitingPlayers.size())
        {
            //Split into 2 methods: if player isActive and !isActive




            suggestionDisproven = response.getSuggestionStatus() == SuggestionStatus.PROVING_SUGGESTION_FALSE;
            if(suggestionDisproven)
            {
                System.out.println("Suggestion has been disproven");
                broadcastSuggestionResults(
                        currentWaitingPlayer,
                        currentWaitingPlayer.getCharacter().getName() + " has disproven the suggestion."
                );
                suggestionTestimonyResponseBuilder.withResponse(response.getResponseValue());
            }
            else
            {
                System.out.println("Suggestion cannot be disproven");
                broadcastSuggestionResults(
                        currentWaitingPlayer,
                        currentWaitingPlayer.getCharacter().getName() + " cannot prove the suggestion false"
                );
            }
            playersAsked++;
            currentWaitingPlayer = currentWaitingPlayer.getNextPlayer();
        }

        if(!suggestionDisproven)
        {
            List<Player> allPlayers = new ArrayList<>();
            allPlayers.addAll(waitingPlayers);
            ClueLessServerGameProtocol.broadcast(
                                game,
                    "No one is able to disprove " + activePlayer.getCharacter().getName() + "'s suggestion",
                    allPlayers
            );
        }

        activePlayer.write(suggestionTestimonyResponseBuilder.build());
    }

    private void broadcastSuggestionResults(Player currentWaitingPlayer, String announcement)
    {
        List<Player> playersToNotify = waitingPlayers.stream()
                .filter(player -> player != currentWaitingPlayer)
                .collect(Collectors.toList());

        ClueLessServerGameProtocol.broadcast(
                        game,
                announcement,
                playersToNotify
        );
    }

    private void activePlayerQuerySetsResponse(){
        currentWaitingPlayer.writeInstanceAndExpectType(
                new TurnStart(ClueLessProtocolType.SUGGESTION, game.getLocations(), ""),
                TurnStart.class
        );

        this.response = currentWaitingPlayer.writeInstanceAndExpectType(
                suggestion,
                Suggestion.class
        );

    }

/**
 * inactivePlayerQuery checks whether any of the cards are present in the inactive player's hand.
 * If one card is present, it returns a suggestion response with that type of card.
 * If more than one is present, it randomly selects a card and uses that to respond to the query.
 * **/

    private Suggestion inactivePlayerQuery(){

        SuggestionBuilder inactiveSuggestion = SuggestionBuilder.aSuggestion();

        ArrayList<Card> playerHandCards = this.currentWaitingPlayer.getNotebook().getHandCards();
        ArrayList<Card> possibleDisproveCards = new ArrayList<Card>(playerHandCards);

        possibleDisproveCards.retainAll(buildCardsFromCaseDetails(suggestion));

        if (possibleDisproveCards.size() == 0){
            inactiveSuggestion.withSuggestionStatus(CANNOT_DISPROVE);
        }

        else if (possibleDisproveCards.size() == 1) {
            inactiveSuggestion.withSuggestionStatus(PROVING_SUGGESTION_FALSE);
            inactiveSuggestion.withResponseValue(possibleDisproveCards.get(0).getName());
            return inactiveSuggestion.build();
        }

        else {
            inactiveSuggestion.withSuggestionStatus(PROVING_SUGGESTION_FALSE);
            int cardIndex = (int) Math.random() * (possibleDisproveCards.size() - 1);

            inactiveSuggestion.withResponseValue(possibleDisproveCards.get(cardIndex).getName());

        }



    }

    private ArrayList<Card> buildCardsFromCaseDetails(Suggestion suggestion)
    {
        CaseDetails caseDetails = suggestion.getCaseDetails();

        RoomCard roomCard = new RoomCard(caseDetails.getRoom());
        WeaponCard weaponCard = new WeaponCard(caseDetails.getWeapon());
        CharacterCard characterCard = new CharacterCard(caseDetails.getCharacterNames());

        return new ArrayList<>(
                Arrays.asList(
                        roomCard,
                        weaponCard,
                        characterCard
                )
        );
    }

}
