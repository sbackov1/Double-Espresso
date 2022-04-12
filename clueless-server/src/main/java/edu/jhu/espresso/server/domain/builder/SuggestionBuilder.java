package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.domain.CaseDetails;
import edu.jhu.espresso.server.domain.GameBoard;
import edu.jhu.espresso.server.domain.Suggestion;
import edu.jhu.espresso.server.domain.SuggestionStatus;

public final class SuggestionBuilder
{
    private SuggestionStatus suggestionStatus;
    private GameBoard gameBoard;
    private CaseDetails caseDetails;

    private SuggestionBuilder()
    {
    }

    public static SuggestionBuilder aSuggestion()
    {
        return new SuggestionBuilder();
    }

    public SuggestionBuilder withSuggestionStatus(SuggestionStatus suggestionStatus)
    {
        this.suggestionStatus = suggestionStatus;
        return this;
    }

    public SuggestionBuilder withGameBoard(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        return this;
    }

    public SuggestionBuilder withCaseDetails(CaseDetails caseDetails)
    {
        this.caseDetails = caseDetails;
        return this;
    }

    public Suggestion build()
    {
        return new Suggestion(suggestionStatus, gameBoard, caseDetails);
    }
}
