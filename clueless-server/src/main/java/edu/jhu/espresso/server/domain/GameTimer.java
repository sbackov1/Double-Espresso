package edu.jhu.espresso.server.domain;

public class GameTimer {

    private int turn;
    private int turnLength;

    public GameTimer(){
        turn = 0;
    }

    public GameTimer(int turnLength){
        turnLength = turnLength;
        turn = 0;
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
