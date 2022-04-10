package edu.jhu.espresso.server.domain;

public class CharacterCard implements Card{

    private final String name;

    public CharacterCard(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
