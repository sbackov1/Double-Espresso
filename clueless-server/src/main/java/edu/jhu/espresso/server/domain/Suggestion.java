package edu.jhu.espresso.server.domain;

public final class Suggestion
{
    private SuggestionStatus suggestionStatus;
    private Weapon weapon;
    private Room room;
    private CharacterNames characterNames;

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
        return "Suggestion{" +
                "suggestionStatus=" + suggestionStatus +
                ", weapon=" + weapon +
                ", room=" + room +
                ", character=" + characterNames +
                '}';
    }
}
