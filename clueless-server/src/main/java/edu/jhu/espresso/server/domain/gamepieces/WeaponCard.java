package edu.jhu.espresso.server.domain.gamepieces;

import java.util.Optional;

public class WeaponCard implements Card {

    private final String name;
    private final String cardType;
    private final Weapon weapon;

    public WeaponCard(Weapon weapon) {
        this.cardType = "WeaponCard";
        this.name = weapon.name();
        this.weapon = weapon;
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
        return Optional.of(weapon);
    }

    @Override
    public Optional<CharacterNames> getCharacterName()
    {
        return Optional.empty();
    }

    @Override
    public String toString()
    {
        return "WeaponCard{" +
                "name='" + name + '\'' +
                ", cardType='" + cardType + '\'' +
                ", weapon=" + weapon +
                '}';
    }
}
