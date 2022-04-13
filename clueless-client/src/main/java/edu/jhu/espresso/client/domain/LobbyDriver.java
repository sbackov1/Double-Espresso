package edu.jhu.espresso.client.domain;

import java.util.ArrayList;

public class LobbyDriver {

    public static void main(String[] args)
    {
        Lobby lobby = new Lobby();
        ArrayList<String> characters = new ArrayList<>();
        characters.add("PROFESSOR_PLUM");
        characters.add("MRS_PEACOCK");
        characters.add("MISS_SCARLET");
        characters.add("COLONEL_MUSTARD");

        lobby.setCharacters(characters);

        lobby.LobbyMenu();

    }
}
