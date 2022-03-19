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
        MessageStub messageStub = new MessageStub();
        messageStub.setMessage("Server message");
        messageStub.setSomeData(false);

        ClueLessServer clueLessServer = new ClueLessServer();

        List<ClueLessClientHandler> clueLessClientHandlers = new ArrayList<>();
        for(int i = 0; i < 6; i++)
        {
            clueLessClientHandlers.add(clueLessServer.accept());
        }

        clueLessClientHandlers.forEach(handler -> new Thread(handler).start());
        clueLessClientHandlers.forEach(clueLessClientHandler -> clueLessClientHandler.write(messageStub));
    }
}
