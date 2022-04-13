package edu.jhu.espresso.client.domain;

public class CaseDetails
{
    private Weapon weapon;
    private Room room;
    private CharacterNames characterNames;

    public Weapon getWeapon()
    {
        return weapon;
    }

    public Room getRoom()
    {
        return room;
    }

    public CharacterNames getCharacterNames()
    {
        return characterNames;
    }

    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }

    public void setRoom(Room room)
    {
        this.room = room;
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
                ", room=" + room +
                ", characterNames=" + characterNames +
                '}';
    }
}
