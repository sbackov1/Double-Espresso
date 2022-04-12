package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.domain.CharacterNames;
import edu.jhu.espresso.server.domain.Room;
import edu.jhu.espresso.server.domain.CaseDetails;
import edu.jhu.espresso.server.domain.Weapon;

import java.util.Objects;

public final class CaseDetailsBuilder
{
    private Weapon weapon;
    private Room room;
    private CharacterNames characterNames;

    private CaseDetailsBuilder()
    {
    }

    public static CaseDetailsBuilder aSuggestion()
    {
        return new CaseDetailsBuilder();
    }

    public CaseDetailsBuilder withWeapon(Weapon weapon)
    {
        this.weapon = weapon;
        return this;
    }

    public CaseDetailsBuilder withRoom(Room room)
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
