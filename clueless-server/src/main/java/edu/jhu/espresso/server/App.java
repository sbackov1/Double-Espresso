package edu.jhu.espresso.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

        ClueLessServerProtocol clueLessServerProtocol = new ClueLessServerProtocol(clueLessClientHandlers);

        while(true)
        {
            try
            {
                Thread.sleep(2500L);
                clueLessServerProtocol.runTurn();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
