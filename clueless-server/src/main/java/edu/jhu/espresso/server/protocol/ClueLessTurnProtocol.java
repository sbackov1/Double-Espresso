package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.ClueLessClientHandler;
import edu.jhu.espresso.server.domain.*;
import edu.jhu.espresso.server.domain.builder.AccusationBuilder;
import edu.jhu.espresso.server.domain.builder.SuggestionBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ClueLessTurnProtocol
{
    private final Game game;
    private final List<ClueLessClientHandler> waitingPlayers;
    private final ClueLessClientHandler activePlayerHandler;

    public ClueLessTurnProtocol(
            List<ClueLessClientHandler> waitingPlayers,
            ClueLessClientHandler activePlayerHandler,
            Game game
    ) {
        this.waitingPlayers = waitingPlayers;
        this.activePlayerHandler = activePlayerHandler;
        this.game = game;
    }

    public boolean executeTurn()
    {
        notifyPlayersOfStatus();

        activePlayerHandler.write(game.getLocations());
        waitingPlayers.forEach(waiter -> waiter.write(game.getLocations()));

        MoveChoice moveChoice = activePlayerHandler.writeInstanceAndExpectType(
                determineValidMoveOptions(game.getGameBoard()),
                MoveChoice.class
        );

        game.applyMoveChoice(moveChoice);

        Suggestion suggestion = activePlayerHandler.writeInstanceAndExpectType(
                buildOfferSuggestionMessage(),
                Suggestion.class
        );

        if(suggestion.getSuggestionStatus() == SuggestionStatus.MAKING_SUGGESTION)
        {
            new SuggestionTestimonyProtocol(waitingPlayers, suggestion, game).execute();
        }

        Accusation accusation = activePlayerHandler.writeInstanceAndExpectType(
                buildOfferAccusationMessage(),
                Accusation.class
        );

        if(accusation.getAccusationStatus() == AccusationStatus.MAKING_ACCUSATION)
        {
            new ServerAccusationProtocol(activePlayerHandler, waitingPlayers, accusation).execute();
        }

        System.out.println("Moving to the next player");
        System.out.println();

        return game.isOver();
    }

    private void notifyPlayersOfStatus()
    {
        TurnStart activePlayerTurnStart = new TurnStart(ClueLessProtocolType.ACTIVE_PLAYER);
        TurnStart waitingPlayerTurnStart = new TurnStart(ClueLessProtocolType.WAITING_PLAYER);

        CompletableFuture<TurnStart> activeResponseFuture = activePlayerHandler.asyncWriteInstanceAndExpectType(
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
        moveOptions.setValidMoves(new ArrayList<>(Arrays.asList(LocationNames.HALLWAY1, LocationNames.BALLROOM, LocationNames.STUDY)));
        moveOptions.setTurnIndicator(ClueLessProtocolType.ACTIVE_PLAYER);
        return moveOptions;
    }

    private Suggestion buildOfferSuggestionMessage()
    {
        return SuggestionBuilder.aSuggestion()
                .withSuggestionStatus(SuggestionStatus.OFFER_SUGGESTION)
                .withGameBoard(game.getGameBoard())
                .withValidCharacters(Arrays.asList(CharacterNames.COLONEL_MUSTARD.name(), CharacterNames.MRS_PEACOCK.name(), CharacterNames.MR_GREEN.name()))
                .withValidWeapons(Arrays.asList(Weapon.CANDLESTICK.name(), Weapon.DAGGER.name(), Weapon.REVOLVER.name()))
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
}
