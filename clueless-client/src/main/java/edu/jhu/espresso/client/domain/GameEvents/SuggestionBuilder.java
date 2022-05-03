package edu.jhu.espresso.client.domain.GameEvents;

import edu.jhu.espresso.client.domain.GamePieces.CaseDetails;
import edu.jhu.espresso.client.domain.GamePieces.CharacterNames;
import edu.jhu.espresso.client.domain.GamePieces.RoomNames;
import edu.jhu.espresso.client.domain.GamePieces.Weapon;

import java.util.ArrayList;

public final class SuggestionBuilder {
    private SuggestionStatus suggestionStatus;
    private Weapon weapon;
    private RoomNames roomNames;
    private CharacterNames character;
    private CaseDetails caseDetails;
    private ArrayList<String> validCharacters;
    private ArrayList<String> validWeapons;
    private String responseValue;

    private SuggestionBuilder() {
    }

    public static SuggestionBuilder aSuggestion() {
        return new SuggestionBuilder();
    }

    public SuggestionBuilder withSuggestionStatus(SuggestionStatus suggestionStatus) {
        this.suggestionStatus = suggestionStatus;
        return this;
    }

    public SuggestionBuilder withWeapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    public SuggestionBuilder withRoomNames(RoomNames roomNames) {
        this.roomNames = roomNames;
        return this;
    }

    public SuggestionBuilder withCharacter(CharacterNames character) {
        this.character = character;
        return this;
    }

    public SuggestionBuilder withCaseDetails(CaseDetails caseDetails) {
        this.caseDetails = caseDetails;
        return this;
    }

    public SuggestionBuilder withValidCharacters(ArrayList<String> validCharacters) {
        this.validCharacters = validCharacters;
        return this;
    }

    public SuggestionBuilder withValidWeapons(ArrayList<String> validWeapons) {
        this.validWeapons = validWeapons;
        return this;
    }

    public SuggestionBuilder withResponseValue(String responseValue) {
        this.responseValue = responseValue;
        return this;
    }

    public Suggestion build() {
        Suggestion suggestion = new Suggestion();
        suggestion.setSuggestionStatus(suggestionStatus);
        suggestion.setWeapon(weapon);
        suggestion.setRoomNames(roomNames);
        suggestion.setCharacter(character);
        suggestion.setCaseDetails(caseDetails);
        suggestion.setValidCharacters(validCharacters);
        suggestion.setValidWeapons(validWeapons);
        suggestion.setResponseValue(responseValue);
        return suggestion;
    }
}
