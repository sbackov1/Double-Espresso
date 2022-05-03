package edu.jhu.espresso.client.domain.GameEvents;

import edu.jhu.espresso.client.domain.GamePieces.Character;
import edu.jhu.espresso.client.domain.GamePieces.RoomNames;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class GameState
{
    private List<RoomNames> roomNames;
    private List<String> hallways;
    private Map<Character, RoomNames> characterPositions = new EnumMap<>(Character.class);

    public List<RoomNames> getRooms()
    {
        return roomNames;
    }

    public void setRooms(List<RoomNames> roomNames)
    {
        this.roomNames = roomNames;
    }

    public List<String> getHallways()
    {
        return hallways;
    }

    public void setHallways(List<String> hallways)
    {
        this.hallways = hallways;
    }

    public Map<Character, RoomNames> getCharacterPositions()
    {
        return characterPositions;
    }

    public void setCharacterPositions(Map<Character, RoomNames> characterPositions)
    {
        this.characterPositions = characterPositions;
    }

    @Override
    public String toString()
    {
        return "GameState{" +
                "rooms=" + roomNames +
                ", hallways=" + hallways +
                ", characterPositions=" + characterPositions +
                '}';
    }
}
