package edu.jhu.espresso.server.domain.PreGame;

import edu.jhu.espresso.server.ClueLessServer;
import edu.jhu.espresso.server.domain.Game;
import edu.jhu.espresso.server.domain.gamepieces.Character;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;
import edu.jhu.espresso.server.domain.gamepieces.Player;
import edu.jhu.espresso.server.protocol.ClueLessServerGameProtocol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * The gamelobby class is responsible for setting up individual games.
 * It receives players from the Foyer and adds them to the game.
 * It creates a character list and offers the characters to players in the order that they join the game.
 * It offers the gametimer object to the players and allows them to choose the turn length.
 * ***/


public class GameLobby {

    private Game thisGame;
    private ArrayList<Player> thisGamePlayerList;
    private Player host;
    private ArrayList<Character> unusedCharacters;
    private int gameNumber;
    private int turnLengthTimer;

    private CompletableFuture<Boolean> startGame;

    private boolean isStartGame;

    private ClueLessServer server;

    public GameLobby(
            int gameNumber,
            ClueLessServer server,
            Player firstPlayer)
    {
        this.gameNumber = gameNumber;
        this.server = server;
        this.host = firstPlayer;
        this.makeUnusedCharacters();
    }



    public void execute()
    {
        this.setPlayerCharacter(this.host);

        this.askHostForTimer();

        this.startGame = this.askHostForStartGame();

        while (this.thisGamePlayerList.size() <= 6){

            //listen for players being added.

            try {
                if (this.startGame.isDone()) {
                    if (this.startGame.get()) {
                        this.isStartGame = true;
                    }
                }
            } catch(InterruptedException | ExecutionException ex){
                System.out.println("Interrupted exception!");
            }
        if(this.isStartGame){
            break;
        }

        }

        this.thisGame = new Game(gameNumber, this.thisGamePlayerList);

        ClueLessServerGameProtocol clueLessServerGameProtocol = new ClueLessServerGameProtocol(this.thisGamePlayerList, this.thisGame);

        clueLessServerGameProtocol.playGame();

    }

    /**
     * Getters and setters
     * **/

    public ArrayList<Player> getThisGamePlayerList() {
        return thisGamePlayerList;
    }

    public Object addPlayer(Player p){
        if(this.getThisGamePlayerList().size() <= 6){

            this.setPlayerCharacter(p);

            return getThisGamePlayerList().add(p);
        }
        else return null;
    }

    public void setPlayerCharacter(Player p){
//        p.setCharacter(p.writeInstanceAndExpectType(getUnusedCharacters(), Character.class));
    }

    public void setThisGamePlayerList(ArrayList<Player> thisGamePlayerList) {
        this.thisGamePlayerList = thisGamePlayerList;
    }

    public Player getHost() {
        return host;
    }

    public void setHost(Player host) {
        this.host = host;
    }

    public ArrayList<Character> getUnusedCharacters() {
        return unusedCharacters;
    }

    public void makeUnusedCharacters() {
        Arrays.asList(CharacterNames.values())
                .forEach(CharName -> this.unusedCharacters.add(new Character(CharName)));
        }

    public int getGameNumber() {
        return gameNumber;
    }

    private void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public int getTurnLengthTimer() {
        return turnLengthTimer;
    }

    private void setTurnLengthTimer(int turnLengthTimer) {
        this.turnLengthTimer = turnLengthTimer;
    }

    /**
     * End getters and setters.
     * **/

    private void askHostForTimer(){
        this.setTurnLengthTimer(this.host.writeInstanceAndExpectType("Choose turn length.", int.class));
    }

    private CompletableFuture<Boolean> askHostForStartGame(){

        CompletableFuture<Boolean> startGame = this.host.asyncWriteInstanceAndExpectType("Do you want to start the game?", boolean.class);

        return startGame;

    }



}
