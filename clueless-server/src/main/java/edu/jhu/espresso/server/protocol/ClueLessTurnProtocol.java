package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.domain.*;
import edu.jhu.espresso.server.domain.gameEvents.MoveOptions;
import edu.jhu.espresso.server.domain.gameEvents.Suggestion;
import edu.jhu.espresso.server.domain.gameEvents.Accusation;
import edu.jhu.espresso.server.domain.gamepieces.Weapon;
import edu.jhu.espresso.server.domain.gameEvents.SuggestionStatus;
import edu.jhu.espresso.server.domain.gameEvents.AccusationStatus;
import edu.jhu.espresso.server.domain.gamepieces.Room;
import edu.jhu.espresso.server.domain.builder.AccusationBuilder;
import edu.jhu.espresso.server.domain.builder.ServerActivePlayerProtocolOffererBuilder;
import edu.jhu.espresso.server.domain.builder.SuggestionBuilder;
import edu.jhu.espresso.server.domain.gamepieces.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ClueLessTurnProtocol
{
    private final Game game;
    private final List<Player> waitingPlayers;
    private final Player activePlayer;

    private final MoveOptions moveOptions;

    private boolean playerHasMoved;
    private boolean playerHasSuggested;

    private boolean canMove;
    private boolean canSuggest;

    private Location activePlayerLoc;
    private Location activePlayerStartingLoc;

    List<String> validCharacters;

    List<String> validWeapons;

    public ClueLessTurnProtocol(
            List<Player> waitingPlayers,
            Player activePlayer,
            Game game
    ) {
        this.waitingPlayers = waitingPlayers;
        this.activePlayer = activePlayer;
        this.game = game;
        this.moveOptions = determineValidMoveOptions(game.getGameBoard());

        activePlayerLoc = this.game.getGameBoard().getCharacterLocation(this.activePlayer.getCharacter().getName());
        activePlayerStartingLoc = activePlayerLoc;

        validCharacters = Arrays.stream(CharacterNames.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        validWeapons = Arrays.stream(Weapon.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public boolean executeTurn()
    {


        notifyPlayersOfStatus();

        boolean endTurn = false;

        this.playerHasMoved = false;
        this.playerHasSuggested = false;

        while(!endTurn) {

            //Update the location so that the suggestion builder works properly.
            activePlayerLoc = this.game.getGameBoard().getCharacterLocation(this.activePlayer.getCharacter().getName());

            ServerActivePlayerProtocolOfferer gameOptions = this.createProtocolOfferer();

            ActivePlayerProtocolSelector activePlayerChoice = activePlayer.writeInstanceAndExpectType(gameOptions, ActivePlayerProtocolSelector.class);

            //Run through and get the move choice, and execute the command in the server.
            activePlayerChoice.getMoveChoice().ifPresent(moveChoice -> {
                CharacterNames characterName = activePlayer.getCharacter().getName();
                game.applyMoveChoice(moveChoice, characterName);
                game.getGameBoard().setHallwayEmptyByName(activePlayerStartingLoc.getLocationName());

                ClueLessServerGameProtocol.broadcast(game, characterName.name()  + " moved to " + moveChoice.getMove(), waitingPlayers);
            });

            activePlayerChoice.getAccusation().ifPresent(accusation -> new ServerAccusationProtocol(activePlayer, waitingPlayers, accusation, game).execute());
            activePlayerChoice.getSuggestion().ifPresent(this::launchSuggestionTestimony);

            //Set playerHasMoved or playerHasSuggested
            if(activePlayerChoice.getMoveChoice().isPresent()){setPlayerHasMoved(true);}
            if(activePlayerChoice.getSuggestion().isPresent()){setPlayerHasSuggested(true);}

            //If player has made an accusation, end the current turn.
            if (activePlayerChoice.getAccusation().isPresent()) {
                endTurn = true;
            }

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
        TurnStart activePlayerTurnStart = new TurnStart(ClueLessProtocolType.ACTIVE_PLAYER, locationNamesMap, "", null);
        TurnStart waitingPlayerTurnStart = new TurnStart(ClueLessProtocolType.WAITING_PLAYER, locationNamesMap, "", null);

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
        Room thisRoom = (Room) activePlayerLoc;

        return SuggestionBuilder.aSuggestion()
                .withSuggestionStatus(SuggestionStatus.OFFER_SUGGESTION)
                .withRoomNames(thisRoom.getRoomName())
                .withValidCharacters(validCharacters)
                .withValidWeapons(validWeapons)
                .build();
    }

    private Accusation buildOfferAccusationMessage()
    {
        List<String> validRooms = Arrays.stream(RoomNames.values())
                .map(Enum::name)
                .collect(Collectors.toList());


        return AccusationBuilder.anAccusation()
                .withAccusationStatus(AccusationStatus.OFFER_ACCUSATION)
                .withValidRooms(validRooms)
                .withValidCharacters(validCharacters)
                .withValidWeapons(validWeapons)
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
            ClueLessServerGameProtocol.broadcastSuggestedPlayer(
                    game,
                    suggestion.getCharacter().name() + " was suggested in the " + suggestion.getRoomNames().name(),
                    waitingPlayers,
                    suggestion.getCharacter()
            );
        }

        List<Player> allPlayers = new ArrayList<>(waitingPlayers);
        allPlayers.add(activePlayer);

        ClueLessServerGameProtocol.broadcast(
                game,
                activePlayer.getCharacter().getName() + " suggested " + character + " in the " + room +
                        " with the " + weapon,
                allPlayers
        );

        new SuggestionTestimonyProtocol(waitingPlayers, activePlayer, suggestion, game).execute();
    }

    public boolean isPlayerHasMoved() {
        return playerHasMoved;
    }

    public void setPlayerHasMoved(boolean playerHasMoved) {
        this.playerHasMoved = playerHasMoved;
    }

    public boolean isPlayerHasSuggested() {
        return playerHasSuggested;
    }

    public void setPlayerHasSuggested(boolean playerHasSuggested) {
        this.playerHasSuggested = playerHasSuggested;
    }

    /*** The validateCanMove method determines whether the activePlayer can legally move.
     */
    public void validateCanMove(){
        this.canMove = this.moveOptions.getValidMoves().size() != 0 && !this.isPlayerHasMoved();
    }

    /**
     * The validateCanSuggest method determines whether the activePlayer is in a room and can make a suggestion.
     **/

    public void validateCanSuggest(){

        String locationType =  LocationNames.StringLocationTypeFromStringName(activePlayerLoc.getLocationName());

        canSuggest = locationType.equals("Room") && !this.isPlayerHasSuggested();
    }

    /** createProtocolOfferer creates a protocol offerer using the validateCanSuggest(){}
    **/
    private ServerActivePlayerProtocolOfferer createProtocolOfferer(){
        this.validateCanMove();
        this.validateCanSuggest();

        ServerActivePlayerProtocolOffererBuilder offerBuilder = ServerActivePlayerProtocolOffererBuilder.aServerActivePlayerProtocolOfferer();

            if(canMove){
                offerBuilder.withOfferMoveOptions(moveOptions);
            }

            //Add suggestion
            if(canSuggest){
                offerBuilder.withOfferSuggestion(this.buildOfferSuggestionMessage());
            }

            //Add accusation
            offerBuilder.withOfferAccusation(this.buildOfferAccusationMessage());

    return offerBuilder.build();
    }



}
