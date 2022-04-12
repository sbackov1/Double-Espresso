package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameState;

import java.util.Random;

class WaitingPlayerProtocolStub implements ClueLessProtocol
{
    private final ClueLessClient client;

    public WaitingPlayerProtocolStub(ClueLessClient client)
    {
        this.client = client;
    }

    public void execute(GameState gameState)
    {
        try
        {
            Thread.sleep(Math.abs(new Random().nextInt()) % 10);
        }
        catch (InterruptedException e)
        {
            throw new IllegalStateException(e);
        }
    }
}
