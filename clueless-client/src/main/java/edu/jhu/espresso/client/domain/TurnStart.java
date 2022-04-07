package edu.jhu.espresso.client.domain;

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

    @Override
    public String toString()
    {
        return "TurnStart{" +
                "turnIndicator=" + turnIndicator +
                ", gameState=" + gameState +
                '}';
    }
}
