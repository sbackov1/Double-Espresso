package edu.jhu.espresso.client.domain.GameEvents;

import java.util.List;

public final class NewGame
{
    private List<String> playerIds;
    private String gameId;

    public List<String> getPlayerIds()
    {
        return playerIds;
    }

    public void setPlayerIds(List<String> playerIds)
    {
        this.playerIds = playerIds;
    }

    public String getGameId()
    {
        return gameId;
    }

    public void setGameId(String gameId)
    {
        this.gameId = gameId;
    }
}
