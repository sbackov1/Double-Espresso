package edu.jhu.espresso.server.domain.gamepieces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.jhu.espresso.server.domain.builder.CaseDetailsBuilder;

@JsonDeserialize(builder = CaseDetailsBuilder.class)
public class CaseDetails
{
    private final Weapon weapon;
    private final RoomNames room;
    private final CharacterNames characterNames;

    public CaseDetails(Weapon weapon, RoomNames room, CharacterNames characterNames)
    {
        this.weapon = weapon;
        this.room = room;
        this.characterNames = characterNames;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public RoomNames getRoom()
    {
        return room;
    }

    public CharacterNames getCharacterNames()
    {
        return characterNames;
    }

    @Override
    public String toString()
    {
        return "Suggestion{" +
                "weapon=" + weapon +
                ", room=" + room +
                ", characterNames=" + characterNames +
                '}';
    }
}
