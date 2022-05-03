package edu.jhu.espresso.client.domain.GamePieces;

public enum LocationNames
{
    PROFESSOR_PLUM_HS,
    MRS_PEACOCK_HS,
    MR_GREEN_HS,
    MRS_WHITE_HS,
    COLONEL_MUSTARD_HS,
    MISS_SCARLET_HS,
    KITCHEN,
    HALL,
    BALLROOM,
    CONSERVATORY,
    DINING_ROOM,
    CELLAR,
    BILLIARD_ROOM,
    LIBRARY,
    LOUNGE,
    STUDY,
    HALLWAY1,
    HALLWAY2,
    HALLWAY3,
    HALLWAY4,
    HALLWAY5,
    HALLWAY6,
    HALLWAY7,
    HALLWAY8,
    HALLWAY9,
    HALLWAY10,
    HALLWAY11,
    HALLWAY12;

    public static LocationNames fromStringName(String name)
    {
        LocationNames result;
        switch (name)
        {
            case "H1":
                result = HALLWAY1;
                break;
            case "H2":
                result = HALLWAY2;
                break;
            case "H3":
                result = HALLWAY3;
                break;
            case "H4":
                result = HALLWAY4;
                break;
            case "H5":
                result = HALLWAY5;
                break;
            case "H6":
                result = HALLWAY6;
                break;
            case "H7":
                result = HALLWAY7;
                break;
            case "H8":
                result = HALLWAY8;
                break;
            case "H9":
                result = HALLWAY9;
                break;
            case "H10":
                result = HALLWAY10;
                break;
            case "H11":
                result = HALLWAY11;
                break;
            case "H12":
                result = HALLWAY12;
                break;
            case "LOUNGE":
                result = LOUNGE;
                break;
            case "STUDY":
                result = STUDY;
                break;
            case "HALL":
                result = HALL;
                break;
            case "LIBRARY":
                result = LIBRARY;
                break;
            case "BILLIARD_ROOM":
                result = BILLIARD_ROOM;
                break;
            case "DINING_ROOM":
                result = DINING_ROOM;
                break;
            case "CONSERVATORY":
                result = CONSERVATORY;
                break;
            case "BALLROOM":
                result = BALLROOM;
                break;
            case "KITCHEN":
                result = KITCHEN;
                break;
            case "CELLAR":
                result = CELLAR;
            case "PROFESSOR_PLUM_HS":
                result = PROFESSOR_PLUM_HS;
                break;
            case "MRS_PEACOCK_HS":
                result = MRS_PEACOCK_HS;
                break;
            case "MR_GREEN_HS":
                result = MR_GREEN_HS;
                break;
            case "MRS_WHITE_HS":
                result = MRS_WHITE_HS;
                break;
            case "COLONEL_MUSTARD_HS":
                result = COLONEL_MUSTARD_HS;
                break;
            case "MISS_SCARLET_HS":
                result = MISS_SCARLET_HS;
                break;
            default:
                throw new IllegalArgumentException(name + " is not a valid location name");
        }
        return result;
    }

}
