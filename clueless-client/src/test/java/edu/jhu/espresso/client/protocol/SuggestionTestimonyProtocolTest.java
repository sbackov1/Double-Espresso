package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.domain.GameEvents.SuggestionBuilder;
import edu.jhu.espresso.client.domain.GameEvents.SuggestionStatus;
import edu.jhu.espresso.client.domain.GamePieces.CharacterNames;
import edu.jhu.espresso.client.domain.GamePieces.Player;
import edu.jhu.espresso.client.domain.GamePieces.RoomNames;
import edu.jhu.espresso.client.domain.GamePieces.Weapon;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

class SuggestionTestimonyProtocolTest {

    Player pl1 = Mockito.mock(Player.class);
    Player pl2 = Mockito.mock(Player.class);
    Player pl3 = Mockito.mock(Player.class);

    SuggestionBuilder sg = SuggestionBuilder.aSuggestion()
            .withWeapon(Weapon.REVOLVER)
            .withCharacter(CharacterNames.COLONEL_MUSTARD)
            .withSuggestionStatus(SuggestionStatus.MAKING_SUGGESTION)
            .withRoomNames(RoomNames.BILLIARD_ROOM);

    ArrayList<Player> waitingPlayers = new ArrayList<>(Arrays.asList(pl1, pl2));


}