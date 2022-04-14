package edu.jhu.espresso.client.domain;

public class WeaponCard implements Card{

    private final String name;
    private final String type = "WEAPON";

    public WeaponCard(String name) {
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
