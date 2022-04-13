package edu.jhu.espresso.server;

import edu.jhu.espresso.server.domain.Character;
import edu.jhu.espresso.server.domain.CharacterNames;
import edu.jhu.espresso.server.domain.Game;
import edu.jhu.espresso.server.domain.Player;
import edu.jhu.espresso.server.protocol.ClueLessServerGameProtocol;

import java.io.IOException;
import java.util.ArrayList;
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

        List<ClueLessClientHandler> clueLessClientHandlers = new ArrayList<>();
        for(int i = 0; i < 6; i++)
        {
            clueLessClientHandlers.add(clueLessServer.accept());
        }

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(0, 0, new Character(CharacterNames.PROFESSOR_PLUM)));
        Game game = new Game(0, players);

        ClueLessServerGameProtocol clueLessServerGameProtocol = new ClueLessServerGameProtocol(clueLessClientHandlers, game);

        while(true)
        {
            try
            {
                Thread.sleep(2500L);
                clueLessServerGameProtocol.playGame();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
