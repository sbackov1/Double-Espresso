package edu.jhu.espresso.client.domain.GamePieces;


import edu.jhu.espresso.client.fx.GameboardController;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.shape.Circle;

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

    public Circle getCircleFromBoard(GameboardController gameboardController)
    {
        switch (this)
        {
            case MISS_SCARLET:
                return gameboardController.MISS_SCARLET;
            case PROFESSOR_PLUM:
                return gameboardController.PROFESSOR_PLUM;
            case MRS_PEACOCK:
                return gameboardController.MRS_PEACOCK;
            case MR_GREEN:
                return gameboardController.MR_GREEN;
            case COLONEL_MUSTARD:
                return gameboardController.COLONEL_MUSTARD;
            case MRS_WHITE:
                return gameboardController.MRS_WHITE;
            default:
                throw new IllegalArgumentException(this + " does not have a cirle on the gameboard");
        }
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


