package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.domain.*;
import edu.jhu.espresso.server.domain.builder.SuggestionTestimonyResponseBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SuggestionTestimonyProtocol
{
    private final List<Player> waitingPlayers;
    private final Player activePlayer;
    private final Suggestion suggestion;
    private final Game game;

    public SuggestionTestimonyProtocol(List<Player> waitingPlayers, Player activePlayer, Suggestion suggestion, Game game)
    {
        this.waitingPlayers = waitingPlayers;
        this.activePlayer = activePlayer;
        this.suggestion = suggestion;
        this.game = game;
    }

    public void execute()
    {
        System.out.println("Server suggestion logic executes");
        boolean suggestionDisproven = false;
        int playersAsked = 0;
        Player currentWaitingPlayer = activePlayer.getNextPlayer();

        SuggestionTestimonyResponseBuilder suggestionTestimonyResponseBuilder = SuggestionTestimonyResponseBuilder.aSuggestionTestimonyResponse();

        while(!suggestionDisproven && playersAsked < waitingPlayers.size())
        {
            currentWaitingPlayer.writeInstanceAndExpectType(
                    new TurnStart(ClueLessProtocolType.SUGGESTION, game.getLocations(), ""),
                    TurnStart.class
            );

            Suggestion response = currentWaitingPlayer.writeInstanceAndExpectType(
                    suggestion,
                    Suggestion.class
            );

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
}
