package edu.jhu.espresso.server.domain.gamepieces;

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
    BILLIARD_ROOM,
    LIBRARY,
    LOUNGE,
    STUDY,
    H1,
    H2,
    H3,
    H4,
    H5,
    H6,
    H7,
    H8,
    H9,
    H10,
    H11,
    H12;

    public static LocationNames fromStringName(String name)
    {
        LocationNames result;
        switch (name)
        {
            case "H1":
                result = H1;
                break;
            case "H2":
                result = H2;
                break;
            case "H3":
                result = H3;
                break;
            case "H4":
                result = H4;
                break;
            case "H5":
                result = H5;
                break;
            case "H6":
                result = H6;
                break;
            case "H7":
                result = H7;
                break;
            case "H8":
                result = H8;
                break;
            case "H9":
                result = H9;
                break;
            case "H10":
                result = H10;
                break;
            case "H11":
                result = H11;
                break;
            case "H12":
                result = H12;
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

    public static String StringLocationTypeFromStringName(String thisName)
    {
        String result;
        switch (thisName)
        {
            case "H1":
            case "H2":
            case "H3":
            case "H4":
            case "H5":
            case "H6":
            case "H7":
            case "H8":
            case "H9":
            case "H10":
            case "H11":
            case "H12":
                result = "Hallway";
                break;
            case "LOUNGE":
            case "STUDY":
            case "HALL":
            case "LIBRARY":
            case "BILLIARD_ROOM":
            case "DINING_ROOM":
            case "CONSERVATORY":
            case "BALLROOM":
            case "KITCHEN":
            case "CELLAR":
                result = "Room";
                break;
            case "PROFESSOR_PLUM_HS":
            case "MRS_PEACOCK_HS":
            case "MR_GREEN_HS":
            case "MRS_WHITE_HS":
            case "COLONEL_MUSTARD_HS":
            case "MISS_SCARLET_HS":
                result = "HomeSquare";
                break;

            default:
                throw new IllegalArgumentException("Not a valid location name");
        }
        return result;
    }
}
