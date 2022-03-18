package edu.jhu.espresso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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

    public void accept() throws IOException
    {
        Socket client = serverSocket.accept();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(client.getInputStream());
        byte[] helloBytes = new byte[11];
        bufferedInputStream.read(helloBytes);
        String hello = new String(helloBytes);
        System.out.println(hello);
    }
}
