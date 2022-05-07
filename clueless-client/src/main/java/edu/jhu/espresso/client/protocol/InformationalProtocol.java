package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.domain.GameEvents.TurnStart;
import edu.jhu.espresso.client.fx.GameboardController;

public class InformationalProtocol implements ClueLessProtocol
{
    private final GameboardController gameboardController;

    public InformationalProtocol(GameboardController gameboardController)
    {
        this.gameboardController = gameboardController;
    }

    @Override
    public void execute(TurnStart turnStart)
    {
        gameboardController.updateStatusBar(turnStart.getAnnouncement());
    }
}
