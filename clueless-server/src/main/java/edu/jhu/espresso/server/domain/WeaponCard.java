package edu.jhu.espresso.server.domain;

public class WeaponCard implements Card{

    private final String name;

    public WeaponCard(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
