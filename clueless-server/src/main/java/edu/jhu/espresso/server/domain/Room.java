package edu.jhu.espresso.server.domain;
import java.util.*;

public class Room implements Location {
    private final String LocationName;
    private final boolean isFull;
    private ArrayList<String> possibleDestinations;

    public Room(String name){
        this.LocationName = name;
        this.isFull = false;

        this.possibleDestinations = null;

        switch(name) {
            case "Lounge":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Conservatory", "H2", "H9"));
            case "Study":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Kitchen", "H1", "H7"));
            case "Hall":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("H1", "H2", "H8"));
            case "Library":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("H3", "H7", "H10"));
            case "Billiard Room":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("H3", "H4", "H8", "H11"));
            case "Dining Room":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("H4", "H9", "H11"));
            case "Conservatory":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Lounge", "H5", "H10"));
            case "Ballroom":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("H5", "H6", "H11"));
            case "Kitchen":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Study", "H8", "H12"));
        }

    }

    public String getLocationName() {
        return LocationName;
    }

    public boolean isFull() {
        return isFull;
    }

    public ArrayList<String> getPossibleDestinations() {
        return possibleDestinations;
    }
}


