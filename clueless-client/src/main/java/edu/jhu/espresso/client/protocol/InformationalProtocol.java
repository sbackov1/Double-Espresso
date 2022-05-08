package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.GameEvents.TurnStart;
import edu.jhu.espresso.client.fx.GameboardController;

public class InformationalProtocol implements ClueLessProtocol
{
    private final GameboardController gameboardController;
    private final ClueLessClient client;

    public InformationalProtocol(GameboardController gameboardController, ClueLessClient client)
    {
        this.gameboardController = gameboardController;
        this.client = client;
    }

    @Override
    public void execute(TurnStart turnStart)
    {
        gameboardController.updateStatusBar(turnStart.getAnnouncement());

        turnStart.getCharacterMovedFromSuggestionOptional().ifPresent(name -> {
            if(client.getPlayer().getCharacter() == name)
            {
                client.getPlayer().setPulledFromSuggestion(true);
            }
        });
    }
}
