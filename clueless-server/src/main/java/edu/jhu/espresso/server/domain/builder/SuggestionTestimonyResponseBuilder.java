package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.domain.gameEvents.SuggestionTestimonyResponse;

public final class SuggestionTestimonyResponseBuilder
{
    private String response;

    private SuggestionTestimonyResponseBuilder()
    {
    }

    public static SuggestionTestimonyResponseBuilder aSuggestionTestimonyResponse()
    {
        return new SuggestionTestimonyResponseBuilder();
    }

    public SuggestionTestimonyResponseBuilder withResponse(String response)
    {
        this.response = response;
        return this;
    }

    public SuggestionTestimonyResponse build()
    {
        return new SuggestionTestimonyResponse(response);
    }
}
