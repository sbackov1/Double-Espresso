package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.ClueLessProtocolType;
import edu.jhu.espresso.client.fx.GameboardController;

/**
 * Protocol factory is responsible for finding the next protocol type for each player, whether active, waiting,
 * accusation, suggestion, or informational.
 * **/

public class ProtocolFactory
{
    private final GameboardController gameboardController;

    public ProtocolFactory(GameboardController gameboardController)
    {
        this.gameboardController = gameboardController;
    }

    public ClueLessProtocol determineNextProtocol(ClueLessProtocolType clueLessProtocolType, ClueLessClient client)
    {
        ClueLessProtocol protocol;
        switch (clueLessProtocolType)
        {
            case ACTIVE_PLAYER:
                protocol = new ActivePlayerProtocol(gameboardController, client);
                break;
            case WAITING_PLAYER:
                protocol = new WaitingPlayerProtocol(gameboardController);
                break;
            case ACCUSATION:
                protocol = new ClientAccusationProtocol(client);
                break;
            case SUGGESTION:
                protocol = new ClientSuggestionTestimonyProtocol(client, gameboardController);
                break;
            case INFORMATIONAL:
                protocol = new InformationalProtocol(gameboardController, client);
                break;
            default:
                throw new IllegalArgumentException(clueLessProtocolType + " not supported!");
        }
        return protocol;
    }
}
