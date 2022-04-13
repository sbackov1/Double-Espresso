package edu.jhu.espresso.server.domain;

public class RoomCard implements Card{

    private final String name;
    private final String cardType;

    public RoomCard(RoomNames room) {
        this.cardType = "RoomCard";
        this.name = room.name();
    }

    @Override
    public String getName() {
        return name;
    }
}
