package edu.jhu.espresso.client;

import java.util.List;

public class ActivePlayerProtocol
{
    private final List<String> validMoves;
    private final ClueLessClient client;

    public ActivePlayerProtocol(List<String> validMoves, ClueLessClient client)
    {
        this.validMoves = validMoves;
        this.client = client;
    }

    public void execute()
    {
        MessageStub messageStub = new MessageStub();
        messageStub.setMessage(validMoves.get(0));
        messageStub.setTurnIndicator(TurnIndicator.ACTIVE_PLAYER);
        client.write(messageStub);
    }
}
