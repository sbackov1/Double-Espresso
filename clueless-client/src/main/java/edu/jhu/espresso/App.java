package edu.jhu.espresso;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        MessageStub messageStub = new MessageStub();
        messageStub.setMessage("Client message");
        messageStub.setSomeData(true);

        List<ClueLessClient> clients = new ArrayList<>();
        for (int i = 0; i < 6; i++)
        {
            clients.add(new ClueLessClient("localhost", 8080));
        }

        clients.forEach(client -> client.write(messageStub));
        clients.forEach(ClueLessClient::listen);
    }
}
