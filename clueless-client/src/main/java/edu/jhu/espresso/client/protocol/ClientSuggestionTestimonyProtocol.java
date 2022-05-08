package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameEvents.Suggestion;
import edu.jhu.espresso.client.domain.GameEvents.SuggestionResponse;
import edu.jhu.espresso.client.domain.GameEvents.TurnStart;
import edu.jhu.espresso.client.domain.GamePieces.*;
import edu.jhu.espresso.client.fx.GameboardController;
import edu.jhu.espresso.client.fx.GameboardControllerStatus;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Arrays;

class ClientSuggestionTestimonyProtocol implements ClueLessProtocol
{
    private final ClueLessClient client;
    private final GameboardController gameboardController;

    public ClientSuggestionTestimonyProtocol(ClueLessClient client, GameboardController gameboardController)
    {
        this.client = client;
        this.gameboardController = gameboardController;
    }

    @Override
    public void execute(TurnStart turnStart)
    {
        Suggestion suggestion = client.waitForResponse(Suggestion.class);

        Platform.runLater(() -> gameboardController.disproveSuggestionWindow(buildCardsFromCaseDetails(suggestion)));

        GameboardControllerStatus status = gameboardController.getStatusFuture().join();
        if(status != GameboardControllerStatus.SUGGESTION_RESPONSE)
        {
            throw new IllegalStateException("Expected a suggestion response");
        }

        gameboardController.resetStatus();

        SuggestionResponse suggestionResponse = gameboardController.getSuggestionResponse();

        suggestion.setSuggestionStatus(suggestionResponse.getSuggestionAction());
        suggestion.setResponseValue(suggestionResponse.getResponseValue());
        client.write(suggestion);
    }

    ArrayList<Card> buildCardsFromCaseDetails(Suggestion suggestion)
    {
        RoomCard roomCard = new RoomCard(suggestion.getRoomNames().name());
        WeaponCard weaponCard = new WeaponCard(suggestion.getWeapon().name());
        CharacterCard characterCard = new CharacterCard(suggestion.getCharacter().name());

        return new ArrayList<>(
                Arrays.asList(
                        roomCard,
                        weaponCard,
                        characterCard
                )
        );
    }
}
