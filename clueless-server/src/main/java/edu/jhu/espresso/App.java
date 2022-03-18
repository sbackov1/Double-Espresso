package edu.jhu.espresso;

import java.io.IOException;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        ClueLessServer clueLessServer = new ClueLessServer();
        Client client = new Client();

        clueLessServer.accept();
    }
}
