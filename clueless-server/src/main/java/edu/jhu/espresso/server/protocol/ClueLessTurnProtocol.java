package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.domain.*;
import edu.jhu.espresso.server.domain.builder.AccusationBuilder;
import edu.jhu.espresso.server.domain.builder.SuggestionBuilder;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ClueLessTurnProtocol
{
    private final Game game;
    private final List<Player> waitingPlayers;
    private final Player activePlayer;

    public ClueLessTurnProtocol(
            List<Player> waitingPlayers,
            Player activePlayer,
            Game game
    ) {
        this.waitingPlayers = waitingPlayers;
        this.activePlayer = activePlayer;
        this.game = game;
    }

    public boolean executeTurn()
    {
        notifyPlayersOfStatus();

        MoveChoice moveChoice = activePlayer.writeInstanceAndExpectType(
                determineValidMoveOptions(game.getGameBoard()),
                MoveChoice.class
        );

        game.applyMoveChoice(moveChoice, activePlayer.getCharacter().getName());

        Suggestion suggestion = activePlayer.writeInstanceAndExpectType(
                buildOfferSuggestionMessage(),
                Suggestion.class
        );

        if(suggestion.getSuggestionStatus() == SuggestionStatus.MAKING_SUGGESTION)
        {
            launchSuggestionTestimony(suggestion);
        }

        Accusation accusation = activePlayer.writeInstanceAndExpectType(
                buildOfferAccusationMessage(),
                Accusation.class
        );

        if(accusation.getAccusationStatus() == AccusationStatus.MAKING_ACCUSATION)
        {
            new ServerAccusationProtocol(activePlayer, waitingPlayers, accusation, game).execute();
        }

        System.out.println("Moving to the next player");
        System.out.println();

        return !game.isOver();
    }

    private void notifyPlayersOfStatus()
    {
        Map<CharacterNames, LocationNames> locationNamesMap = game.getLocations();
        TurnStart activePlayerTurnStart = new TurnStart(ClueLessProtocolType.ACTIVE_PLAYER, locationNamesMap, "");
        TurnStart waitingPlayerTurnStart = new TurnStart(ClueLessProtocolType.WAITING_PLAYER, locationNamesMap, "");

        CompletableFuture<TurnStart> activeResponseFuture = activePlayer.asyncWriteInstanceAndExpectType(
                activePlayerTurnStart,
                TurnStart.class
        );

        List<CompletableFuture<TurnStart>> waitingResponseFutures = waitingPlayers.stream()
                .map(handler -> handler.asyncWriteInstanceAndExpectType(waitingPlayerTurnStart, TurnStart.class))
                .collect(Collectors.toList());

        activeResponseFuture.join();
        waitingResponseFutures.forEach(CompletableFuture::join);
    }

    private MoveOptions determineValidMoveOptions(GameBoard gameBoard)
    {
        MoveOptions moveOptions = new MoveOptions();
        ArrayList<Location> legalMoveLocations = gameBoard.getLegalMoves(activePlayer.getCharacter().getName());
        List<LocationNames> locationNames = legalMoveLocations.stream()
                .map(Location::getLocationName)
                .map(LocationNames::fromStringName)
                .collect(Collectors.toList());

        moveOptions.setValidMoves(locationNames);
        moveOptions.setTurnIndicator(ClueLessProtocolType.ACTIVE_PLAYER);
        return moveOptions;
    }

    private Suggestion buildOfferSuggestionMessage()
    {
        List<String> validCharacters = Arrays.stream(CharacterNames.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        List<String> validWeapons = Arrays.stream(Weapon.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        return SuggestionBuilder.aSuggestion()
                .withSuggestionStatus(SuggestionStatus.OFFER_SUGGESTION)
                .withValidCharacters(validCharacters)
                .withValidWeapons(validWeapons)
                .build();
    }

    private Accusation buildOfferAccusationMessage()
    {
        return AccusationBuilder.anAccusation()
                .withAccusationStatus(AccusationStatus.OFFER_ACCUSATION)
                .withValidRooms(Arrays.asList(RoomNames.BILLIARD_ROOM.name(), RoomNames.BALLROOM.name()))
                .withValidCharacters(Arrays.asList(CharacterNames.COLONEL_MUSTARD.name(), CharacterNames.MRS_PEACOCK.name(), CharacterNames.MR_GREEN.name()))
                .withValidWeapons(Arrays.asList(Weapon.CANDLESTICK.name(), Weapon.DAGGER.name(), Weapon.REVOLVER.name()))
                .build();
    }

    private void launchSuggestionTestimony(Suggestion suggestion)
    {
        String character = Optional.ofNullable(suggestion.getCharacter()).map(Enum::name).orElse("");
        String room = Optional.ofNullable(suggestion.getRoomNames()).map(Enum::name).orElse("");
        String weapon = Optional.ofNullable(suggestion.getWeapon()).map(Enum::name).orElse("");

        if(suggestion.getCharacter() != null && suggestion.getRoomNames() != null)
        {
            game.getGameBoard().moveCharacter(suggestion.getCharacter(), Location.fromRoomNames(suggestion.getRoomNames()));
        }

        ClueLessServerGameProtocol.broadcast(
                game,
                activePlayer.getCharacter().getName() + " suggested " + character + " in the " + room +
                        " with the " + weapon,
                waitingPlayers
        );

        new SuggestionTestimonyProtocol(waitingPlayers, activePlayer, suggestion, game).execute();
    }
}
