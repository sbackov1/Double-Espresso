package edu.jhu.espresso.server.domain.gamepieces;

import java.util.Optional;

public class RoomCard implements Card {

    private final String name;
    private final String cardType;
    private final RoomNames roomNames;

    public RoomCard(RoomNames room) {
        this.cardType = "RoomCard";
        this.name = room.name();
        this.roomNames = room;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Optional<RoomNames> getRoomName()
    {
        return Optional.of(roomNames);
    }

    @Override
    public Optional<Weapon> getWeapon()
    {
        return Optional.empty();
    }

    @Override
    public Optional<CharacterNames> getCharacterName()
    {
        return Optional.empty();
    }

    @Override
    public String toString()
    {
        return "RoomCard{" +
                "name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", roomNames=" + roomNames +
                '}';
    }
}
