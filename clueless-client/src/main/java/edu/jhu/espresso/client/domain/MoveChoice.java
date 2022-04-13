package edu.jhu.espresso.client.domain;

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
