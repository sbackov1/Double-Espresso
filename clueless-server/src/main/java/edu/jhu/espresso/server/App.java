package edu.jhu.espresso.server;

import edu.jhu.espresso.server.domain.Character;
import edu.jhu.espresso.server.domain.CharacterNames;
import edu.jhu.espresso.server.domain.Game;
import edu.jhu.espresso.server.domain.Player;
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
        for(int i = 0; i < 6; i++)
        {
            ClueLessClientHandler handler = clueLessServer.accept();
            players.add(new Player(0, i, new Character(characterNames.get(i)), handler));
        }

        Game game = new Game(0, players);

        ClueLessServerGameProtocol clueLessServerGameProtocol = new ClueLessServerGameProtocol(players, game);
        clueLessServerGameProtocol.playGame();
    }
}
