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
        gameState.getCharacterPositions().put(CharacterNames.MISS_SCARLET, RoomNames.BILLIARD_ROOM);
        gameState.getCharacterPositions().put(CharacterNames.COLONEL_MUSTARD, RoomNames.STUDY);
        gameState.getCharacterPositions().put(CharacterNames.PROFESSOR_PLUM, RoomNames.CONSERVATORY);
        gameState.getCharacterPositions().put(CharacterNames.MR_GREEN, RoomNames.DINING_ROOM);
        gameState.getCharacterPositions().put(CharacterNames.MRS_WHITE, RoomNames.BALLROOM);

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
