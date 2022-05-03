package edu.jhu.espresso.client.domain.GamePieces;

public enum RoomNames
{
    KITCHEN,
    HALL,
    BALLROOM,
    CONSERVATORY,
    DINING_ROOM,
    BILLIARD_ROOM,
    LIBRARY,
    LOUNGE,
    STUDY;

    public String toString() {

        switch(this) {
            case KITCHEN: return "KITCHEN";
            case HALL: return "HALL";
            case BALLROOM:return "BALLROOM";
            case CONSERVATORY:return "CONSERVATORY";
            case DINING_ROOM: return "DINING_ROOM";
            case BILLIARD_ROOM: return "BILLIARD_ROOM";
            case LIBRARY: return"LIBRARY";
            case LOUNGE: return  "LOUNGE";
            case STUDY: return "STUDY";

        } return null;
    }
}
