package edu.jhu.espresso.client.domain.GamePieces;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.Arrays;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
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
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Study", "Hall"));
            case "H2":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Hall", "Lounge"));
            case "H3":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Library", "Billiard Room"));
            case "H4":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Billiard Room", "Dining Room"));
            case "H5":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Conservatory", "Ballroom"));
            case "H6":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Ballroom", "Kitchen"));
            case "H7":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Study", "Library"));
            case "H8":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Hall", "Billiard Room"));
            case "H9":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Lounge", "Dining Room"));
            case "H10":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Library", "Conservatory"));
            case "H11":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Billiard Room", "Ballroom"));
            case "H12":
                this.possibleDestinations = new ArrayList<String>(Arrays.asList("Dining Room", "Kitchen"));
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
