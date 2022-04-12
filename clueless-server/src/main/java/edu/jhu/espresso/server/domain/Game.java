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

    public static void main(String[] args) {

    Character Professor_Plum = new Character("PROFESSOR_PLUM");
    Character Colonel_Mustard= new Character("COLONEL_MUSTARD");
    Player jim = new Player(1,  1, Professor_Plum);
    Player jon = new Player( 1, 3, Colonel_Mustard);
    ArrayList<Player> playerList = new ArrayList<Player>();
    playerList.add(jim);
    playerList.add(jon);
    GameBoard gb = new GameBoard();
    Game thisGame = new Game(1, playerList);
    int x = 0;
    }

    public void applyMoveChoice(MoveChoice moveChoice)
    {

    }
}

