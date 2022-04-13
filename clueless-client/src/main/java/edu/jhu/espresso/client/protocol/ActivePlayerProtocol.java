package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.*;

class ActivePlayerProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;

    public ActivePlayerProtocol(ClueLessClient client)
    {
        this.client = client;
    }

    @Override
    public void execute(GameBoard gameBoard)
    {
        MoveOptions moveOptions = client.waitForResponse(MoveOptions.class);
        sleep(1000);
        client.write(makeMoveChoice(moveOptions));

        Suggestion suggestion = client.waitForResponse(Suggestion.class);
        sleep(500);
        client.write(makeSuggestionChoice(suggestion));

        Accusation accusation = client.waitForResponse(Accusation.class);
        sleep(1500);
        client.write(makeAccusationChoice(accusation));
    }

    private Accusation makeAccusationChoice(Accusation options)
    {
        options.mainAccMenu();
        options.setAccusationStatus(AccusationStatus.MAKING_ACCUSATION);
        return options;
    }

    private Suggestion makeSuggestionChoice(Suggestion options)
    {
        options.mainSugMenu();
        options.setRoomNames(RoomNames.BILLIARD_ROOM);
        options.setSuggestionStatus(SuggestionStatus.MAKING_SUGGESTION);
        return options;
    }

    private MoveChoice makeMoveChoice(MoveOptions options)
    {
        options.mainMoveMenu();
        return new MoveChoice(options.getLocation());
    }

    private void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            throw new IllegalStateException(e);
        }
    }
}
