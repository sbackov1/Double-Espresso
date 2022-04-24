package edu.jhu.espresso.server.domain.gameEvents;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.jhu.espresso.server.domain.gamepieces.RoomNames;
import edu.jhu.espresso.server.domain.gamepieces.Weapon;
import edu.jhu.espresso.server.domain.builder.AccusationBuilder;
import edu.jhu.espresso.server.domain.gamepieces.CaseDetails;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;

import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = AccusationBuilder.class)
public final class Accusation
{
    private final AccusationStatus accusationStatus;
    private final Weapon weapon;
    private final RoomNames roomNames;
    private final CharacterNames character;
    private final List<String> validCharacters;
    private final List<String> validRooms;
    private final List<String> validWeapons;

    private final CaseDetails caseDetails;

    public Accusation(
            AccusationStatus accusationStatus,
            Weapon weapon,
            RoomNames roomNames,
            CharacterNames character,
            List<String> validCharacters,
            List<String> validRooms,
            List<String> validWeapons,
            CaseDetails caseDetails
    )
    {
        this.accusationStatus = Objects.requireNonNull(accusationStatus);
        this.weapon = weapon;
        this.roomNames = roomNames;
        this.character = character;
        this.validCharacters = validCharacters;
        this.validRooms = validRooms;
        this.validWeapons = validWeapons;
        this.caseDetails = caseDetails;
    }

    public AccusationStatus getAccusationStatus()
    {
        return accusationStatus;
    }

    public CaseDetails getCaseDetails()
    {
        return caseDetails;
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

    public List<String> getValidCharacters()
    {
        return validCharacters;
    }

    public List<String> getValidRooms()
    {
        return validRooms;
    }

    public List<String> getValidWeapons()
    {
        return validWeapons;
    }
}
