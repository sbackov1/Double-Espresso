package edu.jhu.espresso.server.domain.gamepieces;

import java.util.Optional;

public class CharacterCard implements Card {

    private final String name;
    private final String cardType;
    private final CharacterNames characterNames;

    public CharacterCard(CharacterNames ch) {
        this.cardType = "CharacterCard";
        this.name = ch.name();
        this.characterNames = ch;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Optional<RoomNames> getRoomName()
    {
        return Optional.empty();
    }

    @Override
    public Optional<Weapon> getWeapon()
    {
        return Optional.empty();
    }

    @Override
    public Optional<CharacterNames> getCharacterName()
    {
        return Optional.of(characterNames);
    }

    @Override
    public String toString()
    {
        return "CharacterCard{" +
                "name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", characterNames=" + characterNames +
                '}';
    }
}
