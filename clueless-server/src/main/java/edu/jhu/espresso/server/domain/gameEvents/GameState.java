package edu.jhu.espresso.server.domain.gameEvents;

import edu.jhu.espresso.server.domain.gamepieces.RoomNames;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;

import java.util.*;

public final class GameState
{
    private List<RoomNames> rooms = Arrays.asList(RoomNames.values());
    private List<String> hallways = Collections.emptyList();
    private Map<CharacterNames, RoomNames> characterPositions = new EnumMap<CharacterNames, RoomNames>(CharacterNames.class);

    public List<RoomNames> getRooms()
    {
        return rooms;
    }

    public void setRooms(List<RoomNames> rooms)
    {
        this.rooms = rooms;
    }

    public List<String> getHallways()
    {
        return hallways;
    }

    public void setHallways(List<String> hallways)
    {
        this.hallways = hallways;
    }

    public Map<CharacterNames, RoomNames> getCharacterPositions()
    {
        return characterPositions;
    }

    public void setCharacterPositions(Map<CharacterNames, RoomNames> characterPositions)
    {
        this.characterPositions = characterPositions;
    }

    @Override
    public String toString()
    {
        return "GameState{" +
                "rooms=" + rooms +
                ", hallways=" + hallways +
                ", characterPositions=" + characterPositions +
                '}';
    }
}
