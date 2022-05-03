package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.ClueLessServer;
import edu.jhu.espresso.server.domain.Game;
import edu.jhu.espresso.server.domain.PreGame.GameLobby;
import edu.jhu.espresso.server.domain.gamepieces.Player;

import java.util.ArrayList;

public final class GameLobbyBuilder {
    private ArrayList<Player> thisGamePlayerList;
    private Player host;
    private int turnLengthTimer;
    private int gameNumber;
    private ClueLessServer server;

    private Game thisGame;

    private GameLobbyBuilder() {
    }

    public static GameLobbyBuilder aGameLobby() {
        return new GameLobbyBuilder();
    }

    public GameLobbyBuilder withThisGamePlayerList(ArrayList<Player> thisGamePlayerList) {
        this.thisGamePlayerList = thisGamePlayerList;
        return this;
    }

    public GameLobbyBuilder withHost(Player host) {
        this.host = host;
        return this;
    }

    public GameLobbyBuilder withTurnLengthTimer(int turnLengthTimer) {
        this.turnLengthTimer = turnLengthTimer;
        return this;
    }

    public GameLobbyBuilder withGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
        return this;
    }

    public GameLobbyBuilder withServer(ClueLessServer server) {
        this.server = server;
        return this;
    }

    public GameLobbyBuilder withGame (Game game){
        this.thisGame = game;
        return this;
    }


    public GameLobby build() {
        GameLobby gameLobby = new GameLobby(gameNumber, server, host);
        return gameLobby;
    }
}
