package edu.jhu.espresso.server.domain;
import edu.jhu.espresso.server.domain.gameEvents.GameTimer;
import edu.jhu.espresso.server.domain.gameEvents.MoveChoice;
import edu.jhu.espresso.server.domain.gamepieces.*;

import java.util.*;
public class Game {

    public int GameID;
    private CardPlayer cardPlayer;
    private GameBoard gameBoard;
    public ArrayList<Player> playerList;
    public GameTimer gameTimer;

    public Game(int gameID, ArrayList<Player> playerListy) {
        GameID = gameID;

        //Instantiate objects
        cardPlayer = new CardPlayer();
        playerList = playerListy;
        this.gameBoard = new GameBoard();
        gameTimer = new GameTimer(1, playerListy);
    }


    public int getGameID() {
        return GameID;
    }

    public CardPlayer getCardPlayer() {
        return cardPlayer;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public boolean isOver()
    {
        return false;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    //Startgame creates notebooks, deals cards, and starts the turn of the first active player.
    public void startGame(){
        for (Player p : playerList){
            p.makeNotebook(this.getCardPlayer().getCardDeck());
        }

        this.getCardPlayer().dealCards(this.getPlayerList());

    }

    //confirmAccusation returns true if accusation is accurate and false if not.
    public boolean confirmAccusation(ArrayList<Card> Accusation){
        return Accusation.containsAll(cardPlayer.getCaseFile());
    }


    //confirmSuggestion loops through players and checks their notebooks for the correct card.
    public ArrayList<Card> confirmSuggestion (ArrayList<Card> suggestion) {

        Player activePlayer = this.gameTimer.getActivePlayer();
        Player passivePlayer = activePlayer;

        //Stop when a full loop is created.
        while ( passivePlayer.getNextPlayer() != activePlayer){

            passivePlayer = this.gameTimer.setNextPassivePlayer();
            ArrayList<Card> disproveCards = passivePlayer.notebook.canDisproveSuggestion(suggestion);

            if (disproveCards.size() != 0) {return disproveCards;}
        }

        return null;

    }

    public void endTurn(){

        this.getGameTimer().setNewActivePlayer();

    }

    public Map<CharacterNames, LocationNames> getLocations()
    {
        return gameBoard.getLocationMappingNames();
    }

    public void applyMoveChoice(MoveChoice moveChoice, CharacterNames characterNames)
    {
        gameBoard.moveCharacter(characterNames, Location.fromLocationName(moveChoice.getMove()));

        Location thisLoc = gameBoard.getCharacterLocation(characterNames);

        this.gameBoard.setHallwayFullByName(thisLoc.getLocationName());

    }


}




