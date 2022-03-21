package edu.jhu.espresso;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Client {
    private Socket socket;
    private final int CONNECT_PORT = 8080;
    private final String CONNECT_ADDRESS = "localhost";

    public Client() throws IOException {
        this.socket = new Socket(CONNECT_ADDRESS, CONNECT_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader( this.socket.getInputStream()));
        PrintWriter output = new PrintWriter(this.socket.getOutputStream(), true);

        connect(this.socket, output);
        sendData(this.socket, input);
        output.close();
        input.close();
    }

    public Client(int cport, String cadd) throws IOException {
        this.socket = new Socket(cadd, cport);

        BufferedReader input = new BufferedReader(new InputStreamReader( this.socket.getInputStream()));
        PrintWriter output = new PrintWriter(this.socket.getOutputStream(), true);

        connect(this.socket, output);
        sendData(this.socket, input);
        output.close();
        input.close();
    }

    public void sendData(Socket aSocket, BufferedReader in) throws IOException {


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

    public void connect(Socket aSocket, PrintWriter out) throws IOException {



        //Card sendCard = new Card("Suspect", "Professor Plum");
        Card sendCard = new Card();
        JsonNode cardNode = Json.toJson(sendCard);

        // PrintWriter out = new PrintWriter(aSocket.getOutputStream(), true);
        out.println("Hello World");
        String cardString = Json.jsonString(cardNode, false);
        System.out.println(Json.jsonString(cardNode, true));

        out.println(cardString);
        //out.close();

    }

}


