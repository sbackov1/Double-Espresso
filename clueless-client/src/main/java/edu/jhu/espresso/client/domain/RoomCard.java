package edu.jhu.espresso.client.domain;

public class RoomCard implements Card{

    private final String name;
    private final String type = "ROOM";

    public RoomCard(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
}
