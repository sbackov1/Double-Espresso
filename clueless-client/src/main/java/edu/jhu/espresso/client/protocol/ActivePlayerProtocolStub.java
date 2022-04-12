package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.*;
import edu.jhu.espresso.client.domain.Character;

import java.util.Collections;

class ActivePlayerProtocolStub implements ClueLessProtocol
{
    private final ClueLessClient client;

    public ActivePlayerProtocolStub(ClueLessClient client)
    {
        this.client = client;
    }

    @Override
    public void execute(GameState gameState)
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
        System.out.println("game offers accusation " + options);
        System.out.println();
        Accusation accusation = new Accusation();
        accusation.setAccusationStatus(AccusationStatus.MAKING_ACCUSATION);
        accusation.setCharacter(Character.COLONEL_MUSTARD);
        accusation.setRoom(Room.BILLIARD_ROOM);
        accusation.setWeapon(Weapon.CANDLESTICK);
        return accusation;
    }

    private Suggestion makeSuggestionChoice(Suggestion options)
    {
        System.out.println("game offers suggestion " + options);
        System.out.println();
        Suggestion suggestion = new Suggestion();
        suggestion.setSuggestionAction(SuggestionStatus.MAKING_SUGGESTION);
        suggestion.setCharacter(Character.MISS_SCARLET);
        suggestion.setRoom(Room.BILLIARD_ROOM);
        suggestion.setWeapon(Weapon.CANDLESTICK);
        return suggestion;
    }

    private MoveOptions makeMoveChoice(MoveOptions options)
    {
        System.out.println("game offers moves " + options);
        System.out.println();
        MoveOptions moveOptions = new MoveOptions();
        moveOptions.setValidMoves(Collections.singletonList(options.getValidMoves().get(0)));
        moveOptions.setTurnIndicator(ClueLessProtocolType.ACTIVE_PLAYER);
        return moveOptions;
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
