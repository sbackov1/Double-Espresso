package edu.jhu.espresso.server.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.jhu.espresso.server.domain.builder.TurnStartBuilder;
import edu.jhu.espresso.server.domain.gamepieces.CharacterNames;
import edu.jhu.espresso.server.domain.gamepieces.LocationNames;

import java.util.Map;

@JsonDeserialize(builder = TurnStartBuilder.class)
public class TurnStart
{
    private final ClueLessProtocolType clueLessProtocolType;
    private final Map<CharacterNames, LocationNames> locationNamesMap;
    private final String announcement;
    private final CharacterNames characterMovedFromSuggestion;

    public TurnStart(ClueLessProtocolType clueLessProtocolType, Map<CharacterNames, LocationNames> locationNamesMap, String announcement, CharacterNames characterMovedFromSuggestion)
    {
        this.clueLessProtocolType = clueLessProtocolType;
        this.locationNamesMap = locationNamesMap;
        this.announcement = announcement;
        this.characterMovedFromSuggestion = characterMovedFromSuggestion;
    }

    public ClueLessProtocolType getClueLessProtocolType()
    {
        return clueLessProtocolType;
    }

    public Map<CharacterNames, LocationNames> getLocationNamesMap()
    {
        return locationNamesMap;
    }

    public String getAnnouncement()
    {
        return announcement;
    }

    public CharacterNames getCharacterMovedFromSuggestion()
    {
        return characterMovedFromSuggestion;
    }
}
