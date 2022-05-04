package edu.jhu.espresso.server.domain.PreGame;

import edu.jhu.espresso.server.ClueLessClientHandler;
import edu.jhu.espresso.server.ClueLessServer;
import edu.jhu.espresso.server.domain.gamepieces.Player;

import java.io.IOException;
import java.util.ArrayList;

public class GameFoyer {

    private static GameFoyer gameFoyer = null;
    public ArrayList<GameLobby> gameLobbyList;

    ClueLessServer clueLessServer;

    private static int nextGameNumber = 0;

    /**
     * GameFoyer is a singleton object.
     * **/

    private GameFoyer(){
        this.gameLobbyList = new ArrayList<>();
        this.clueLessServer = new ClueLessServer();
        int nextPlayerID = 0;

        while(true){
            try {
                ClueLessClientHandler handler = clueLessServer.accept();

                Player newPlayer = new Player(nextPlayerID, 0, null, handler);

                nextPlayerID += 1;

                GameLobby gameLobbyChoice = newPlayer.writeInstanceAndExpectType(gameLobbyList, GameLobby.class);

                if(gameLobbyList.contains(gameLobbyChoice)) {
                    gameLobbyChoice.addPlayer(newPlayer);
                }

                else {
                    addToGameLobbyList(newPlayer);
                    gameLobbyList.get(gameLobbyList.size()-1).execute();
                }

            }
            catch(IOException ie){
                System.out.println("IO Exception");
            }
        }

    }

    public static GameFoyer makeGameFoyer(){
        if (gameFoyer == null){gameFoyer = new GameFoyer();}
        return gameFoyer;
    }

    public ArrayList<GameLobby> getGameLobbyList() {
        return gameLobbyList;
    }

    public void addToGameLobbyList(Player player) {
        this.gameLobbyList.add(new GameLobby(nextGameNumber, this.clueLessServer, player));
        nextGameNumber += 1;
    }

    public void removeGameFromList(GameLobby gameLobby){
        this.getGameLobbyList().remove(gameLobby);
    }

    public void sendPlayertoGame(Player player, GameLobby lobby){
        lobby.addPlayer(player);
    }





}


