package edu.jhu.espresso.server.domain.gamepieces;

import edu.jhu.espresso.server.ClueLessClientHandler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class Player {
    private final Character character;
    private boolean activeStatus;
    public Notebook notebook;
    private final int playerID;
    private final int gameID;
    private final ClueLessClientHandler clueLessClientHandler;
    private Player nextPlayer;

    public Player(int gID, int pID, Character ch, ClueLessClientHandler clueLessClientHandler) {
        this.playerID = pID;
        this.character = ch;
        this.gameID = gID;
        this.clueLessClientHandler = Objects.requireNonNull(clueLessClientHandler);
        this.activeStatus = true;
        notebook = new Notebook(new CardDeck());
    }

    public void makeNotebook(CardDeck c) {
        this.notebook = new Notebook(c);
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public Character getCharacter() {
        return character;
    }

    public boolean getActiveStatus() {
        return activeStatus;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public ClueLessClientHandler getClueLessClientHandler()
    {
        return clueLessClientHandler;
    }

    public <I, O> O writeInstanceAndExpectType(I input, Class<O> responseClass)
    {
        return clueLessClientHandler.writeInstanceAndExpectType(input, responseClass);
    }

    public <I, O> CompletableFuture<O> asyncWriteInstanceAndExpectType(I input, Class<O> responseClass)
    {
        return clueLessClientHandler.asyncWriteInstanceAndExpectType(input, responseClass);
    }

    public void write(Object message)
    {
        clueLessClientHandler.write(message);
    }

    public PrintWriter getPrintWriter()
    {
        return clueLessClientHandler.getPrintWriter();
    }

    public BufferedReader getBufferedReader()
    {
        return clueLessClientHandler.getBufferedReader();
    }
}
