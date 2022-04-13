package edu.jhu.espresso.client.domain;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    Menu mainMenu = new Menu();
    ArrayList<String> legalMoves = new ArrayList<>();
    private static Menu mainSubMenu;

    MoveOptions moveOptions = new MoveOptions();
    Suggestion suggestion = new Suggestion();
    Accusation accusation = new Accusation();

    public List<String> getLegalMoves()
    {
        return legalMoves;
    }

    public void setLegalMoves(ArrayList<String> legalMoves)
    {
        this.legalMoves = legalMoves;
    }

    public void mainMenu ()
    {
        mainMenu.setTitle("*** Possible Actions ***");
        mainMenu.addItem(new MenuItem("Move Character", this, "moveCharacter"));
        mainMenu.addItem(new MenuItem("Make Suggestion", this, "makeSuggestion"));
        mainMenu.addItem(new MenuItem("Make Accusation", this, "makeAccusation"));
        mainMenu.addItem(new MenuItem("View Notebook", this, "openNotebook"));
        mainMenu.execute();
    }

    public void moveCharacter()
    {
        ArrayList<String> ValidMoves = new ArrayList<>();
        ValidMoves.add("HALLWAY5");
        ValidMoves.add("HALLWAY6");
        ValidMoves.add("HALLWAY11");
        moveOptions.setValidMoves(ValidMoves);
        moveOptions.mainMoveMenu();
    }

    public void makeSuggestion()
    {
        ArrayList<String> validChars = new ArrayList<>();
        validChars.add("MISS_SCARLET");
        validChars.add("PROFESSOR_PLUM");

        ArrayList<String> validWeapons = new ArrayList<>();
        validWeapons.add("REVOLVER");
        validWeapons.add("WRENCH");

        suggestion.setValidCharacters(validChars);
        suggestion.setValidWeapons(validWeapons);
        suggestion.mainSugMenu();
    }

    public void makeAccusation()
    {
        ArrayList<String> validChars = new ArrayList<>();
        validChars.add("MISS_SCARLET");
        validChars.add("PROFESSOR_PLUM");

        ArrayList<String> validRooms = new ArrayList<>();
        validRooms.add("KITCHEN");
        validRooms.add("CELLAR");

        ArrayList<String> validWeapons = new ArrayList<>();
        validWeapons.add("REVOLVER");
        validWeapons.add("WRENCH");

        accusation.setValidCharacters(validChars);
        accusation.setValidRooms(validRooms);
        accusation.setValidWeapons(validWeapons);
        accusation.mainAccMenu();
    }

    public void openNotebook()
    {
        System.out.println("This is a placeholder for the player notebook.");
    }
}
