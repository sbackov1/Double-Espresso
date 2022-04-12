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
                protocol = new ActivePlayerProtocolStub(client);
                break;
            case WAITING_PLAYER:
                protocol = new WaitingPlayerProtocolStub(client);
                break;
            case ACCUSATION:
                protocol = new ClientAccusationProtocol(client);
                break;
            case SUGGESTION:
                protocol = new ClientSuggestionProtocol(client);
                break;
            default:
                throw new IllegalArgumentException(clueLessProtocolType + " not supported!");
        }
        return protocol;
    }
}
