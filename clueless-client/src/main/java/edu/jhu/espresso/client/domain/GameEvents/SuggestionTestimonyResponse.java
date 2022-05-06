package edu.jhu.espresso.client.domain.GameEvents;

import java.util.Optional;

public class SuggestionTestimonyResponse
{
    private String response;

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public Optional<String> optionalResponse()
    {
        return Optional.ofNullable(response);
    }

    @Override
    public String toString()
    {
        return "SuggestionTestimonyResponse{" +
                "response='" + response + '\'' +
                '}';
    }
}
