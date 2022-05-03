package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.domain.gameEvents.Accusation;
import edu.jhu.espresso.server.domain.gameEvents.AccusationStatus;
import edu.jhu.espresso.server.domain.gamepieces.CaseDetails;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;
import edu.jhu.espresso.server.domain.gamepieces.RoomNames;
import edu.jhu.espresso.server.domain.gamepieces.Weapon;

import java.util.List;

public final class AccusationBuilder
{
    private AccusationStatus accusationStatus;
    private Weapon weapon;
    private RoomNames roomNames;
    private CharacterNames character;
    private List<String> validCharacters;
    private List<String> validRooms;
    private List<String> validWeapons;
    private CaseDetails caseDetails;

    private AccusationBuilder()
    {
    }

    public static AccusationBuilder anAccusation()
    {
        return new AccusationBuilder();
    }

    public AccusationBuilder withAccusationStatus(AccusationStatus accusationStatus)
    {
        this.accusationStatus = accusationStatus;
        return this;
    }

    public AccusationBuilder withWeapon(Weapon weapon)
    {
        this.weapon = weapon;
        return this;
    }

    public AccusationBuilder withRoomNames(RoomNames roomNames)
    {
        this.roomNames = roomNames;
        return this;
    }

    public AccusationBuilder withCharacter(CharacterNames character)
    {
        this.character = character;
        return this;
    }

    public AccusationBuilder withValidCharacters(List<String> validCharacters)
    {
        this.validCharacters = validCharacters;
        return this;
    }

    public AccusationBuilder withValidRooms(List<String> validRooms)
    {
        this.validRooms = validRooms;
        return this;
    }

    public AccusationBuilder withValidWeapons(List<String> validWeapons)
    {
        this.validWeapons = validWeapons;
        return this;
    }

    public AccusationBuilder withCaseDetails(CaseDetails caseDetails)
    {
        this.caseDetails = caseDetails;
        return this;
    }

    public Accusation build()
    {
        return new Accusation(accusationStatus, weapon, roomNames, character, validCharacters, validRooms, validWeapons, caseDetails);
    }
}
