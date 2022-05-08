package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.domain.GameEvents.TurnStart;
import edu.jhu.espresso.client.fx.GameboardController;

class WaitingPlayerProtocol implements ClueLessProtocol
{
    private final GameboardController gameboardController;

    public WaitingPlayerProtocol(GameboardController gameboardController)
    {
        this.gameboardController = gameboardController;
    }

    @Override
    public void execute(TurnStart turnStart)
    {
        gameboardController.setDisableForAllButtons(true);
    }
}
