package edu.jhu.espresso.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.jhu.espresso.client.domain.TurnIndicator;
import edu.jhu.espresso.client.domain.TurnStart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class ClueLessClient implements Runnable
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final String host;
    private final int port;
    private final Socket socket;
    private final PrintWriter printWriter;
    private final BufferedReader bufferedReader;

    public ClueLessClient(String host, int port)
    {
        this.host = host;
        this.port = port;
        try
        {
            socket = new Socket(host, port);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
                bufferedReader.mark(1_000);
                if(bufferedReader.readLine() != null)
                {
                    bufferedReader.reset();
                    TurnStart turnStart = OBJECT_MAPPER.readValue(bufferedReader.readLine(), TurnStart.class);
                    if(turnStart.getTurnIndicator() == TurnIndicator.ACTIVE_PLAYER)
                    {
                        ActivePlayerProtocolStub activePlayerProtocolStub = new ActivePlayerProtocolStub(this);
                        activePlayerProtocolStub.execute(turnStart);
                    }
                    else
                    {
                        WaitingPlayerProtocolStub waitingPlayerProtocolStub = new WaitingPlayerProtocolStub(this);
                        waitingPlayerProtocolStub.execute(turnStart);
                    }
                }
            }
            catch (IOException e)
            {
                throw new IllegalStateException(e);
            }
        }
    }

    public <T> T waitForResponse(Class<T> clazz)
    {
        T response = null;
        while (response == null)
        {
            try
            {
                bufferedReader.mark(1_000);
                if(bufferedReader.readLine() != null)
                {
                    bufferedReader.reset();
                    response = OBJECT_MAPPER.readValue(bufferedReader.readLine(), clazz);
                }
            }
            catch (IOException e)
            {
                throw new IllegalStateException(e);
            }
        }
        return response;
    }

    public <I,O> CompletableFuture<O> write(I input, Class<O> responseClass)
    {
        CompletableFuture<O> response;
        try
        {
            printWriter.println(OBJECT_MAPPER.writeValueAsString(input));
            response = CompletableFuture.supplyAsync(() -> waitForResponse(responseClass));
        }
        catch (JsonProcessingException e)
        {
            throw new IllegalStateException(e);
        }

        return response;
    }

}
