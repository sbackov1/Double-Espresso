package edu.jhu.espresso.server.domain;

public class WeaponCard implements Card{

    private final String name;
    private final String cardType;

    public WeaponCard(Weapon weapon) {
        this.cardType = "WeaponCard";
        this.name = weapon.name();
    }

    @Override
    public String getName() {
        return name;
    }
}
