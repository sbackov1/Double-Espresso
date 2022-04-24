package edu.jhu.espresso.server.domain.gamepieces;

import java.util.Optional;

public interface Card {

    final String name = null;
    final String cardType = null;

    public String getName();

    Optional<RoomNames> getRoomName();
    Optional<Weapon> getWeapon();
    Optional<CharacterNames> getCharacterName();
}
