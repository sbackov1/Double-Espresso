package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameEvents.TurnStart;

class WaitingPlayerProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;

    public WaitingPlayerProtocol(ClueLessClient client)
    {
        this.client = client;
    }

    @Override
    public void execute(TurnStart turnStart)
    {
    }
}
