package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameBoard;
import edu.jhu.espresso.client.domain.Player;
import edu.jhu.espresso.client.domain.TurnStart;

import java.util.Random;

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
