package edu.jhu.espresso.client.domain.GamePieces;

import javafx.scene.shape.Circle;

public class Player {
    private CharacterNames character;
    private boolean activeStatus;
    private Notebook notebook;
    private final int playerID;
    private final int gameID;

    private Player nextPlayer;
    private Player previousPlayer;
    private Circle circle;

    private boolean hasMoved;
    private boolean hasSuggested;
    private boolean pulledFromSuggestion;

    public Player(int gID, int pID) {
        this.playerID = pID;
        this.gameID = gID;
        this.activeStatus = true;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Circle getCircle() {
        return circle;
    }

    public void makeNotebook(CardDeck c) {
        this.notebook = new Notebook(c);
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public CharacterNames getCharacter() {
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

    public void setCharacter(CharacterNames character)
    {
        this.character = character;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean isHasSuggested() {
        return hasSuggested;
    }

    public void setHasSuggested(boolean hasSuggested) {
        this.hasSuggested = hasSuggested;
    }

    public boolean isPulledFromSuggestion()
    {
        return pulledFromSuggestion;
    }

    public void setPulledFromSuggestion(boolean pulledFromSuggestion)
    {
        this.pulledFromSuggestion = pulledFromSuggestion;
    }
}
