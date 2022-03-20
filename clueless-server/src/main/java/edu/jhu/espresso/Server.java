package edu.jhu.espresso;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        sendData(client);
    }

    public Server(int sPort, String sAddress) throws IOException {
        this.serverSocket = new ServerSocket(sPort, 0, InetAddress.getByName(sAddress));
        Socket client = serverSocket.accept();

        sendData(client);
    }

    public static void sendData(Socket aSocket) throws IOException {

        /*
        BufferedInputStream bufferedInputStream = new BufferedInputStream(aSocket.getInputStream());
        byte[] helloBytes = new byte[11];
        bufferedInputStream.read(helloBytes);
        String hello = new String(helloBytes);
        System.out.println(hello);

         */

        BufferedReader in = new BufferedReader(new InputStreamReader( aSocket.getInputStream()));
        String readString = in.readLine();
        System.out.println(readString);
        String jsonString = in.readLine();
        System.out.println(jsonString);
        in.close();
    }
}



