package edu.jhu.espresso.client.domain;

import java.util.ArrayList;

public class SuggestionResponseDriver {


   // private ArrayList<Card> validCards = new ArrayList<>();

    public static void main(String[] args) {

        ArrayList<Card> vCards = new ArrayList<>();
        vCards.add(new WeaponCard("DAGGER"));
        vCards.add(new RoomCard("STUDY"));
        vCards.add(new CharacterCard("PROFESSOR_PLUM"));

        SuggestionResponse suggestionResponse = new SuggestionResponse();
        suggestionResponse.setValidCards(vCards);

        suggestionResponse.mainSugMenu();

    }

}
