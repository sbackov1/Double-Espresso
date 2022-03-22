package edu.jhu.espresso.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClueLessClientHandler implements Runnable
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static int NUM_HANDLERS = 0;

    private final PrintWriter printWriter;
    private final BufferedReader bufferedReader;
    private final int handlerNumber;
    private final Socket socket;
    private final MessageStub messageStub = new MessageStub();

    public ClueLessClientHandler(Socket socket) throws IOException
    {
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
        handlerNumber = NUM_HANDLERS;
        NUM_HANDLERS++;

        messageStub.setMessage("Client handler " + handlerNumber);
        messageStub.setTurnIndicator(handlerNumber == 0 ? TurnIndicator.ACTIVE_PLAYER : TurnIndicator.WAITING_PLAYER);
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                bufferedReader.mark(1_000);
                if(bufferedReader.readLine() != null)
                {
                    bufferedReader.reset();
                    System.out.println("handler " + handlerNumber + " received " + OBJECT_MAPPER.readValue(bufferedReader.readLine(), MessageStub.class));
                }
            }
            catch (IOException e)
            {
                throw new IllegalStateException(e);
            }
        }
    }

    public void write(TurnIndicator turnIndicator, String message)
    {
        try
        {
            MessageStub messageStub = new MessageStub();
            messageStub.setMessage(message);
            messageStub.setTurnIndicator(turnIndicator);
            printWriter.println(OBJECT_MAPPER.writeValueAsString(messageStub));
        }
        catch (JsonProcessingException e)
        {
            throw new IllegalStateException(e);
        }
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
