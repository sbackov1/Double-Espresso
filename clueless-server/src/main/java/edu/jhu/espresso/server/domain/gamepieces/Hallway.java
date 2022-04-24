package edu.jhu.espresso.server.domain.gamepieces;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.*;

@JsonTypeName("Hallway")
public class Hallway implements Location {

    private final String locationName;
    ArrayList<String> possibleDestinations;
    private boolean isFull;

    // Pass name, adjacent room list to instantiate.
    public Hallway(String name){
        this.locationName = name;
        this.isFull = false;

        switch(name) {
            case "H1":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("STUDY", "HALL"));
            case "H2":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("HALL", "LOUNGE"));
            case "H3":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("LIBRARY", "BILLIARD_ROOM"));
            case "H4":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("BILLIARD_ROOM", "DINING_ROOM"));
            case "H5":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("CONSERVATORY", "BALLROOM"));
            case "H6":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("BALLROOM", "KITCHEN"));
            case "H7":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("STUDY", "LIBRARY"));
            case "H8":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("HALL", "BILLIARD_ROOM"));
            case "H9":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("LOUNGE", "DINING_ROOM"));
            case "H10":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("LIBRARY", "CONSERVATORY"));
            case "H11":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("BILLIARD_ROOM", "BALLROOM"));
            case "H12":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("DINING_ROOM", "KITCHEN"));
        }


        }


    public String getLocationName() {
        return locationName;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }


    public ArrayList<String> getPossibleDestinations() {
        ArrayList<String> retVals = new ArrayList<String>(this.possibleDestinations);
        return retVals;
    }
}
