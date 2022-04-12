package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.ClueLessClientHandler;
import edu.jhu.espresso.server.domain.Suggestion;

import java.util.List;

public class SuggestionTestimonyProtocol
{
    private final List<ClueLessClientHandler> waitingPlayers;
    private final Suggestion suggestion;

    public SuggestionTestimonyProtocol(List<ClueLessClientHandler> waitingPlayers, Suggestion suggestion)
    {
        this.waitingPlayers = waitingPlayers;
        this.suggestion = suggestion;
    }

    public void execute()
    {

    }
}
