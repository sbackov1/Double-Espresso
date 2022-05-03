package edu.jhu.espresso.server;

import edu.jhu.espresso.server.domain.PreGame.GameFoyer;
import edu.jhu.espresso.server.domain.gamepieces.Character;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;
import edu.jhu.espresso.server.domain.Game;
import edu.jhu.espresso.server.domain.gamepieces.Player;
import edu.jhu.espresso.server.protocol.ClueLessServerGameProtocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws IOException
    {
        GameFoyer.makeGameFoyer();
    }

    public static boolean log = true;

    public static void logMessage(String message)
    {
        if(log)
        {
            System.out.println(message);
        }
    }
}
