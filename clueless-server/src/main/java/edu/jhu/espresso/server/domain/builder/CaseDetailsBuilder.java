package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.domain.gamepieces.CaseDetails;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;
import edu.jhu.espresso.server.domain.gamepieces.RoomNames;
import edu.jhu.espresso.server.domain.gamepieces.Weapon;

import java.util.Objects;

public final class CaseDetailsBuilder
{
    private Weapon weapon;
    private RoomNames room;
    private CharacterNames characterNames;

    private CaseDetailsBuilder()
    {
    }

    public static CaseDetailsBuilder aCaseDetails()
    {
        return new CaseDetailsBuilder();
    }

    public CaseDetailsBuilder withWeapon(Weapon weapon)
    {
        this.weapon = weapon;
        return this;
    }

    public CaseDetailsBuilder withRoom(RoomNames room)
    {
        this.room = room;
        return this;
    }

    public CaseDetailsBuilder withCharacterNames(CharacterNames characterNames)
    {
        this.characterNames = characterNames;
        return this;
    }

    public CaseDetails build()
    {
        return new CaseDetails(
                Objects.requireNonNull(weapon),
                Objects.requireNonNull(room),
                Objects.requireNonNull(characterNames)
        );
    }
}
