package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.domain.gameEvents.Suggestion;
import edu.jhu.espresso.server.domain.gameEvents.SuggestionStatus;
import edu.jhu.espresso.server.domain.gamepieces.CaseDetails;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;
import edu.jhu.espresso.server.domain.gamepieces.RoomNames;
import edu.jhu.espresso.server.domain.gamepieces.Weapon;

import java.util.List;

public final class SuggestionBuilder
{
    private SuggestionStatus suggestionStatus;
    private Weapon weapon;
    private RoomNames roomNames;
    private CharacterNames character;
    private CaseDetails caseDetails;
    private List<String> validCharacters;
    private List<String> validWeapons;
    private String responseValue;

    private SuggestionBuilder()
    {
    }

    public static SuggestionBuilder aSuggestion()
    {
        return new SuggestionBuilder();
    }

    public SuggestionBuilder withSuggestionStatus(SuggestionStatus suggestionStatus)
    {
        this.suggestionStatus = suggestionStatus;
        return this;
    }

    public SuggestionBuilder withWeapon(Weapon weapon)
    {
        this.weapon = weapon;
        return this;
    }

    public SuggestionBuilder withRoomNames(RoomNames roomNames)
    {
        this.roomNames = roomNames;
        return this;
    }

    public SuggestionBuilder withCharacter(CharacterNames character)
    {
        this.character = character;
        return this;
    }

    public SuggestionBuilder withCaseDetails(CaseDetails caseDetails)
    {
        this.caseDetails = caseDetails;
        return this;
    }

    public SuggestionBuilder withValidCharacters(List<String> validCharacters)
    {
        this.validCharacters = validCharacters;
        return this;
    }

    public SuggestionBuilder withValidWeapons(List<String> validWeapons)
    {
        this.validWeapons = validWeapons;
        return this;
    }

    public SuggestionBuilder withResponseValue(String responseValue)
    {
        this.responseValue = responseValue;
        return this;
    }

    public Suggestion build()
    {
        return new Suggestion(suggestionStatus, weapon, roomNames, character, caseDetails, validCharacters, validWeapons, responseValue);
    }
}
