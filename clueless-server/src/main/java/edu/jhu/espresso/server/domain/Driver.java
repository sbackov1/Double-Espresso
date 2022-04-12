/****
 * TODO LIST:
 * Create GameLobby Class
 * Decide on a system of passing location references:  Strings vs. Objects
 * This will enable the change location feature to work (with some adjustments).
 * Implement Suggestion / Accusation Procedure
 * End Game Sequence?
 *
 */



package edu.jhu.espresso.server.domain;

import java.util.ArrayList;

public class Driver {

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
}
