package edu.jhu.espresso;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Client
{
    private final Socket socket;

    public Client()
    {
        String host = "localhost";
        int port = 8080;
        try
        {
            DataInputStream in = new DataInputStream(System.in);
            socket = new Socket(host, port);
            OutputStream out = socket.getOutputStream();
            out.write("Hello world".getBytes(UTF_8));
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
    }

    public Socket getSocket()
    {
        return socket;
    }
}
