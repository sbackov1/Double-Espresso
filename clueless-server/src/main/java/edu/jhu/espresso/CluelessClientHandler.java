package edu.jhu.espresso;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class CluelessClientHandler implements Runnable {
   // private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static int NUM_HANDLERS = 0;

    private final int CONNECT_PORT = 8080;
    private final String CONNECT_ADDRESS = "localhost";

    private  PrintWriter printWriter;
    private  BufferedReader bufferedReader;
    private  int handlerNumber;
    private Socket socket;
    private final Object clientMessage = new Card("Suspect", "Professor Plum");

    public CluelessClientHandler(Socket socket) throws IOException
    {

        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        handlerNumber = NUM_HANDLERS;
        NUM_HANDLERS++;
    }

    public CluelessClientHandler() throws IOException {
    }

    @Override
    public void run()
    {
        try
        {
            SocketUtils.readMessage(this.socket, getBufferedReader());
            SocketUtils.writeMessage(this.socket, getPrintWriter(), clientMessage );


        } catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
        finally {
            printWriter.close();
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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