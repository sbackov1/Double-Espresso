package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.ClueLessClientHandler;
import edu.jhu.espresso.server.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class ClueLessServerGameProtocol
{
    private final List<ClueLessClientHandler> clientHandlers;
    private final Game game;

    private int activePlayerIndex = 0;

    public ClueLessServerGameProtocol(List<ClueLessClientHandler> clientHandlers, Game game)
    {
        this.clientHandlers = clientHandlers;
        this.game = game;
    }

    public void playGame()
    {
        boolean playing = true;
        while(playing)
        {
            ClueLessClientHandler activePlayerHandler = clientHandlers.get(activePlayerIndex);
            List<ClueLessClientHandler> waitingPlayers = clientHandlers.stream()
                    .filter(handler -> !handler.equals(activePlayerHandler))
                    .collect(Collectors.toList());

            playing = new ClueLessTurnProtocol(waitingPlayers, activePlayerHandler, game).executeTurn();
            activePlayerIndex = (activePlayerIndex + 1) % clientHandlers.size();
        }
    }
}
