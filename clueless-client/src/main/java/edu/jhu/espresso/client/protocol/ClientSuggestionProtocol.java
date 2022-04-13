package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameBoard;
import edu.jhu.espresso.client.domain.Suggestion;
import edu.jhu.espresso.client.domain.SuggestionStatus;

import java.util.Random;

class ClientSuggestionProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;

    public ClientSuggestionProtocol(ClueLessClient client)
    {
        this.client = client;
    }

    @Override
    public void execute(GameBoard gameBoard)
    {
        Suggestion suggestion = client.waitForResponse(Suggestion.class);
        Random random = new Random();
        suggestion.setSuggestionStatus(
                random.nextInt() % 2 == 0 ?
                        SuggestionStatus.PROVING_SUGGESTION_FALSE :
                        SuggestionStatus.CANNOT_DISPROVE
        );
        client.write(suggestion);
    }
}
