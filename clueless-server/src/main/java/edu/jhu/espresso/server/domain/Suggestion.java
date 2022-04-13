package edu.jhu.espresso.server.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.jhu.espresso.server.domain.builder.SuggestionBuilder;

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

    public Suggestion(
            SuggestionStatus suggestionStatus,
            Weapon weapon,
            RoomNames roomNames,
            CharacterNames character,
            GameBoard gameBoard,
            CaseDetails caseDetails,
            List<String> validCharacters,
            List<String> validWeapons
    ) {
        this.suggestionStatus = Objects.requireNonNull(suggestionStatus);
        this.weapon = weapon;
        this.roomNames = roomNames;
        this.character = character;
        this.caseDetails = caseDetails;
        this.validCharacters = validCharacters;
        this.validWeapons = validWeapons;
    }

    public SuggestionStatus getSuggestionStatus()
    {
        return suggestionStatus;
    }

    public CaseDetails getCaseDetails()
    {
        return caseDetails;
    }

//    public GameBoard getGameBoard()
//    {
//        return gameBoard;
//    }


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
}
