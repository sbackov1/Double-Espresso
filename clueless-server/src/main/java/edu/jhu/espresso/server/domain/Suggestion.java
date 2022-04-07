package edu.jhu.espresso.server.domain;

public final class Suggestion
{
    private SuggestionStatus suggestionStatus;
    private Weapon weapon;
    private Room room;
    private Character character;

    public SuggestionStatus getSuggestionAction()
    {
        return suggestionStatus;
    }

    public void setSuggestionAction(SuggestionStatus suggestionStatus)
    {
        this.suggestionStatus = suggestionStatus;
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
        return "Suggestion{" +
                "suggestionStatus=" + suggestionStatus +
                ", weapon=" + weapon +
                ", room=" + room +
                ", character=" + character +
                '}';
    }
}
