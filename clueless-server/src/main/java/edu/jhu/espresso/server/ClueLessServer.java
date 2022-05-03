package edu.jhu.espresso.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ClueLessServer
{
    private final ServerSocket serverSocket;

    public ClueLessServer()
    {
        try
        {
            this.serverSocket = new ServerSocket(8080);
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
    }

    public ClueLessClientHandler accept() throws IOException
    {
        return new ClueLessClientHandler(serverSocket.accept());
    }
}

