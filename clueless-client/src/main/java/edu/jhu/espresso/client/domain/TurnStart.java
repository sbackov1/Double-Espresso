package edu.jhu.espresso.client.domain;

import java.util.Map;

public class TurnStart
{
    private ClueLessProtocolType clueLessProtocolType;
    private Map<CharacterNames, LocationNames> locationNamesMap;
    private String announcement;

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
}
