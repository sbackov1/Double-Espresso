package edu.jhu.espresso;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClueLessClient
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final String host;
    private final int port;
    private final Socket socket;
    private final PrintWriter printWriter;
    private final BufferedReader input;

    public ClueLessClient(String host, int port)
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

    public void write(Object message)
    {
        try
        {
            printWriter.println(OBJECT_MAPPER.writeValueAsString(message));
        }
        catch (JsonProcessingException e)
        {
            throw new IllegalStateException(e);
        }
    }

    public void listen()
    {
        try
        {
            MessageStub messageStub = OBJECT_MAPPER.readValue(input, MessageStub.class);
            System.out.println(messageStub);
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
    }
}
