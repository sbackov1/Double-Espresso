package edu.jhu.espresso.server.domain;

public class Player {
    private final Character character;
    private boolean activeStatus;
    public Notebook notebook;
    private final int playerID;
    private final int gameID;

    private Player nextPlayer;
    private Player previousPlayer;


    public Player(int gID, int pID, Character ch) {
        this.playerID = pID;
        this.character = ch;
        this.gameID = gID;
        this.activeStatus = true;
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

    public Player getPreviousPlayer() {
        return previousPlayer;
    }

    public void setPreviousPlayer(Player previousPlayer) {
        this.previousPlayer = previousPlayer;
    }
}
