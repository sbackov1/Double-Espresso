package edu.jhu.espresso;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ClientApp {
   private static final int CONNECT_PORT = 8080;
   private static final String CONNECT_ADDRESS = "localhost";

    public static void main(String[] args) throws IOException {

        Client aClient = new Client(CONNECT_PORT, CONNECT_ADDRESS);
    }
}
