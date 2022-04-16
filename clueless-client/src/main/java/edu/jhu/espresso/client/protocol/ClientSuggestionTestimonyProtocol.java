package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.*;

import java.util.ArrayList;
import java.util.Arrays;

class ClientSuggestionTestimonyProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;

    public ClientSuggestionTestimonyProtocol(ClueLessClient client)
    {
        this.client = client;
    }

    @Override
    public void execute(TurnStart turnStart)
    {
        Suggestion suggestion = client.waitForResponse(Suggestion.class);
        SuggestionResponse suggestionResponse = new SuggestionResponse();

        ArrayList<Card> clientHandCards = client.getPlayer().getNotebook().getHandCards();

        clientHandCards.retainAll(buildCardsFromCaseDetails(suggestion.getCaseDetails()));

        suggestionResponse.setValidCards(clientHandCards);
        suggestionResponse.mainSugMenu();

        suggestion.setSuggestionStatus(suggestionResponse.getSuggestionAction());
        suggestion.setResponseValue(suggestionResponse.getResponseValue());
        client.write(suggestion);
    }

    ArrayList<Card> buildCardsFromCaseDetails(CaseDetails caseDetails)
    {
        RoomCard roomCard = new RoomCard(caseDetails.getRoom().name());
        WeaponCard weaponCard = new WeaponCard(caseDetails.getWeapon().name());
        CharacterCard characterCard = new CharacterCard(caseDetails.getCharacterNames().name());

        return new ArrayList<>(
                Arrays.asList(
                        roomCard,
                        weaponCard,
                        characterCard
                )
        );
    }
}
