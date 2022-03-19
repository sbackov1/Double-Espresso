package edu.jhu.espresso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        ClueLessServer clueLessServer = new ClueLessServer();

        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < 6; i++)
        {
            clients.add(new Client("localhost", 8080));
        }

        List<ClueLessClientHandler> clueLessClientHandlers = new ArrayList<>();
        for(int i = 0; i < 6; i++)
        {
            clueLessClientHandlers.add(clueLessServer.accept());
        }

        clients.forEach(client -> client.write("Hello"));
        clueLessClientHandlers.forEach(handler -> new Thread(handler).start());

        clueLessClientHandlers.forEach(clueLessClientHandler -> clueLessClientHandler.getPrintWriter().println("Yeet"));

        clients.forEach(Client::listen);
    }
}
