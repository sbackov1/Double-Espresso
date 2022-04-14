package edu.jhu.espresso.client.domain;

public class CharacterCard implements Card{

    private final String name;
    private final String type = "CHARACTER";

    public CharacterCard(String name) {
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
