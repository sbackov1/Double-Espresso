package edu.jhu.espresso.client.domain.GamePieces;

import edu.jhu.espresso.client.domain.GameEvents.GameLobby;

import java.util.ArrayList;

public class LobbyDriver {

    public static void main(String[] args)
    {
        GameLobby lobby = new GameLobby();
        ArrayList<String> characters = new ArrayList<>();
        characters.add("PROFESSOR_PLUM");
        characters.add("MRS_PEACOCK");
        characters.add("MISS_SCARLET");
        characters.add("COLONEL_MUSTARD");

        lobby.setCharacters(characters);

        lobby.LobbyMenu();

    }
}
