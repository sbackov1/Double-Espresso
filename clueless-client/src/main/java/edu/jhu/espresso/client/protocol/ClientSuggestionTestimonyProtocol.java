package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameEvents.Suggestion;
import edu.jhu.espresso.client.domain.GameEvents.SuggestionResponse;
import edu.jhu.espresso.client.domain.GameEvents.TurnStart;
import edu.jhu.espresso.client.domain.GamePieces.*;

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

        ArrayList<Card> possibleDisproveCards = new ArrayList<>(clientHandCards);

        possibleDisproveCards.retainAll(buildCardsFromCaseDetails(suggestion.getCaseDetails()));

        suggestionResponse.setValidCards(possibleDisproveCards);
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
