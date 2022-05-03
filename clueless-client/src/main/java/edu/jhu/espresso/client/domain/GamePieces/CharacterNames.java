package edu.jhu.espresso.client.domain.GamePieces;


public enum CharacterNames
{
    PROFESSOR_PLUM,
    MRS_WHITE,
    MR_GREEN,
    MRS_PEACOCK,
    MISS_SCARLET,
    COLONEL_MUSTARD;


    public String toString() {

        switch(this) {
            case PROFESSOR_PLUM:
                return "PROFESSOR_PLUM";
            case MRS_WHITE:
                return "MRS_WHITE";
            case MR_GREEN:
                return "MR_GREEN";
            case MRS_PEACOCK:
                return "MRS_PEACOCK";
            case MISS_SCARLET:
                return "MISS_SCARLET";
            case COLONEL_MUSTARD:
                return "COLONEL_MUSTARD";
        }
        return null;
        }
}


