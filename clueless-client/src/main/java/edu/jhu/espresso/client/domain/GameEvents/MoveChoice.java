package edu.jhu.espresso.client.domain.GameEvents;

import edu.jhu.espresso.client.domain.GamePieces.LocationNames;

public class MoveChoice
{
    private final LocationNames move;

    public MoveChoice(LocationNames move)
    {
        this.move = move;
    }

    public LocationNames getMove()
    {
        return move;
    }
}
