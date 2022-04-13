package edu.jhu.espresso.server;

import edu.jhu.espresso.server.domain.Game;
import edu.jhu.espresso.server.protocol.ClueLessServerGameProtocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

        List<ClueLessClientHandler> clueLessClientHandlers = new ArrayList<>();
        for(int i = 0; i < 2; i++)
        {
            clueLessClientHandlers.add(clueLessServer.accept());
        }

        Game game = new Game(0, new ArrayList<>());

        ClueLessServerGameProtocol clueLessServerGameProtocol = new ClueLessServerGameProtocol(clueLessClientHandlers, game);

        while(true)
        {
            try
            {
                Thread.sleep(2500L);
                clueLessServerGameProtocol.playGame();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
