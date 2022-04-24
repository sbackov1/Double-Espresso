package edu.jhu.espresso.client.domain.GamePieces;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.util.ArrayList;

@JsonSubTypes({
        @JsonSubTypes.Type(value = Hallway.class, name = "Hallway"),
        @JsonSubTypes.Type(value = Room.class, name = "Room"),
        @JsonSubTypes.Type(value = HomeSquare.class, name = "HomeSquare")
})
public interface Location
{
    String locationName = null;
    boolean isFull = false;
    ArrayList<Location> possibleDestinations = new ArrayList<>();

    public ArrayList<String> getPossibleDestinations();
}


