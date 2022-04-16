package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.ClueLessProtocolType;

public class ProtocolFactory
{
    public ClueLessProtocol determineNextProtocol(ClueLessProtocolType clueLessProtocolType, ClueLessClient client)
    {
        ClueLessProtocol protocol;
        switch (clueLessProtocolType)
        {
            case ACTIVE_PLAYER:
                protocol = new ActivePlayerProtocol(client);
                break;
            case WAITING_PLAYER:
                protocol = new WaitingPlayerProtocol(client);
                break;
            case ACCUSATION:
                protocol = new ClientAccusationProtocol(client);
                break;
            case SUGGESTION:
                protocol = new ClientSuggestionTestimonyProtocol(client);
                break;
            case INFORMATIONAL:
                protocol = new InformationalProtocol();
                break;
            default:
                throw new IllegalArgumentException(clueLessProtocolType + " not supported!");
        }
        return protocol;
    }
}
