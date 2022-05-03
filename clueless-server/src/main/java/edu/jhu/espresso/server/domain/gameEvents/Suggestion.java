package edu.jhu.espresso.server.domain.gameEvents;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.jhu.espresso.server.domain.builder.SuggestionBuilder;
import edu.jhu.espresso.server.domain.gamepieces.CaseDetails;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;
import edu.jhu.espresso.server.domain.gamepieces.RoomNames;
import edu.jhu.espresso.server.domain.gamepieces.Weapon;

import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = SuggestionBuilder.class)
public final class Suggestion
{
    private final SuggestionStatus suggestionStatus;
    private final Weapon weapon;
    private final RoomNames roomNames;
    private final CharacterNames character;
    private final CaseDetails caseDetails;
    private final List<String> validCharacters;
    private final List<String> validWeapons;
    private final String responseValue;

    public Suggestion(
            SuggestionStatus suggestionStatus,
            Weapon weapon,
            RoomNames roomNames,
            CharacterNames character,
            CaseDetails caseDetails,
            List<String> validCharacters,
            List<String> validWeapons,
            String responseValue
    ) {
        this.suggestionStatus = suggestionStatus;
        this.weapon = weapon;
        this.roomNames = roomNames;
        this.character = character;
        this.caseDetails = caseDetails;
        this.validCharacters = validCharacters;
        this.validWeapons = validWeapons;
        this.responseValue = responseValue;
    }

    public SuggestionStatus getSuggestionStatus()
    {
        return suggestionStatus;
    }

    public CaseDetails getCaseDetails()
    {
        return caseDetails;
    }

    public List<String> getValidCharacters()
    {
        return validCharacters;
    }

    public List<String> getValidWeapons()
    {
        return validWeapons;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public RoomNames getRoomNames()
    {
        return roomNames;
    }

    public CharacterNames getCharacter()
    {
        return character;
    }

    public String getResponseValue()
    {
        return responseValue;
    }
}
