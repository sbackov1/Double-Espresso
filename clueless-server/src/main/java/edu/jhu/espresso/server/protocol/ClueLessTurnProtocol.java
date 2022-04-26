package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.domain.*;
import edu.jhu.espresso.server.domain.builder.AccusationBuilder;
import edu.jhu.espresso.server.domain.builder.SuggestionBuilder;
import edu.jhu.espresso.server.domain.gameEvents.*;
import edu.jhu.espresso.server.domain.gamepieces.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ClueLessTurnProtocol
{
    private final Game game;
    private final List<Player> waitingPlayers;
    private final Player activePlayer;

    public static boolean playerHasMoved;
    public static boolean playerHasSuggested;

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
        boolean endTurn = false;
        playerHasMoved = false;
        playerHasSuggested = false;

        while(!endTurn) {

            ActivePlayerProtocolSelector activePlayerChoice = activePlayer.writeInstanceAndExpectType(determineValidMoveOptions(game.getGameBoard()), ActivePlayerProtocolSelector.class);

            //Run through and get the move choice, and execute the command in the server.
            activePlayerChoice.getMoveChoice().ifPresent(moveChoice -> game.applyMoveChoice(moveChoice, activePlayer.getCharacter().getName()));
            activePlayerChoice.getAccusation().ifPresent(accusation -> new ServerAccusationProtocol(activePlayer, waitingPlayers, accusation, game).execute());
            activePlayerChoice.getSuggestion().ifPresent(this::launchSuggestionTestimony);

            //Set playerHasMoved or playerHasSuggested
            if(activePlayerChoice.getMoveChoice().isPresent()){setPlayerHasMoved(true);}
            if(activePlayerChoice.getSuggestion().isPresent()){setPlayerHasSuggested(true);}

            if (!activePlayerChoice.getMoveChoice().isPresent() && !activePlayerChoice.getSuggestion().isPresent() && !activePlayerChoice.getAccusation().isPresent()){
                endTurn = true;
            }

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

    public static boolean isPlayerHasMoved() {
        return playerHasMoved;
    }

    public static void setPlayerHasMoved(boolean playerHasMoved) {
        ClueLessTurnProtocol.playerHasMoved = playerHasMoved;
    }

    public static boolean isPlayerHasSuggested() {
        return playerHasSuggested;
    }

    public static void setPlayerHasSuggested(boolean playerHasSuggested) {
        ClueLessTurnProtocol.playerHasSuggested = playerHasSuggested;
    }
}
