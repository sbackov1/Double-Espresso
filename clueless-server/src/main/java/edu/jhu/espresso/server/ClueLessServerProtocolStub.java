package edu.jhu.espresso.server;

import edu.jhu.espresso.server.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ClueLessServerProtocolStub
{
    private static final TurnStart ACTIVE_PLAYER_TURN = TurnStart.fromIndicator(TurnIndicator.ACTIVE_PLAYER);
    private static final TurnStart WAITING_PLAYER_TURN = TurnStart.fromIndicator(TurnIndicator.WAITING_PLAYER);

    private final List<ClueLessClientHandler> clientHandlers;
    private final List<String> validMoves = new ArrayList<>(Arrays.asList("left", "right", "up", "down", "suggestion"));

    private int activePlayerIndex = 0;

    public ClueLessServerProtocolStub(List<ClueLessClientHandler> clientHandlers)
    {
        this.clientHandlers = clientHandlers;
    }

    public void runTurn()
    {
        ClueLessClientHandler activePlayerHandler = clientHandlers.get(activePlayerIndex);
        List<ClueLessClientHandler> waitingPlayers = clientHandlers.stream()
                .filter(handler -> !handler.equals(activePlayerHandler))
                .collect(Collectors.toList());

        CompletableFuture<TurnStart> activeResponseFuture = activePlayerHandler.writeAsync(ACTIVE_PLAYER_TURN, TurnStart.class);

        List<CompletableFuture<TurnStart>> waitingResponseFutures = waitingPlayers.stream()
                .map(handler -> handler.writeAsync(WAITING_PLAYER_TURN, TurnStart.class))
                .collect(Collectors.toList());

        TurnStart activeResponse = activeResponseFuture.join();
        List<TurnStart> responses = waitingResponseFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        responses.add(activeResponse);
        responses.forEach(System.out::println);

        System.out.println();

        Accusation accusationChoice = CompletableFuture.supplyAsync(
                () -> activePlayerHandler.write(
                        stubMoveOptions(),
                        MoveOptions.class
                )
        ).thenApplyAsync(
                moveOptions -> {
                    System.out.println(moveOptions);
                    return activePlayerHandler.write(
                            stubSuggestion(),
                            Suggestion.class
                    );
                }
        ).thenApplyAsync(
                suggestion -> {
                    System.out.println(suggestion);
                    return activePlayerHandler.write(
                            stubAccusation(),
                            Accusation.class
                    );
                }
        ).join();

        System.out.println("player makes accusation " + accusationChoice);

        System.out.println();

        activePlayerIndex = (activePlayerIndex + 1) % clientHandlers.size();
    }

    private MoveOptions stubMoveOptions()
    {
        MoveOptions moveOptions = new MoveOptions();
        moveOptions.setValidMoves(new ArrayList<>(Arrays.asList("hallway", Room.BALLROOM.name(), Room.CELLAR.name())));
        moveOptions.setTurnIndicator(TurnIndicator.ACTIVE_PLAYER);
        return moveOptions;
    }

    private Suggestion stubSuggestion()
    {
        Suggestion suggestion = new Suggestion();
        suggestion.setSuggestionAction(SuggestionStatus.OFFER_SUGGESTION);
        return suggestion;
    }

    private Accusation stubAccusation()
    {
        Accusation accusation = new Accusation();
        accusation.setAccusationStatus(AccusationStatus.OFFER_ACCUSATION);
        return accusation;
    }
}
