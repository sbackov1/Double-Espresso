package edu.jhu.espresso;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private final int CONNECT_PORT = 8080;
    private final String CONNECT_ADDRESS = "localhost";
    private final Object clientMessage = new Card();

    public Client() throws IOException {
        this.socket = new Socket(CONNECT_ADDRESS, CONNECT_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader( this.socket.getInputStream()));
        PrintWriter output = new PrintWriter(this.socket.getOutputStream(), true);

        SocketUtils.writeMessage(this.socket, output, clientMessage );
        SocketUtils.readMessage(this.socket, input);
        output.close();
        input.close();
        Scanner terminate = new Scanner(System.in);
        System.out.println("Close Client?");
        terminate.nextLine();
        output.close();
        input.close();
        terminate.close();
    }

    public Client(int cport, String cadd) throws IOException {
        this.socket = new Socket(cadd, cport);

        BufferedReader input = new BufferedReader(new InputStreamReader( this.socket.getInputStream()));
        PrintWriter output = new PrintWriter(this.socket.getOutputStream(), true);

        SocketUtils.writeMessage(this.socket, output, clientMessage);
        SocketUtils.readMessage(this.socket, input);
        Scanner terminate = new Scanner(System.in);
        System.out.println("Close Client?");
        terminate.nextLine();
        output.close();
        input.close();
        terminate.close();
    }



}


