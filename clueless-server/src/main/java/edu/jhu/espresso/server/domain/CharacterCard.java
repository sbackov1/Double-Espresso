package edu.jhu.espresso.server.domain;

public class CharacterCard implements Card{

    private final String name;
    private final String cardType;

    public CharacterCard(CharacterNames ch) {
        this.cardType = "CharacterCard";
        this.name = ch.name();
    }

    @Override
    public String getName() {
        return name;
    }
}
