package edu.jhu.espresso.client.domain.GameEvents;

import edu.jhu.espresso.client.domain.GamePieces.Character;
import edu.jhu.espresso.client.domain.Menus.Menu;
import edu.jhu.espresso.client.domain.Menus.MenuItem;
import edu.jhu.espresso.client.domain.GamePieces.Player;

import java.util.ArrayList;

public class GameLobby {

    Menu lobby = new Menu();
    private ArrayList<String> characters;
    private Character character;
    private Player host;
    private ArrayList<Player> players;
    private final String[] times = {"1", "2", "5"};
    private boolean isHost = true;
    private int gameTimer;

    public ArrayList<String> getCharacters()
    {
        return characters;
    }

    public void setCharacters(ArrayList<String> characters)
    {
        this.characters = characters;
    }

    public Player getHost()
    {
        return host;
    }

    public void setHost(Player host)
    {
        this.host = host;
        this.isHost = true;
    }

    public void addPlayer(int clientID, Player player)
    {
        players.add(player);
    }

    @Override
    public String toString()
    {
        return "Character{" +
                "character=" + character +
                '}';
    }

    public void printToString()
    {
        String output = toString();
        if (character == null)
        {
            System.out.println("\nPlease select a character.");
        }
        else
        {
            System.out.println("\nYou are " + character);
        }
    }

    public void LobbyMenu()
    {
        lobby.setTitle("*** Game Lobby ***");
        lobby.addItem(new MenuItem("Character Selection", this, "CharacterMenu", null));
        lobby.addItem(new MenuItem("Confirm Character Selection", this, "printToString", null));

        if(this.isHost)
        {
            lobby.addItem(new MenuItem("Set Turn Timer", this, "TimerMenu", null));
            lobby.addItem(new MenuItem("Start Game", this, "startGame", null));
        }
        lobby.execute();
    }

    public void CharacterMenu()
    {
        Menu charMenu = new Menu();
        charMenu.setTitle("*** Select Character ***");
        for (String characterString : this.characters)
        {
            charMenu.addItem(new MenuItem(characterString, this, "setCharacterString", characterString));
        }
        charMenu.execute();
    }

    public void TimerMenu()
    {
        Menu timeMenu = new Menu();
        timeMenu.setTitle("*** Select Time (Minutes) ***");

        for (String timerString : this.times)
        {
            timeMenu.addItem(new MenuItem(timerString, this, "setTurnTimerString", timerString));
        }

        timeMenu.execute();
    }

    public void setCharacter(Character character)
    {
        this.character = character;
    }

    public void setCharacterString(String characterString)
    {
        this.character = Character.valueOf(characterString);
        System.out.println("\n" + this.character + " was selected.");

    }

    public void setTurnTimer(int gameTimer)
    {
        this.gameTimer = gameTimer;
    }

    public void setTurnTimerString(String timerString)
    {
        this.gameTimer = Integer.parseInt(timerString);
        System.out.println("\nThe current time limit for a turn is " + this.gameTimer + " minute(s).");
    }

    public void startGame()
    {
        // prevents host from starting game when not all players have chosen a character
        // starts game if all players have chosen a character, adds dummy characters if needed
        // closes game lobby menu
    }
}
