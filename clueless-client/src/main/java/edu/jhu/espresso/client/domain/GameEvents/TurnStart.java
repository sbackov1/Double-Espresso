package edu.jhu.espresso.client.domain.GameEvents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.jhu.espresso.client.domain.ClueLessProtocolType;
import edu.jhu.espresso.client.domain.GamePieces.CharacterNames;
import edu.jhu.espresso.client.domain.GamePieces.LocationNames;

import java.util.Map;
import java.util.Optional;

public class TurnStart
{
    private ClueLessProtocolType clueLessProtocolType;
    private Map<CharacterNames, LocationNames> locationNamesMap;
    private String announcement;
    private CharacterNames characterMovedFromSuggestion;

    public void setClueLessProtocolType(ClueLessProtocolType clueLessProtocolType)
    {
        this.clueLessProtocolType = clueLessProtocolType;
    }

    public ClueLessProtocolType getClueLessProtocolType()
    {
        return clueLessProtocolType;
    }

    public Map<CharacterNames, LocationNames> getLocationNamesMap()
    {
        return locationNamesMap;
    }

    public void setLocationNamesMap(Map<CharacterNames, LocationNames> locationNamesMap)
    {
        this.locationNamesMap = locationNamesMap;
    }

    public String getAnnouncement()
    {
        return announcement;
    }

    public void setAnnouncement(String announcement)
    {
        this.announcement = announcement;
    }

    public void setCharacterMovedFromSuggestion(CharacterNames characterMovedFromSuggestion)
    {
        this.characterMovedFromSuggestion = characterMovedFromSuggestion;
    }

    public CharacterNames getCharacterMovedFromSuggestion()
    {
        return characterMovedFromSuggestion;
    }

    @JsonIgnore
    public Optional<CharacterNames> getCharacterMovedFromSuggestionOptional()
    {
        return Optional.ofNullable(characterMovedFromSuggestion);
    }
}
