package edu.jhu.espresso.client.domain.GamePieces;


import javafx.geometry.HPos;
import javafx.geometry.VPos;

public enum CharacterNames
{
    PROFESSOR_PLUM(HPos.LEFT, VPos.BOTTOM),
    MRS_WHITE(HPos.CENTER, VPos.BOTTOM),
    MR_GREEN(HPos.CENTER, VPos.TOP),
    MRS_PEACOCK(HPos.RIGHT, VPos.TOP),
    MISS_SCARLET(HPos.LEFT, VPos.TOP),
    COLONEL_MUSTARD(HPos.RIGHT, VPos.BOTTOM);

    private final HPos horizontalPosition;
    private final VPos verticalPosition;

    public HPos getHorizontalPosition()
    {
        return horizontalPosition;
    }

    public VPos getVerticalPosition()
    {
        return verticalPosition;
    }

    CharacterNames(HPos horizontalPosition, VPos verticalPosition)
    {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
    }

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


