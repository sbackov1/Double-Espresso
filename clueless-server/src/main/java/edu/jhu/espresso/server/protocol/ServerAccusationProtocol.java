package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.ClueLessClientHandler;
import edu.jhu.espresso.server.domain.Accusation;

import java.util.List;

public class ServerAccusationProtocol
{
    private final ClueLessClientHandler activePlayerHandler;
    private final List<ClueLessClientHandler> waitingPlayerHandlers;
    private final Accusation accusation;

    public ServerAccusationProtocol(
            ClueLessClientHandler activePlayerHandler,
            List<ClueLessClientHandler> waitingPlayerHandlers,
            Accusation accusation
    ) {
        this.activePlayerHandler = activePlayerHandler;
        this.waitingPlayerHandlers = waitingPlayerHandlers;
        this.accusation = accusation;
    }

    public void execute()
    {

    }
}
