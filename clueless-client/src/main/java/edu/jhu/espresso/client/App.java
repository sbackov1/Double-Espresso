package edu.jhu.espresso.client;

public class App
{
    public static boolean log = true;

    public static void main( String[] args )
    {
        new Thread(new ClueLessClient("localhost", 8080)).start();
    }

    public static void logMessage(String message)
    {
        if(log)
        {
            System.out.println(message);
        }
    }
}
