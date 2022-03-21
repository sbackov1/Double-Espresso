package edu.jhu.espresso;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;
    private final int LISTEN_PORT = 8080;
    private final String LISTEN_ADDRESS = "localhost";


    public Server() throws IOException {
        this.serverSocket = new ServerSocket(LISTEN_PORT, 0, InetAddress.getByName(LISTEN_ADDRESS));
        Socket client = serverSocket.accept();

        BufferedReader input = new BufferedReader(new InputStreamReader( client.getInputStream()));
        PrintWriter output = new PrintWriter(client.getOutputStream(), true);

        sendData(client, input);
        connect(client,output);
        input.close();
        output.close();
    }

    public Server(int sPort, String sAddress) throws IOException {
        this.serverSocket = new ServerSocket(sPort, 0, InetAddress.getByName(sAddress));
        Socket client = serverSocket.accept();

        BufferedReader input = new BufferedReader(new InputStreamReader( client.getInputStream()));
        PrintWriter output = new PrintWriter(client.getOutputStream(), true);

        sendData(client, input);
        connect(client,output);
        input.close();
        output.close();
    }

    public static void sendData(Socket aSocket, BufferedReader in) throws IOException {


      //  BufferedReader in = new BufferedReader(new InputStreamReader( aSocket.getInputStream()));
        String readString = in.readLine();
        System.out.println(readString);
        String cardString = in.readLine();


        JsonNode cardNode = Json.parse(cardString);
        Card aCard = Json.fromJson(cardNode, Card.class);

        System.out.println(cardString);
        System.out.println(aCard.getCardType());
        System.out.println(aCard.getCardValue());
        //in.close();
    }

    public static void connect(Socket aSocket, PrintWriter out) throws IOException {



        Card sendCard = new Card("Suspect", "Professor Plum");
        //Card sendCard = new Card();
        JsonNode cardNode = Json.toJson(sendCard);

       // PrintWriter out = new PrintWriter(aSocket.getOutputStream(), true);
        out.println("Hello World");
        String cardString = Json.jsonString(cardNode, false);
        System.out.println(Json.jsonString(cardNode, true));

        out.println(cardString);
        //out.close();

    }

}



