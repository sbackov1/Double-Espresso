package edu.jhu.espresso.server;

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

        List<ClueLessClientHandler> clueLessClientHandlers = new ArrayList<>();
        for(int i = 0; i < 6; i++)
        {
            clueLessClientHandlers.add(clueLessServer.accept());
        }

        ClueLessServerProtocolStub clueLessServerProtocolStub = new ClueLessServerProtocolStub(clueLessClientHandlers);

        while(true)
        {
            try
            {
                Thread.sleep(2500L);
                clueLessServerProtocolStub.runTurn();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
