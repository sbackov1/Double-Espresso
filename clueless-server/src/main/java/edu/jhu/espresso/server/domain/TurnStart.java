package edu.jhu.espresso.server.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.jhu.espresso.server.domain.builder.TurnStartBuilder;

@JsonDeserialize(builder = TurnStartBuilder.class)
public class TurnStart
{
    private final ClueLessProtocolType clueLessProtocolType;
    private final GameBoard gameBoard;

    public TurnStart(ClueLessProtocolType clueLessProtocolType, GameBoard gameBoard)
    {
        this.clueLessProtocolType = clueLessProtocolType;
        this.gameBoard = gameBoard;
    }

    public ClueLessProtocolType getClueLessProtocolType()
    {
        return clueLessProtocolType;
    }

    public GameBoard getGameBoard()
    {
        return gameBoard;
    }
}
