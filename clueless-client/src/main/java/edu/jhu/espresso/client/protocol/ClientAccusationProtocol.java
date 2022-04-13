package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameBoard;

class ClientAccusationProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;

    public ClientAccusationProtocol(ClueLessClient clueLessClient)
    {
        this.client = clueLessClient;
    }

    @Override
    public void execute(GameBoard gameBoard)
    {

    }
}
