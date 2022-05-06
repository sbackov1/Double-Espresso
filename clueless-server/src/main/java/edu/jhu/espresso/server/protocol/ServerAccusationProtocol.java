package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.domain.gameEvents.Accusation;
import edu.jhu.espresso.server.domain.gamepieces.Card;
import edu.jhu.espresso.server.domain.Game;
import edu.jhu.espresso.server.domain.gamepieces.Player;

import java.util.*;
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

        String roomName = caseCards.stream()
                .map(Card::getRoomName)
                .filter(Optional::isPresent)
                .findFirst()
                .map(optional -> optional.orElseThrow(() -> new IllegalStateException("No room name found in case file")))
                .orElseThrow(NoSuchElementException::new).name();

        String characterName = caseCards.stream()
                .map(Card::getCharacterName)
                .filter(Optional::isPresent)
                .findFirst()
                .map(optional -> optional.orElseThrow(() -> new IllegalStateException("No room name found in case file")))
                .orElseThrow(NoSuchElementException::new).name();

        String weaponName = caseCards.stream()
                .map(Card::getWeapon)
                .filter(Optional::isPresent)
                .findFirst()
                .map(optional -> optional.orElseThrow(() -> new IllegalStateException("No room name found in case file")))
                .orElseThrow(NoSuchElementException::new).name();

        String caseDetails = characterName + " in the " + roomName + " with the " + weaponName;

        List<Player> allPlayers = new ArrayList<>(waitingPlayerHandlers);
        allPlayers.add(activePlayerHandler);

        if(names.contains(accusation.getRoomNames().name()) &&
                names.contains(accusation.getCharacter().name()) &&
                names.contains(accusation.getWeapon().name()))
        {
            ClueLessServerGameProtocol.broadcast(
                                game,
                    activePlayerHandler.getCharacter().getName() + " wins! The case details were " + caseDetails,
                    allPlayers
            );
            System.exit(0);
        }
        else
        {
            ClueLessServerGameProtocol.broadcast(
                    game,
                    activePlayerHandler.getCharacter().getName() + " has made an incorrect accusation and is no longer active",
                    waitingPlayerHandlers
            );

            ClueLessServerGameProtocol.broadcast(
                    game,
                    "That accusation is incorrect. The case details are " + caseDetails,
                    Collections.singletonList(activePlayerHandler)
            );

            //Change active status to false.
            activePlayerHandler.setActiveStatus(false);
        }

        List<Player> remainingActivePlayers = allPlayers.stream()
                .filter(Player::getActiveStatus)
                .collect(Collectors.toList());

        if(remainingActivePlayers.size() == 1)
        {
            ClueLessServerGameProtocol.broadcast(
                    game,
                    remainingActivePlayers.get(0).getCharacter().getName() + " wins! The case details were " + caseDetails,
                    allPlayers
            );
            System.exit(0);
        }
    }
}
