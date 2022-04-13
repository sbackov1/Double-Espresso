package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.ClueLessClientHandler;
import edu.jhu.espresso.server.domain.*;

import java.util.List;

public class SuggestionTestimonyProtocol
{
    private final List<ClueLessClientHandler> waitingPlayers;
    private final Suggestion suggestion;
    private final Game game;

    public SuggestionTestimonyProtocol(List<ClueLessClientHandler> waitingPlayers, Suggestion suggestion, Game game)
    {
        this.waitingPlayers = waitingPlayers;
        this.suggestion = suggestion;
        this.game = game;
    }

    public void execute()
    {
        System.out.println("Server suggestion logic executes");
        boolean suggestionDisproven = false;
        int waitingPlayerIndex = 0;
        while(!suggestionDisproven && waitingPlayerIndex < waitingPlayers.size())
        {
            ClueLessClientHandler waitingHandler = waitingPlayers.get(waitingPlayerIndex);
            waitingHandler.writeInstanceAndExpectType(
                    new TurnStart(ClueLessProtocolType.SUGGESTION),
                    TurnStart.class
            );

            waitingHandler.write(game.getLocations());

            Suggestion response = waitingHandler.writeInstanceAndExpectType(
                    suggestion,
                    Suggestion.class
            );

            suggestionDisproven = response.getSuggestionStatus() == SuggestionStatus.PROVING_SUGGESTION_FALSE;
            if(suggestionDisproven)
            {
                System.out.println("Suggestion has been disproven");
            }
            else
            {
                System.out.println("Suggestion cannot be disproven");
            }
            waitingPlayerIndex++;
        }
    }
}
