package edu.jhu.espresso.server.domain.gameEvents;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.jhu.espresso.server.domain.gamepieces.RoomNames;
import edu.jhu.espresso.server.domain.gamepieces.Weapon;
import edu.jhu.espresso.server.domain.builder.GameStartBuilder;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;

import java.util.List;

@JsonDeserialize(builder = GameStartBuilder.class)
public class GameStart
{
    private final List<RoomNames> roomNamesList;
    private final List<Weapon> weapons;
    private final List<CharacterNames> characterNamesList;
    private final List<String> extraCardsNames;
    private final CharacterNames characterNames;
    private final int numberOfPlayers;

    public GameStart(List<RoomNames> roomNamesList, List<Weapon> weapons, List<CharacterNames> characterNamesList, List<String> extraCardsNames, CharacterNames characterNames, int numberOfPlayers)
    {
        this.roomNamesList = roomNamesList;
        this.weapons = weapons;
        this.characterNamesList = characterNamesList;
        this.extraCardsNames = extraCardsNames;
        this.characterNames = characterNames;
        this.numberOfPlayers = numberOfPlayers;
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

    public int getNumberOfPlayers()
    {
        return numberOfPlayers;
    }

    public List<String> getExtraCardsNames()
    {
        return extraCardsNames;
    }
}
