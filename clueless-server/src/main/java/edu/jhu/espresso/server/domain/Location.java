package edu.jhu.espresso.server.domain;

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

    ArrayList<String> getPossibleDestinations();
    String getLocationName();
    boolean isFull();
    void setFull(boolean set);

    static Location fromLocationName(LocationNames locationNames)
    {
        switch (locationNames)
        {
            case PROFESSOR_PLUM_HS:
                return new HomeSquare(CharacterNames.PROFESSOR_PLUM);
            case MRS_PEACOCK_HS:
                return new HomeSquare(CharacterNames.MRS_PEACOCK);
            case MR_GREEN_HS:
                return new HomeSquare(CharacterNames.MR_GREEN);
            case MRS_WHITE_HS:
                return new HomeSquare(CharacterNames.MRS_WHITE);
            case COLONEL_MUSTARD_HS:
                return new HomeSquare(CharacterNames.COLONEL_MUSTARD);
            case MISS_SCARLET_HS:
                return new HomeSquare(CharacterNames.MISS_SCARLET);
            case KITCHEN:
                return new Room(RoomNames.KITCHEN);
            case HALL:
                return new Room(RoomNames.HALL);
            case BALLROOM:
                return new Room(RoomNames.BALLROOM);
            case CONSERVATORY:
                return new Room(RoomNames.CONSERVATORY);
            case DINING_ROOM:
                return new Room(RoomNames.DINING_ROOM);
            case BILLIARD_ROOM:
                return new Room(RoomNames.BILLIARD_ROOM);
            case LIBRARY:
                return new Room(RoomNames.LIBRARY);
            case LOUNGE:
                return new Room(RoomNames.LOUNGE);
            case STUDY:
                return new Room(RoomNames.STUDY);
            case HALLWAY1:
                return new Hallway("H1");
            case HALLWAY2:
                return new Hallway("H2");
            case HALLWAY3:
                return new Hallway("H3");
            case HALLWAY4:
                return new Hallway("H4");
            case HALLWAY5:
                return new Hallway("H5");
            case HALLWAY6:
                return new Hallway("H6");
            case HALLWAY7:
                return new Hallway("H7");
            case HALLWAY8:
                return new Hallway("H8");
            case HALLWAY9:
                return new Hallway("H9");
            case HALLWAY10:
                return new Hallway("H10");
            case HALLWAY11:
                return new Hallway("H11");
            case HALLWAY12:
                return new Hallway("H12");
            default:
                throw new IllegalArgumentException(locationNames + " is not a valid Location Name");
        }
    }

    static Location fromRoomNames(RoomNames roomNames)
    {
        switch (roomNames)
        {
            case KITCHEN:
                return new Room(RoomNames.KITCHEN);
            case HALL:
                return new Room(RoomNames.HALL);
            case BALLROOM:
                return new Room(RoomNames.BALLROOM);
            case CONSERVATORY:
                return new Room(RoomNames.CONSERVATORY);
            case DINING_ROOM:
                return new Room(RoomNames.DINING_ROOM);
            case BILLIARD_ROOM:
                return new Room(RoomNames.BILLIARD_ROOM);
            case LIBRARY:
                return new Room(RoomNames.LIBRARY);
            case LOUNGE:
                return new Room(RoomNames.LOUNGE);
            case STUDY:
                return new Room(RoomNames.STUDY);
            default:
                throw new IllegalArgumentException(roomNames + " is not a valid room name");
        }
    }
}


