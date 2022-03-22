package edu.jhu.espresso;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static edu.jhu.espresso.SocketUtils.writeMessage;
import static edu.jhu.espresso.SocketUtils.readMessage;

public class Server {
    private final ServerSocket serverSocket;
    private final int LISTEN_PORT = 8080;
    private final String LISTEN_ADDRESS = "localhost";
    private Socket client;
    private final Object serverMessage = new Card("Suspect", "Professor Plum");

    private static ArrayList<CluelessClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(6);


    public Server() throws IOException {
        this.serverSocket = new ServerSocket(LISTEN_PORT, 0, InetAddress.getByName(LISTEN_ADDRESS));
        this.client = serverSocket.accept();
        /*
        BufferedReader input = new BufferedReader(new InputStreamReader( client.getInputStream()));
        PrintWriter output = new PrintWriter(client.getOutputStream(), true);

        readMessage(this.client, input);
        writeMessage(this.client,output, serverMessage);
        input.close();
        output.close();

         */
        while (true) {
            this.client = serverSocket.accept();
            CluelessClientHandler clientThread = new CluelessClientHandler(this.client);
            clients.add(clientThread);

            pool.execute(clientThread);
        }
    }

    public Server(int sPort, String sAddress) throws IOException {
        this.serverSocket = new ServerSocket(sPort, 0, InetAddress.getByName(sAddress));
        // this.client = serverSocket.accept();

        /*
        BufferedReader input = new BufferedReader(new InputStreamReader( client.getInputStream()));
        PrintWriter output = new PrintWriter(client.getOutputStream(), true);

        readMessage(this.client, input);
        writeMessage(this.client,output, serverMessage);
        input.close();
        output.close();

         */

        while (true) {
            this.client = serverSocket.accept();
            CluelessClientHandler clientThread = new CluelessClientHandler(this.client);
            clients.add(clientThread);

            pool.execute(clientThread);
        }
    }

    

}



