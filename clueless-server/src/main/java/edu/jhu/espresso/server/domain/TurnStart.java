package edu.jhu.espresso.server.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.jhu.espresso.server.domain.builder.TurnStartBuilder;

import java.util.Map;

@JsonDeserialize(builder = TurnStartBuilder.class)
public class TurnStart
{
    private final ClueLessProtocolType clueLessProtocolType;

    public TurnStart(ClueLessProtocolType clueLessProtocolType)
    {
        this.clueLessProtocolType = clueLessProtocolType;
    }

    public ClueLessProtocolType getClueLessProtocolType()
    {
        return clueLessProtocolType;
    }

}
