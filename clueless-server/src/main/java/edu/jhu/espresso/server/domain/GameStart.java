package edu.jhu.espresso.server.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.jhu.espresso.server.domain.builder.GameStartBuilder;

import java.util.List;

@JsonDeserialize(builder = GameStartBuilder.class)
public class GameStart
{
    private final List<RoomNames> roomNamesList;
    private final List<Weapon> weapons;
    private final List<CharacterNames> characterNamesList;
    private final CharacterNames characterNames;

    public GameStart(List<RoomNames> roomNamesList, List<Weapon> weapons, List<CharacterNames> characterNamesList, CharacterNames characterNames)
    {
        this.roomNamesList = roomNamesList;
        this.weapons = weapons;
        this.characterNamesList = characterNamesList;
        this.characterNames = characterNames;
    }

    public CharacterNames getCharacterNames()
    {
        return characterNames;
    }

    public List<RoomNames> getRoomNamesList()
    {
        return roomNamesList;
    }

    public List<Weapon> getWeapons()
    {
        return weapons;
    }

    public List<CharacterNames> getCharacterNamesList()
    {
        return characterNamesList;
    }
}
