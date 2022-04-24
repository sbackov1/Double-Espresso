package edu.jhu.espresso.server.domain.gamepieces;

public class Character {

    private final CharacterNames name;

    public Character(CharacterNames name){
        this.name = name;
    }

    public CharacterNames getName()
    {
        return name;
    }
}
