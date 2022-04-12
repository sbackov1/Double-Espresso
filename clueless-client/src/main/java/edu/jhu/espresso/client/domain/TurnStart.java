package edu.jhu.espresso.client.domain;

public class TurnStart
{
    private ClueLessProtocolType clueLessProtocolType;
    private GameState gameState;

    public ClueLessProtocolType getProtocolType()
    {
        return clueLessProtocolType;
    }

    public void setTurnIndicator(ClueLessProtocolType clueLessProtocolType)
    {
        this.clueLessProtocolType = clueLessProtocolType;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState gameState)
    {
        this.gameState = gameState;
    }
}
