package edu.jhu.espresso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    private final String host;
    private final int port;
    private final Socket socket;
    private final PrintWriter printWriter;
    private final BufferedReader input;

    public Client(String host, int port)
    {
        this.host = host;
        this.port = port;
        try
        {
            socket = new Socket(this.host, this.port);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
    }

    public void write(String message)
    {
        printWriter.println(message);
    }

    public void listen()
    {
        try
        {
            System.out.println(input.readLine());
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
    }
}
