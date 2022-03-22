package edu.jhu.espresso.client;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        List<ClueLessClient> clients = new ArrayList<>();
        for (int i = 0; i < 6; i++)
        {
            clients.add(new ClueLessClient("localhost", 8080));
        }

//        clients.forEach(client -> client.write(messageStub));
        clients.forEach(client -> new Thread(client).start());
    }
}
