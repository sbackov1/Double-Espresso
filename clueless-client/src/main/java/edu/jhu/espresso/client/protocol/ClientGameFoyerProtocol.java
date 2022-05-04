package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameEvents.GameLobby;
import edu.jhu.espresso.client.GameFoyer;

import java.util.ArrayList;

public class ClientGameFoyerProtocol {

    private ClueLessClient client;

    private GameFoyer gf;

    private ArrayList<GameLobby> gameLobbies = null;

    public ClientGameFoyerProtocol(ClueLessClient client) {this.client = client;}

    public void execute() {

       gf = client.waitForResponse(GameFoyer.class);

       gameLobbies = gf.getGameLobbyList();

        //Insert method to display GameLobby choices and allow choice.

        //client.write(choice)



    }
}
