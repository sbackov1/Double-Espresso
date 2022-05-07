package edu.jhu.espresso.client.domain.GameEvents;

import edu.jhu.espresso.client.domain.GamePieces.CharacterNames;
import edu.jhu.espresso.client.domain.GamePieces.RoomNames;
import edu.jhu.espresso.client.domain.GamePieces.Weapon;

import java.util.List;

public class GameStart
{
    private List<RoomNames> roomNamesList;
    private List<Weapon> weapons;
    private List<CharacterNames> characterNamesList;
    private List<String> extraCardsNames;
    private CharacterNames characterNames;
    private int numberOfPlayers;

    public List<RoomNames> getRoomNamesList()
    {
        return roomNamesList;
    }

    public void setRoomNamesList(List<RoomNames> roomNamesList)
    {
        this.roomNamesList = roomNamesList;
    }

    public List<Weapon> getWeapons()
    {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons)
    {
        this.weapons = weapons;
    }

    public List<CharacterNames> getCharacterNamesList()
    {
        return characterNamesList;
    }

    public void setCharacterNamesList(List<CharacterNames> characterNamesList)
    {
        this.characterNamesList = characterNamesList;
    }

    public List<String> getExtraCardsNames()
    {
        return extraCardsNames;
    }

    public void setExtraCardsNames(List<String> extraCardsNames)
    {
        this.extraCardsNames = extraCardsNames;
    }

    public CharacterNames getCharacterNames()
    {
        return characterNames;
    }

    public void setCharacterNames(CharacterNames characterNames)
    {
        this.characterNames = characterNames;
    }

    public int getNumberOfPlayers()
    {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers)
    {
        this.numberOfPlayers = numberOfPlayers;
    }
}
