package edu.jhu.espresso.server.domain;

public class RoomCard implements Card{

    private final String name;

    public RoomCard(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
