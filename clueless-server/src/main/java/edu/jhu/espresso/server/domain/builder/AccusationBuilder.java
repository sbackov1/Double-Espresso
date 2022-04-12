package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.domain.Accusation;
import edu.jhu.espresso.server.domain.AccusationStatus;
import edu.jhu.espresso.server.domain.CaseDetails;
import edu.jhu.espresso.server.domain.GameBoard;

public final class AccusationBuilder
{
    private AccusationStatus accusationStatus;
    private GameBoard gameBoard;
    private CaseDetails caseDetails;

    private AccusationBuilder()
    {
    }

    public static AccusationBuilder anAccusation()
    {
        return new AccusationBuilder();
    }

    public AccusationBuilder withAccusationStatus(AccusationStatus accusationStatus)
    {
        this.accusationStatus = accusationStatus;
        return this;
    }

    public AccusationBuilder withGameBoard(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        return this;
    }

    public AccusationBuilder withCaseDetails(CaseDetails caseDetails)
    {
        this.caseDetails = caseDetails;
        return this;
    }

    public Accusation build()
    {
        return new Accusation(accusationStatus, gameBoard, caseDetails);
    }
}
