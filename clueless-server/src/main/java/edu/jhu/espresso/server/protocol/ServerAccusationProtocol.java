package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.domain.gameEvents.Accusation;
import edu.jhu.espresso.server.domain.gamepieces.Card;
import edu.jhu.espresso.server.domain.Game;
import edu.jhu.espresso.server.domain.gamepieces.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServerAccusationProtocol
{
    private final Player activePlayerHandler;
    private final List<Player> waitingPlayerHandlers;
    private final Accusation accusation;
    private final Game game;

    public ServerAccusationProtocol(
            Player activePlayerHandler,
            List<Player> waitingPlayerHandlers,
            Accusation accusation,
            Game game
    ) {
        this.activePlayerHandler = activePlayerHandler;
        this.waitingPlayerHandlers = waitingPlayerHandlers;
        this.accusation = accusation;
        this.game = game;
    }

    public void execute()
    {
        ArrayList<Card> caseCards = game.getCardPlayer().getCaseFile();
        List<String> names = caseCards.stream()
                .map(Card::getName)
                .collect(Collectors.toList());

        if(names.contains(accusation.getRoomNames().name()) &&
                names.contains(accusation.getCharacter().name()) &&
                names.contains(accusation.getWeapon().name()))
        {
            ClueLessServerGameProtocol.broadcast(
                                game,
                    activePlayerHandler.getCharacter().getName() + " wins!",
                    waitingPlayerHandlers
            );
            System.exit(0);
        }
        else
        {
            ClueLessServerGameProtocol.broadcast(
                    game,
                    activePlayerHandler.getCharacter().getName() + " has made an incorrect accusation!",
                    waitingPlayerHandlers
            );

                    //Change active status to false.
                    activePlayerHandler.setActiveStatus(false);


        }
    }
}
