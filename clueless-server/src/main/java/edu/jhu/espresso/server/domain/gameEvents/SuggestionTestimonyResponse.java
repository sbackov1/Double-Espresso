package edu.jhu.espresso.server.domain.gameEvents;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.jhu.espresso.server.domain.builder.SuggestionTestimonyResponseBuilder;

@JsonDeserialize(builder = SuggestionTestimonyResponseBuilder.class)
public class SuggestionTestimonyResponse
{
    private final String response;

    public SuggestionTestimonyResponse(String response)
    {
        this.response = response;
    }

    public String getResponse()
    {
        return response;
    }
}
