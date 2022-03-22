package edu.jhu.espresso.client;

import java.util.Random;

public class WaitingPlayerProtocol
{
    private final ClueLessClient client;
    private final MessageStub messageStub;

    public WaitingPlayerProtocol(MessageStub messageStub, ClueLessClient client)
    {
        this.client = client;
        this.messageStub = messageStub;
    }

    public void execute()
    {
        try
        {
            Thread.sleep(Math.abs(new Random().nextInt()) % 10);
        }
        catch (InterruptedException e)
        {
            throw new IllegalStateException(e);
        }

        MessageStub response = new MessageStub();
        response.setValidMoves(messageStub.getValidMoves());
        response.setHandlerNumber(messageStub.getHandlerNumber());
        response.setTurnIndicator(TurnIndicator.WAITING_PLAYER);
        client.write(response);
    }
}
