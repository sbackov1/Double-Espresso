package edu.jhu.espresso.client.domain;

import java.util.ArrayList;

public class SuggestionDriver { // will need to get the player's location and set the room to it

    public static void main(String[] args)
    {
        ArrayList<String> validChars = new ArrayList<>();
        validChars.add("MISS_SCARLET");
        validChars.add("PROFESSOR_PLUM");

        ArrayList<String> validWeapons = new ArrayList<>();
        validWeapons.add("REVOLVER");
        validWeapons.add("WRENCH");

        Suggestion suggestion = new Suggestion();

        suggestion.setValidCharacters(validChars);
        suggestion.setValidWeapons(validWeapons);

        suggestion.mainSugMenu();
    }
}
