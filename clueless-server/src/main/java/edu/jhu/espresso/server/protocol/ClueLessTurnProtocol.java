package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.ClueLessClientHandler;
import edu.jhu.espresso.server.domain.*;
import edu.jhu.espresso.server.domain.builder.AccusationBuilder;
import edu.jhu.espresso.server.domain.builder.SuggestionBuilder;
import edu.jhu.espresso.server.domain.builder.TurnStartBuilder;

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
            new SuggestionTestimonyProtocol(waitingPlayers, suggestion).execute();
        }

        Accusation accusation = activePlayerHandler.writeInstanceAndExpectType(
                buildOfferAccusationMessage(),
                Accusation.class
        );

        if(accusation.getAccusationStatus() == AccusationStatus.MAKING_ACCUSATION)
        {
            new ServerAccusationProtocol(activePlayerHandler, waitingPlayers, accusation).execute();
        }

        return game.isOver();
    }

    private void notifyPlayersOfStatus()
    {
        TurnStartBuilder builder = TurnStartBuilder.aTurnStart()
                .withGameBoard(game.getGameBoard());

        TurnStart activePlayerTurnStart = builder.withClueLessProtocolType(ClueLessProtocolType.ACTIVE_PLAYER).build();
        TurnStart waitingPlayerTurnStart = builder.withClueLessProtocolType(ClueLessProtocolType.WAITING_PLAYER).build();

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
        moveOptions.setValidMoves(new ArrayList<>(Arrays.asList("hallway", RoomNames.BALLROOM.name(), RoomNames.STUDY.name())));
        moveOptions.setTurnIndicator(ClueLessProtocolType.ACTIVE_PLAYER);
        return moveOptions;
    }

    private Suggestion buildOfferSuggestionMessage()
    {
        return SuggestionBuilder.aSuggestion()
                .withSuggestionStatus(SuggestionStatus.OFFER_SUGGESTION)
                .withGameBoard(game.getGameBoard())
                .build();
    }

    private Accusation buildOfferAccusationMessage()
    {
        return AccusationBuilder.anAccusation()
                .withAccusationStatus(AccusationStatus.OFFER_ACCUSATION)
                .withGameBoard(game.getGameBoard())
                .build();
    }
}
