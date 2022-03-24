package edu.jhu.espresso.server.domain;

public class TurnStart
{
    private TurnIndicator turnIndicator;
    private GameState gameState;

    public TurnIndicator getTurnIndicator()
    {
        return turnIndicator;
    }

    public void setTurnIndicator(TurnIndicator turnIndicator)
    {
        this.turnIndicator = turnIndicator;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState gameState)
    {
        this.gameState = gameState;
    }

    public static TurnStart fromIndicator(TurnIndicator turnIndicator)
    {
        TurnStart turnStart = new TurnStart();
        turnStart.setTurnIndicator(turnIndicator);

        GameState gameState = new GameState();
        gameState.getCharacterPositions().put(Character.MISS_SCARLET, Room.BILLIARD_ROOM);
        gameState.getCharacterPositions().put(Character.COLONEL_MUSTARD, Room.STUDY);
        gameState.getCharacterPositions().put(Character.PROFESSOR_PLUM, Room.CONSERVATORY);
        gameState.getCharacterPositions().put(Character.MR_GREEN, Room.DINING_ROOM);
        gameState.getCharacterPositions().put(Character.MRS_PEACOCK, Room.CELLAR);
        gameState.getCharacterPositions().put(Character.MRS_WHITE, Room.BALLROOM);

        turnStart.setGameState(gameState);

        return turnStart;
    }

    @Override
    public String toString()
    {
        return "TurnStart{" +
                "turnIndicator=" + turnIndicator +
                ", gameState=" + gameState +
                '}';
    }
}
