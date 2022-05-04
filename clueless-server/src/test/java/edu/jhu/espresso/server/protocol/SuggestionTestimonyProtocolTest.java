package edu.jhu.espresso.server.protocol;


import edu.jhu.espresso.server.domain.builder.SuggestionBuilder;
import edu.jhu.espresso.server.domain.gameEvents.Suggestion;
import edu.jhu.espresso.server.domain.gameEvents.SuggestionStatus;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;
import edu.jhu.espresso.server.domain.gamepieces.Player;
import edu.jhu.espresso.server.domain.gamepieces.Character;
import edu.jhu.espresso.server.domain.gamepieces.RoomNames;
import edu.jhu.espresso.server.domain.gamepieces.Weapon;
import edu.jhu.espresso.server.domain.Game;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SuggestionTestimonyProtocolTest {

    Player pl1 = Mockito.mock(Player.class);
    Player pl2 = Mockito.mock(Player.class);
    Player pl3 = Mockito.mock(Player.class);

    Suggestion sg = SuggestionBuilder.aSuggestion()
            .withWeapon(Weapon.REVOLVER)
            .withCharacter(CharacterNames.COLONEL_MUSTARD)
            .withSuggestionStatus(SuggestionStatus.MAKING_SUGGESTION)
            .withRoomNames(RoomNames.BILLIARD_ROOM)
            .build();

    Suggestion sgResp = SuggestionBuilder.aSuggestion()
            .withWeapon(Weapon.REVOLVER)
            .withSuggestionStatus(SuggestionStatus.PROVING_SUGGESTION_FALSE)
            .build();

    Game g1 = Mockito.mock(Game.class);

    ArrayList<Player> waitingPlayers = new ArrayList<>(Arrays.asList(pl1, pl2));

//    @Test
    void testSuggestionTestimonyProtocol(){

        when(pl3.getNextPlayer()).thenReturn(waitingPlayers.get(0));

        SuggestionTestimonyProtocol sgtp = new SuggestionTestimonyProtocol(waitingPlayers, pl3, sg, g1);

        when(pl1.getActiveStatus()).thenReturn(true);

        when(pl1.writeInstanceAndExpectType(any(), eq(Suggestion.class))).thenReturn(sgResp);

        when(pl1.getCharacter()).thenReturn(new Character(CharacterNames.COLONEL_MUSTARD));

        sgtp.execute();

        verify(pl1).getActiveStatus();




    }

}