package edu.jhu.espresso.server.domain.gameEvents;

import edu.jhu.espresso.server.domain.gamepieces.LocationNames;

public class MoveChoice
{
    private LocationNames move;

    public LocationNames getMove()
    {
        return move;
    }

    public void setMove(LocationNames move)
    {
        this.move = move;
    }
}
