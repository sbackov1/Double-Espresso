package edu.jhu.espresso.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.jhu.espresso.server.domain.MoveOptions;
import edu.jhu.espresso.server.domain.TurnIndicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ClueLessClientHandler
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static int NUM_HANDLERS = 0;

    private final PrintWriter printWriter;
    private final BufferedReader bufferedReader;
    private final int handlerNumber;
    private final Socket socket;

    public ClueLessClientHandler(Socket socket) throws IOException
    {
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
        handlerNumber = NUM_HANDLERS;
        NUM_HANDLERS++;
    }

    private <T> T waitForClientResponse(Class<T> clazz)
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
            response = CompletableFuture.supplyAsync(() -> waitForClientResponse(responseClass));
        }
        catch (JsonProcessingException e)
        {
            throw new IllegalStateException(e);
        }

        return response;
    }

    public PrintWriter getPrintWriter()
    {
        return printWriter;
    }

    public BufferedReader getBufferedReader()
    {
        return bufferedReader;
    }
}
