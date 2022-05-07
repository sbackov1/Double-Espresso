package edu.jhu.espresso.server.domain.gamepieces;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.Arrays;

@JsonTypeName("Room")
public class Room implements Location {
    private final String LocationName;
    private final boolean isFull;
    private ArrayList<String> possibleDestinations;

    private RoomNames roomName;

    public Room(RoomNames name){
        this.LocationName = name.name();
        this.roomName = name;
        this.isFull = false;

        this.possibleDestinations = null;

        switch(name) {
            case LOUNGE:
                this.possibleDestinations = new ArrayList<>(Arrays.asList("CONSERVATORY", "H2", "H9"));
                break;
            case STUDY:
                this.possibleDestinations = new ArrayList<>(Arrays.asList("KITCHEN", "H1", "H7"));
                break;
            case HALL:
                this.possibleDestinations = new ArrayList<>(Arrays.asList("H1", "H2", "H8"));
                break;
            case LIBRARY:
                this.possibleDestinations = new ArrayList<>(Arrays.asList("H3", "H7", "H10"));
                break;
            case BILLIARD_ROOM:
                this.possibleDestinations = new ArrayList<>(Arrays.asList("H3", "H4", "H8", "H11"));
                break;
            case DINING_ROOM:
                this.possibleDestinations = new ArrayList<>(Arrays.asList("H4", "H9", "H12"));
                break;
            case CONSERVATORY:
                this.possibleDestinations = new ArrayList<>(Arrays.asList("LOUNGE", "H5", "H10"));
                break;
            case BALLROOM:
                this.possibleDestinations = new ArrayList<>(Arrays.asList("H5", "H6", "H11"));
                break;
            case KITCHEN:
                this.possibleDestinations = new ArrayList<>(Arrays.asList("STUDY", "H6", "H12"));
                break;
        }

    }

    public String getLocationName() {
        return LocationName;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(){};
    public void setEmpty(){};

    public ArrayList<String> getPossibleDestinations() {
        return possibleDestinations;
    }

    public RoomNames getRoomName() {
        return roomName;
    }
}


