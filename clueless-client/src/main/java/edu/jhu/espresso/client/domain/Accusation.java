package edu.jhu.espresso.client.domain;

public final class Accusation
{
    private AccusationStatus accusationStatus;
    private Weapon weapon;
    private Room room;
    private Character character;

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

    public Character getCharacter()
    {
        return character;
    }

    public void setCharacter(Character character)
    {
        this.character = character;
    }

    @Override
    public String toString()
    {
        return "Accusation{" +
                "accusationStatus=" + accusationStatus +
                ", weapon=" + weapon +
                ", room=" + room +
                ", character=" + character +
                '}';
    }
}
