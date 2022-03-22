package edu.jhu.espresso.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClueLessClient implements Runnable
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
            socket = new Socket(host, port);
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

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                input.mark(1_000);
                if(input.readLine() != null)
                {
                    input.reset();
                    MessageStub messageStub = OBJECT_MAPPER.readValue(input.readLine(), MessageStub.class);
                    if(messageStub.getTurnIndicator() == TurnIndicator.ACTIVE_PLAYER)
                    {
                        ActivePlayerProtocol activePlayerProtocol = new ActivePlayerProtocol(
                                messageStub,
                                this
                        );
                        activePlayerProtocol.execute();
                    }
                    else
                    {
                        WaitingPlayerProtocol waitingPlayerProtocol = new WaitingPlayerProtocol(
                                messageStub,
                                this
                        );
                        waitingPlayerProtocol.execute();
                    }
                }
            }
            catch (IOException e)
            {
                throw new IllegalStateException(e);
            }
        }
    }
}
