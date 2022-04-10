package edu.jhu.espresso.server.domain;

public final class Accusation
{
    private AccusationStatus accusationStatus;
    private Weapon weapon;
    private Room room;
    private CharacterNames characterNames;

    public AccusationStatus getAccusationStatus()
    {
        return accusationStatus;
    }

    public void setAccusationStatus(AccusationStatus accusationStatus)
    {
        this.accusationStatus = accusationStatus;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public CharacterNames getCharacter()
    {
        return characterNames;
    }

    public void setCharacter(CharacterNames characterNames)
    {
        this.characterNames = characterNames;
    }

    @Override
    public String toString()
    {
        return "Accusation{" +
                "accusationStatus=" + accusationStatus +
                ", weapon=" + weapon +
                ", room=" + room +
                ", character=" + characterNames +
                '}';
    }
}
