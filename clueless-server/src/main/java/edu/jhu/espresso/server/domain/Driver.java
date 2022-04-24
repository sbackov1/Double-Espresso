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

import edu.jhu.espresso.server.domain.gamepieces.*;
import edu.jhu.espresso.server.domain.gamepieces.Character;

import java.util.ArrayList;

import static edu.jhu.espresso.server.domain.gamepieces.CharacterNames.COLONEL_MUSTARD;
import static edu.jhu.espresso.server.domain.gamepieces.CharacterNames.PROFESSOR_PLUM;

public class Driver {

    public static void main(String[] args) {

        Character Professor_Plum = new Character(PROFESSOR_PLUM);
        Character Colonel_Mustard= new Character(COLONEL_MUSTARD);
        Player jim = new Player(1,  1, Professor_Plum, null);
        Player jon = new Player( 1, 3, Colonel_Mustard, null);
        ArrayList<Player> playerList = new ArrayList<Player>();
        playerList.add(jim);
        playerList.add(jon);

        //Create game
        Game thisGame = new Game(1, playerList);
        thisGame.startGame();

        //Move player into hallway from homesquare.
        ArrayList<Location> thisLegalMovesList = thisGame.getGameBoard().getLegalMoves(PROFESSOR_PLUM);
        Location newLocation = thisLegalMovesList.get(0);
        thisGame.getGameBoard().moveCharacter(PROFESSOR_PLUM, newLocation);

        //Move player into room from hallway.
        ArrayList<Location> thisLegalMovesList2 = thisGame.getGameBoard().getLegalMoves(PROFESSOR_PLUM);
        Location newLocation2 = thisLegalMovesList2.get(0);
        thisGame.getGameBoard().moveCharacter(PROFESSOR_PLUM, newLocation2);

        //End Turn / Start new turn
        System.out.println("Active Player: " + thisGame.gameTimer.getActivePlayer().getPlayerID());
        thisGame.endTurn();
        System.out.println("Active Player: " + thisGame.gameTimer.getActivePlayer().getPlayerID());

        //Make a suggestion
        //Suggestion equal to the casefile.
        ArrayList<Card> suggestion = thisGame.getCardPlayer().getCaseFile();
        ArrayList<Card> suggResult = thisGame.confirmSuggestion(suggestion);

        //Suggestion is disprovable by player.
        ArrayList<Card> suggestion2 = new ArrayList<Card>();
        suggestion2.add(thisGame.getGameTimer().getActivePlayer().getNextPlayer().getNotebook().getHandCards().get(0));
        suggestion2.add(new WeaponCard(Weapon.LEAD_PIPE));
        suggestion2.add(new RoomCard(RoomNames.BALLROOM));
        ArrayList<Card> suggResult2 = thisGame.confirmSuggestion(suggestion2);

        //Player makes a true accusation.
        ArrayList<Card> trueAccusation = thisGame.getCardPlayer().getCaseFile();
        boolean trueAccusationResult = thisGame.confirmAccusation(trueAccusation);

        //Player makes a false accusation.
        ArrayList<Card> falseAccusation = new ArrayList<Card>();
        falseAccusation.add(thisGame.getGameTimer().getActivePlayer().getNextPlayer().getNotebook().getHandCards().get(0));
        falseAccusation.add(new WeaponCard(Weapon.LEAD_PIPE));
        falseAccusation.add(new RoomCard(RoomNames.BALLROOM));
        boolean falseAccusationResult = thisGame.confirmAccusation(falseAccusation);

        int x = 0;

    }
}
