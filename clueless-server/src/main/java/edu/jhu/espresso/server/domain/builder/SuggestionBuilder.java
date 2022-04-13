package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.domain.*;
import edu.jhu.espresso.server.domain.Character;

import java.util.List;

public final class SuggestionBuilder
{
    private SuggestionStatus suggestionStatus;
    private Weapon weapon;
    private RoomNames roomNames;
    private Character character;
    private GameBoard gameBoard;
    private CaseDetails caseDetails;
    private List<String> validCharacters;
    private List<String> validWeapons;

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

    public SuggestionBuilder withCharacter(Character character)
    {
        this.character = character;
        return this;
    }

    public SuggestionBuilder withGameBoard(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
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

    public Suggestion build()
    {
        return new Suggestion(suggestionStatus, weapon, roomNames, character, gameBoard, caseDetails, validCharacters, validWeapons);
    }
}
