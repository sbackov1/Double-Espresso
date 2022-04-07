package edu.jhu.espresso.client.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class GameState
{
    private List<Room> rooms;
    private List<String> hallways;
    private Map<Character, Room> characterPositions = new EnumMap<>(Character.class);

    public List<Room> getRooms()
    {
        return rooms;
    }

    public void setRooms(List<Room> rooms)
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

    public Map<Character, Room> getCharacterPositions()
    {
        return characterPositions;
    }

    public void setCharacterPositions(Map<Character, Room> characterPositions)
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
