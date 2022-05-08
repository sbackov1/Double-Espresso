package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.domain.ClueLessProtocolType;
import edu.jhu.espresso.server.domain.TurnStart;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;
import edu.jhu.espresso.server.domain.gamepieces.LocationNames;

import java.util.Map;

public final class TurnStartBuilder
{
    private ClueLessProtocolType clueLessProtocolType;
    private Map<CharacterNames, LocationNames> locationNamesMap;
    private String announcement;
    private CharacterNames characterMovedFromSuggestion;

    private TurnStartBuilder()
    {
    }

    public static TurnStartBuilder aTurnStart()
    {
        return new TurnStartBuilder();
    }

    public TurnStartBuilder withClueLessProtocolType(ClueLessProtocolType clueLessProtocolType)
    {
        this.clueLessProtocolType = clueLessProtocolType;
        return this;
    }

    public TurnStartBuilder withLocationNamesMap(Map<CharacterNames, LocationNames> locationNamesMap)
    {
        this.locationNamesMap = locationNamesMap;
        return this;
    }

    public TurnStartBuilder withAnnouncement(String announcement)
    {
        this.announcement = announcement;
        return this;
    }

    public TurnStartBuilder withCharacterMovedFromSuggestion(CharacterNames characterMovedFromSuggestion)
    {
        this.characterMovedFromSuggestion = characterMovedFromSuggestion;
        return this;
    }

    public TurnStart build()
    {
        return new TurnStart(clueLessProtocolType, locationNamesMap, announcement, characterMovedFromSuggestion);
    }
}
