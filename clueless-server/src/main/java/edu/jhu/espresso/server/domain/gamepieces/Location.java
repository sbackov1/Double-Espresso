package edu.jhu.espresso.server.domain.gamepieces;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.util.ArrayList;

public interface Location
{
    String locationName = null;
    boolean isFull = false;
    ArrayList<Location> possibleDestinations = new ArrayList<>();

    ArrayList<String> getPossibleDestinations();
    String getLocationName();
    boolean isFull();
    void setFull();

    void setEmpty();



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
            case H1:
                return new Hallway("H1");
            case H2:
                return new Hallway("H2");
            case H3:
                return new Hallway("H3");
            case H4:
                return new Hallway("H4");
            case H5:
                return new Hallway("H5");
            case H6:
                return new Hallway("H6");
            case H7:
                return new Hallway("H7");
            case H8:
                return new Hallway("H8");
            case H9:
                return new Hallway("H9");
            case H10:
                return new Hallway("H10");
            case H11:
                return new Hallway("H11");
            case H12:
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


