package edu.jhu.espresso.client;

public class App
{
    public static void main( String[] args )
    {
        new Thread(new ClueLessClient("localhost", 8080)).start();
    }
}
