package edu.jhu.espresso.server.domain;
import java.util.*;

public class GameTimer {

    private int turn;
    private int turnLength;
    private ArrayList<Player> playerOrder;
    private Player activePlayer;
    private int passivePlayerIndex;

    public GameTimer(){
        turn = 0;
    }

    public GameTimer(int turnLength, ArrayList<Player> playerList){
        turnLength = turnLength;
        turn = 0;

        //Create a random order for players to play in.
        Collections.shuffle(playerList);
        Collections.copy(this.playerOrder, playerList);

        //Set active player to first in list.
        this.activePlayer = this.playerOrder.get(0);
        }

    //In this case, setActivePlayer returns player instead of "void".
    //Would be sweet to make this a circular linked list.
    public Player setNewActivePlayer(){
        int currentActiveIndex = playerOrder.indexOf(activePlayer);
        int newActiveIndex;

        //If the current player is last in the list, go to beginning
        if (playerOrder.size() == currentActiveIndex + 1){newActiveIndex = 0;}
        else newActiveIndex = currentActiveIndex + 1;

        //Set passive player index to activeplayer index
        passivePlayerIndex = newActiveIndex;

        return playerOrder.get(newActiveIndex);
    }

    public Player getNextPassivePlayer(){
        if(passivePlayerIndex + 1 == playerOrder.size()) {
            passivePlayerIndex = 0;}

        else{passivePlayerIndex += 1;}

        return playerOrder.get(passivePlayerIndex);
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
