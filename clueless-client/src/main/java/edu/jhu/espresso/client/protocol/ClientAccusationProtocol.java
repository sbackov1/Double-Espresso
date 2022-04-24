package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameEvents.TurnStart;

class ClientAccusationProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;

    public ClientAccusationProtocol(ClueLessClient clueLessClient)
    {
        this.client = clueLessClient;
    }

    @Override
    public void execute(TurnStart turnStart)
    {

    }
}
