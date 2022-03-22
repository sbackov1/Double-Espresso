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

        List<Thread> handlerThreads = new ArrayList<>();

        clueLessClientHandlers.forEach(handler -> {
            Thread handlerThread = new Thread(handler);
            handlerThreads.add(handlerThread);
            handlerThread.start();
        });

        int activePlayerIndex = 0;
        while(true)
        {
            try
            {
                Thread.sleep(2500L);
                ClueLessClientHandler activeHandler = clueLessClientHandlers.get(activePlayerIndex);
                activeHandler.write(TurnIndicator.ACTIVE_PLAYER, "Valid moves");

                clueLessClientHandlers.stream()
                        .filter(handler -> handler != activeHandler)
                        .forEach(handler -> handler.write(TurnIndicator.WAITING_PLAYER, ""));

                activePlayerIndex = (activePlayerIndex + 1) % clueLessClientHandlers.size();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
