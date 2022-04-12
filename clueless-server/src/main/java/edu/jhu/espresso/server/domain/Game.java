package edu.jhu.espresso.server.domain;
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
        playerList = playerListy;
        cardPlayer = new CardPlayer();
        this.gameBoard = new GameBoard();
        gameTimer = new GameTimer();

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

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }


}

