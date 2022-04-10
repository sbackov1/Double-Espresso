package edu.jhu.espresso.client.domain;

import java.util.ArrayList;

public class AccusationDriver {

    public static void main(String[] args) {

        ArrayList<String> validChars = new ArrayList<>();
        validChars.add("MISS_SCARLET");
        validChars.add("PROFESSOR_PLUM");


        ArrayList<String> validRooms = new ArrayList<>();
        validRooms.add("KITCHEN");
        validRooms.add("CELLAR");

        ArrayList<String> validWeapons = new ArrayList<>();
        validWeapons.add("REVOLVER");
        validWeapons.add("WRENCH");

        Accusation accusation = new Accusation();

        accusation.setValidCharacters(validChars);
        accusation.setValidRooms(validRooms);
        accusation.setValidWeapons(validWeapons);

        accusation.mainAccMenu();

    }
}
