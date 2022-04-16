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
    public void execute(TurnStart turnStart)
    {
        MoveOptions moveOptions = client.waitForResponse(MoveOptions.class);
        client.write(makeMoveChoice(moveOptions));

        Suggestion suggestion = client.waitForResponse(Suggestion.class);
        client.write(makeSuggestionChoice(suggestion));

        SuggestionTestimonyResponse response = client.waitForResponse(SuggestionTestimonyResponse.class);
        updateNotebook(response);

        Accusation accusation = client.waitForResponse(Accusation.class);
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

        CaseDetails caseDetails = new CaseDetails();
        caseDetails.setRoom(RoomNames.BILLIARD_ROOM);
        caseDetails.setCharacterNames(options.getCharacter());
        caseDetails.setWeapon(options.getWeapon());
        caseDetails.setCharacterNames(options.getCharacter());

        options.setCaseDetails(
                caseDetails
        );
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

    private void updateNotebook(SuggestionTestimonyResponse response)
    {
        response.optionalResponse().ifPresent(value -> client.getPlayer().getNotebook().makeKnownCard(value));
    }
}
