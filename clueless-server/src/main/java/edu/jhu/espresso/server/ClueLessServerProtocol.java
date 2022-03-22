package edu.jhu.espresso.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ClueLessServerProtocol
{
    private final List<ClueLessClientHandler> clientHandlers;
    private final List<String> validMoves = new ArrayList<>(Arrays.asList("left", "right", "up", "down", "suggestion"));

    private int activePlayerIndex = 0;

    public ClueLessServerProtocol(List<ClueLessClientHandler> clientHandlers)
    {
        this.clientHandlers = clientHandlers;
    }

    public void runTurn()
    {
        ClueLessClientHandler activePlayerHandler = clientHandlers.get(activePlayerIndex);
        List<ClueLessClientHandler> waitingPlayers = clientHandlers.stream()
                .filter(handler -> !handler.equals(activePlayerHandler))
                .collect(Collectors.toList());

        CompletableFuture<MessageStub> activeResponseFuture = activePlayerHandler.write(TurnIndicator.ACTIVE_PLAYER, validMoves);

        List<CompletableFuture<MessageStub>> waitingResponseFutures = waitingPlayers.stream()
                .map(handler -> handler.write(TurnIndicator.WAITING_PLAYER, Collections.emptyList()))
                .collect(Collectors.toList());

        MessageStub activeResponse = activeResponseFuture.join();
        List<MessageStub> responses = waitingResponseFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        responses.add(activeResponse);
        responses.stream().sorted().forEach(System.out::println);

        System.out.println();

        if(activeResponse.getValidMoves().get(0).equals("left"))
        {
            MessageStub response = activePlayerHandler.write(
                    TurnIndicator.ACTIVE_PLAYER,
                    Collections.singletonList("suggestion")
            ).join();

            System.out.println(response);
        }

        System.out.println();

        activePlayerIndex = (activePlayerIndex + 1) % clientHandlers.size();
    }
}
