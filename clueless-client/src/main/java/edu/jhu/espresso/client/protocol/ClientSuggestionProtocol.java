package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameBoard;
import edu.jhu.espresso.client.domain.Suggestion;
import edu.jhu.espresso.client.domain.SuggestionStatus;

import java.util.Random;
import java.util.function.Supplier;

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
        Supplier<SuggestionStatus> testimony = random.nextInt() % 6 == 0 ?
                this::proveFalse :
                this::cannotProveFalse;

        suggestion.setSuggestionStatus(testimony.get());

        client.write(suggestion);
    }

    public SuggestionStatus proveFalse()
    {
        System.out.println("Proving the suggestion false");
        return SuggestionStatus.PROVING_SUGGESTION_FALSE;
    }

    public SuggestionStatus cannotProveFalse()
    {
        System.out.println("Cannot prove the suggestion false");
        return SuggestionStatus.CANNOT_DISPROVE;
    }
}
