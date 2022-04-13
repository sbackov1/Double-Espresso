package edu.jhu.espresso.server.domain;

import java.util.ArrayList;

public interface Location {

    String locationName = null;
    boolean isFull = false;
    ArrayList<Location> possibleDestinations = new ArrayList<>();

    public ArrayList<String> getPossibleDestinations();
    public String getLocationName();
    public boolean isFull();
    public void setFull(boolean set);
}


