package edu.jhu.espresso.server.domain.gamepieces;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("HomeSquare")
public class HomeSquare implements Location {

    private String adjacentHallway;
    private final CharacterNames character;
    private String locationName;

    public HomeSquare(CharacterNames Ch){
        this.character = Ch;

        switch(Ch) {
            case PROFESSOR_PLUM:
                this.adjacentHallway =  "H7";
                this.locationName = "PROFESSOR_PLUM_HS";
                break;
            case MRS_PEACOCK:
                this.adjacentHallway = "H10";
                this.locationName = "MRS_PEACOCK_HS";
                break;
            case MR_GREEN:
                this.adjacentHallway = "H5";
                this.locationName = "MR_GREEN_HS";
                break;
            case MRS_WHITE:
                this.adjacentHallway = "H6";
                this.locationName = "MRS_WHITE_HS";
                break;
            case COLONEL_MUSTARD:
                this.adjacentHallway = "H9";
                this.locationName = "COLONEL_MUSTARD_HS";
                break;
            case MISS_SCARLET:
                this.adjacentHallway = "H2";
                this.locationName = "MISS_SCARLET_HS";
                break;
        }
    }

    public String getAdjacentHallway() {
        return adjacentHallway;
    }

    public CharacterNames getCharacter(){
        return character;
    }

    @Override
    public ArrayList<String> getPossibleDestinations() {
        ArrayList<String> retList = new ArrayList<>();
        retList.add(this.adjacentHallway);
        return retList;
    }

    @Override
    public String getLocationName() {
        return locationName;
    }

    @Override
    public void setFull(){};

    public void setEmpty(){};

    @Override
    public boolean isFull(){return true;}
}
