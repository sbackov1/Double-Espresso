package edu.jhu.espresso.server.domain;

public class HomeSquare {

    private String adjacentHallway;
    private CharacterNames character;

    public HomeSquare(CharacterNames Ch){
        this.character = Ch;

        switch(Ch) {
            case PROFESSOR_PLUM:
                this.adjacentHallway =  "H7";
            case MRS_PEACOCK:
                this.adjacentHallway = "H10";
            case MR_GREEN:
                this.adjacentHallway = "H5";
            case MRS_WHITE:
                this.adjacentHallway = "H6";
            case COLONEL_MUSTARD:
                this.adjacentHallway = "H9";
            case MISS_SCARLET:
                this.adjacentHallway = "H2";
        }
    }

    public String getAdjacentHallway() {
        return adjacentHallway;
    }

    public CharacterNames getCharacter(){
        return character;
    }



}
