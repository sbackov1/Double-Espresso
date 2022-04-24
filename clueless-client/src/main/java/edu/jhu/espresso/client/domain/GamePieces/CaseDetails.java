package edu.jhu.espresso.client.domain.GamePieces;

public class CaseDetails
{
    private Weapon weapon;
    private RoomNames roomNames;
    private CharacterNames characterNames;

    public Weapon getWeapon()
    {
        return weapon;
    }

    public RoomNames getRoom()
    {
        return roomNames;
    }

    public CharacterNames getCharacterNames()
    {
        return characterNames;
    }

    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }

    public void setRoom(RoomNames room)
    {
        this.roomNames = room;
    }

    public void setCharacterNames(CharacterNames characterNames)
    {
        this.characterNames = characterNames;
    }

    @Override
    public String toString()
    {
        return "Suggestion{" +
                "weapon=" + weapon +
                ", room=" + roomNames +
                ", characterNames=" + characterNames +
                '}';
    }
}
