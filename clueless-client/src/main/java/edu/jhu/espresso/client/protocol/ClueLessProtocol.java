package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.domain.GameEvents.TurnStart;

/**
 * represents some protocol the client will run on a game state, presumably
 * one it recently received from the server.
 */
@FunctionalInterface
public interface ClueLessProtocol
{
    void execute(TurnStart turnStart);
}
