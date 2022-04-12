package edu.jhu.espresso.server.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.jhu.espresso.server.domain.builder.AccusationBuilder;

import java.util.Objects;
import java.util.Optional;

@JsonDeserialize(builder = AccusationBuilder.class)
public final class Accusation
{
    private final AccusationStatus accusationStatus;
    private final GameBoard gameBoard;
    private final CaseDetails caseDetails;

    public Accusation(AccusationStatus accusationStatus, GameBoard gameBoard, CaseDetails caseDetails)
    {
        this.accusationStatus = Objects.requireNonNull(accusationStatus);
        this.gameBoard = Objects.requireNonNull(gameBoard);
        this.caseDetails = caseDetails;
    }

    public AccusationStatus getAccusationStatus()
    {
        return accusationStatus;
    }

    public Optional<CaseDetails> getCaseDetails()
    {
        return Optional.ofNullable(caseDetails);
    }

    public GameBoard getGameBoard()
    {
        return gameBoard;
    }
}
