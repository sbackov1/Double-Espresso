package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.server.domain.PreGame.GameFoyer;
import edu.jhu.espresso.server.domain.PreGame.GameLobby;

import java.util.ArrayList;

public class ClientGameFoyerProtocol {

    private ClueLessClient client;

    private GameFoyer gf;

    private ArrayList<GameLobby> gameLobbies = null;

    public ClientGameFoyerProtocol(ClueLessClient client) {this.client = client;}

    public void execute() {

       gf = client.waitForResponse(GameFoyer);

       gameLobbies = gf.getGameLobbyList();

        //Insert method to display GameLobby choices and allow choice.

        //client.write(choice)



    }
}
