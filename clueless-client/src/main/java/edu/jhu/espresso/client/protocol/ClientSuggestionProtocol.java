package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameState;

class ClientSuggestionProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;

    public ClientSuggestionProtocol(ClueLessClient client)
    {
        this.client = client;
    }

    @Override
    public void execute(GameState gameState)
    {

    }
}
