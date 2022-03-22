package edu.jhu.espresso.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    private MessageStub waitForClientResponse()
    {
        MessageStub response = null;
        while (response == null)
        {
            try
            {
                bufferedReader.mark(1_000);
                if(bufferedReader.readLine() != null)
                {
                    bufferedReader.reset();
                    response = OBJECT_MAPPER.readValue(bufferedReader.readLine(), MessageStub.class);
                }
            }
            catch (IOException e)
            {
                throw new IllegalStateException(e);
            }
        }
        return response;
    }

    public CompletableFuture<MessageStub> write(TurnIndicator turnIndicator, List<String> validMoves)
    {
        CompletableFuture<MessageStub> response;
        try
        {
            MessageStub messageStub = new MessageStub();
            messageStub.setValidMoves(validMoves);
            messageStub.setTurnIndicator(turnIndicator);
            messageStub.setHandlerNumber(handlerNumber);
            printWriter.println(OBJECT_MAPPER.writeValueAsString(messageStub));
            response = CompletableFuture.supplyAsync(this::waitForClientResponse);
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
