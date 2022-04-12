package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.domain.ClueLessProtocolType;
import edu.jhu.espresso.server.domain.GameBoard;
import edu.jhu.espresso.server.domain.TurnStart;

public final class TurnStartBuilder
{
    private ClueLessProtocolType clueLessProtocolType;
    private GameBoard gameBoard;

    private TurnStartBuilder()
    {
    }

    public static TurnStartBuilder aTurnStart()
    {
        return new TurnStartBuilder();
    }

    public TurnStartBuilder withClueLessProtocolType(ClueLessProtocolType clueLessProtocolType)
    {
        this.clueLessProtocolType = clueLessProtocolType;
        return this;
    }

    public TurnStartBuilder withGameBoard(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        return this;
    }

    public TurnStart build()
    {
        return new TurnStart(clueLessProtocolType, gameBoard);
    }
}
