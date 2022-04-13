package edu.jhu.espresso.client.domain;

import java.util.ArrayList;

public class GameLobby {

    private ArrayList<String> characters;
    private Character character;
    private int gameTimer;

    public ArrayList<String> getCharacters()
    {
        return characters;
    }

    public void setCharacters(ArrayList<String> characters)
    {
        this.characters = characters;
    }

    public void setHost()
    {

    }
    public String toString()
    {
        return "Character{" +
                "Chosen character=" + character +
                '}';
    }

    public void printToString()
    {
        String output = toString();
        System.out.println("\nYou are " + character);
    }

    public void LobbyMenu()
    {
        Menu lobby = new Menu();
        lobby.setTitle("*** Select Your Character ***");

        for (String validCharacter : this.characters)
        {
            lobby.addItem(new MenuItem(validCharacter, this, "setCharacterString", validCharacter));
        }
        lobby.addItem(new MenuItem("Confirm Character Selection", this, "printToString", null));
        lobby.execute();
    }

    private void setCharacter(Character character)
    {
        this.character = character;
    }

    private void setCharacterString(String characterString)
    {
        this.character = Character.valueOf(characterString);
        System.out.println("\n" + this.character + " was selected.");

    }
}
