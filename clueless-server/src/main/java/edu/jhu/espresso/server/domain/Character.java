package edu.jhu.espresso.server.domain;

public class Character {

    private final String name;

    public Character(CharacterNames name){
        this.name = name.name();
    }

    public String getName() {
        return name;
    }
}
