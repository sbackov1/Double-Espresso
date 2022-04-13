package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameBoard;

import java.util.Random;

class WaitingPlayerProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;

    public WaitingPlayerProtocol(ClueLessClient client)
    {
        this.client = client;
    }

    @Override
    public void execute(GameBoard gameBoard)
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
