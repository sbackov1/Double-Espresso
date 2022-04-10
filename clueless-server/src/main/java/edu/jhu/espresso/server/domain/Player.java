package edu.jhu.espresso.server.domain;

public class Player {
    private final Character character;
    private boolean activeStatus;
    public Notebook notebook;
    private final int playerID;
    private final int gameID;


    public Player(int gID, int pID, Character ch) {
        this.playerID = pID;
        this.character = ch;
        this.gameID = gID;
        this.activeStatus = true;
    }

    public void makeNotebook(Game game) {
        this.notebook = new Notebook(game.getCardPlayer().getCardDeck());
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public Character getCharacter() {
        return character;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}
