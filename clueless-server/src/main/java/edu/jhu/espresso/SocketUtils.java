package edu.jhu.espresso;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketUtils {

    public static void readMessage(Socket aSocket, BufferedReader in) throws IOException {


        //  BufferedReader in = new BufferedReader(new InputStreamReader( aSocket.getInputStream()));


        String messageString = in.readLine();
        JsonNode messageNode = Json.parse(messageString);
        Card aCard = Json.fromJson(messageNode, Card.class);
        System.out.println();
        System.out.println("Printing received JSON String:\n");
        System.out.println(messageString);
        System.out.println();

        System.out.println("Printing received Object:\n");
        System.out.println(aCard.getCardType());
        System.out.println(aCard.getCardValue());
        System.out.println();
        //in.close();
    }

    public static void writeMessage(Socket aSocket, PrintWriter out, Object message) throws IOException {



        //Card sendCard = new Card("Suspect", "Professor Plum");
        //Card sendCard = new Card();
        JsonNode messageNode = Json.toJson(message);

        // PrintWriter out = new PrintWriter(aSocket.getOutputStream(), true);
        System.out.println("Sending Object as JSON:\n");


        String messageString = Json.jsonString(messageNode, false);
        System.out.println(Json.jsonString(messageNode, true));

        out.println(messageString);
        System.out.println();
        //out.close();

    }
}
