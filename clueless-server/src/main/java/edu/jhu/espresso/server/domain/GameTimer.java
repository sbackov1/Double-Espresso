package edu.jhu.espresso.server.domain;
import java.util.*;

public class GameTimer {

    private int turn;
    private int turnLength;
    //private ArrayList<Player> playerList;
    private Player activePlayer;
    private Player passivePlayer;

    public GameTimer(){
        turn = 0;
    }

    public GameTimer(int turnLength, ArrayList<Player> playerList){
        turnLength = turnLength;
        turn = 0;

        //Create a random order for players to play in.
        Collections.shuffle(playerList);

        //Set next player for all players
        for (int i = 0; i < playerList.size() - 1; i++){
            playerList.get(i).setNextPlayer(playerList.get(i + 1));
        }

        //Set previous player for all players.
        for (int i = 1; i < playerList.size(); i++){
            playerList.get(i).setPreviousPlayer(playerList.get(i - 1));
        }

        //Set next and previous for last and first players.
        playerList.get(playerList.size() - 1 ).setNextPlayer(playerList.get(0));
        playerList.get(0).setPreviousPlayer(playerList.get(playerList.size() - 1));

        //Set active player to first in list.
        this.activePlayer = playerList.get(0);
        this.passivePlayer = playerList.get(0);
        }

    //In this case, setActivePlayer returns player instead of "void".
    public void setNewActivePlayer(){

        //Set the passive player to the active player, then the first time getNextPassivePlayer is called it will return next player.
        this.passivePlayer = this.activePlayer.getNextPlayer();
        this.activePlayer = this.activePlayer.getNextPlayer();

    }

    public Player setNextPassivePlayer(){

        return this.getPassivePlayer().getNextPlayer();

        }

    public Player getPassivePlayer() {

        return passivePlayer;

    }

    public Player getActivePlayer(){
        return activePlayer;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurnLength() {
        return turnLength;
    }

    public void setTurnLength(int turnLength) {
        this.turnLength = turnLength;
    }

}
