package edu.jhu.espresso.server;

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
        ClueLessServer clueLessServer = new ClueLessServer();

        List<CharacterNames> characterNames = new ArrayList<>(Arrays.asList(CharacterNames.values()));

        ArrayList<Player> players = new ArrayList<>();
        for(int i = 0; i < 3; i++)
        {
            ClueLessClientHandler handler = clueLessServer.accept();
            players.add(new Player(0, i, new Character(characterNames.get(i)), handler));
        }

        Game game = new Game(0, players);

        ClueLessServerGameProtocol clueLessServerGameProtocol = new ClueLessServerGameProtocol(players, game);
       clueLessServerGameProtocol.playGame();
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
