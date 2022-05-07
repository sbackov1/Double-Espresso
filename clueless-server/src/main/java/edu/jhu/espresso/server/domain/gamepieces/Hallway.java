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
                break;
            case "H2":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("HALL", "LOUNGE"));
                break;
            case "H3":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("LIBRARY", "BILLIARD_ROOM"));
                break;
            case "H4":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("BILLIARD_ROOM", "DINING_ROOM"));
                break;
            case "H5":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("CONSERVATORY", "BALLROOM"));
                break;
            case "H6":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("BALLROOM", "KITCHEN"));
                break;
            case "H7":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("STUDY", "LIBRARY"));
                break;
            case "H8":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("HALL", "BILLIARD_ROOM"));
                break;
            case "H9":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("LOUNGE", "DINING_ROOM"));
                break;
            case "H10":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("LIBRARY", "CONSERVATORY"));
                break;
            case "H11":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("BILLIARD_ROOM", "BALLROOM"));
                break;
            case "H12":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("DINING_ROOM", "KITCHEN"));
                break;
        }


        }


    public String getLocationName() {
        return locationName;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull() {
        isFull = true;
    }

    public void setEmpty() {isFull = false;}


    public ArrayList<String> getPossibleDestinations() {
        ArrayList<String> retVals = new ArrayList<String>(this.possibleDestinations);
        return retVals;
    }
}
