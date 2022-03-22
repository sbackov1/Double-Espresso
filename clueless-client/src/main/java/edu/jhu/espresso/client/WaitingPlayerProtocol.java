package edu.jhu.espresso.client;

import java.util.List;

public class WaitingPlayerProtocol
{
    private final ClueLessClient client;
    private final List<String> validMoves;

    public WaitingPlayerProtocol(List<String> validMoves, ClueLessClient client)
    {
        this.client = client;
        this.validMoves = validMoves;
    }

    public void execute()
    {
        MessageStub messageStub = new MessageStub();
        messageStub.setMessage(validMoves.get(0));
        messageStub.setTurnIndicator(TurnIndicator.WAITING_PLAYER);
        client.write(messageStub);
    }
}
