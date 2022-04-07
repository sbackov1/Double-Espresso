package edu.jhu.espresso.client;

import edu.jhu.espresso.client.domain.MoveOptions;
import edu.jhu.espresso.client.domain.TurnIndicator;
import edu.jhu.espresso.client.domain.TurnStart;

import java.util.Random;

public class WaitingPlayerProtocolStub
{
    private final ClueLessClient client;

    public WaitingPlayerProtocolStub(ClueLessClient client)
    {
        this.client = client;
    }

    public void execute(TurnStart turnStart)
    {
        try
        {
            Thread.sleep(Math.abs(new Random().nextInt()) % 10);
        }
        catch (InterruptedException e)
        {
            throw new IllegalStateException(e);
        }
        System.out.println("player acknowledging waiting status");
        client.write(turnStart);
    }
}
