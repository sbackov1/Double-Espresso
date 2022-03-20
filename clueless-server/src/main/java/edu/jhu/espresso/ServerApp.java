package edu.jhu.espresso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerApp {
    private static final int LISTEN_PORT = 8080;
    private static final String LISTEN_ADDRESS = "localhost";

    public static void main(String[] args) throws IOException {

        Server aServer = new Server(LISTEN_PORT, LISTEN_ADDRESS);
    }

}
