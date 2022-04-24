package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.domain.GameEvents.TurnStart;

public class InformationalProtocol implements ClueLessProtocol
{
    @Override
    public void execute(TurnStart turnStart)
    {
        System.out.println(turnStart.getAnnouncement());
    }
}
