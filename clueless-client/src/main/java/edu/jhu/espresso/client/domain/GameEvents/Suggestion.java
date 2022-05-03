package edu.jhu.espresso.client.domain.GameEvents;

import edu.jhu.espresso.client.domain.GamePieces.CaseDetails;
import edu.jhu.espresso.client.domain.GamePieces.CharacterNames;
import edu.jhu.espresso.client.domain.GamePieces.RoomNames;
import edu.jhu.espresso.client.domain.Menus.Menu;
import edu.jhu.espresso.client.domain.Menus.MenuItem;
import edu.jhu.espresso.client.domain.GamePieces.Weapon;

import java.util.ArrayList;

public final class Suggestion {
    private SuggestionStatus suggestionStatus;
    private Weapon weapon;
    private RoomNames roomNames;
    private CharacterNames character;
    private CaseDetails caseDetails;
    private ArrayList<String> validCharacters;
    private ArrayList<String> validWeapons;
    private String responseValue;

    public ArrayList<String> getValidCharacters() {
        return validCharacters;
    }

    public void setValidCharacters(ArrayList<String> validCharacters) {
        this.validCharacters = validCharacters;
    }

    public ArrayList<String> getValidWeapons() {
        return validWeapons;
    }

    public void setValidWeapons(ArrayList<String> validWeapons) {
        this.validWeapons = validWeapons;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public CharacterNames getCharacter() {
        return character;
    }

    public void setCharacter(CharacterNames character) {
        this.character = character;
    }

    public void setCharacterString(String characterString) {
        this.character = CharacterNames.valueOf(characterString);
        System.out.println("\n" + this.character + " was selected.");
    }

    public void setWeaponString(String weaponString) {
        this.weapon = Weapon.valueOf(weaponString);
        System.out.println("\n" + this.weapon + " was selected.");
    }

    public SuggestionStatus getSuggestionStatus()
    {
        return suggestionStatus;
    }

    public void setSuggestionStatus(SuggestionStatus suggestionStatus)
    {
        this.suggestionStatus = suggestionStatus;
    }

    public RoomNames getRoomNames()
    {
        return roomNames;
    }

    public void setRoomNames(RoomNames roomNames)
    {
        this.roomNames = roomNames;
    }

    public CaseDetails getCaseDetails()
    {
        return caseDetails;
    }

    public void setCaseDetails(CaseDetails caseDetails)
    {
        this.caseDetails = caseDetails;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "suggestionStatus=" + suggestionStatus +
                ", weapon=" + weapon +
                ", room=" + roomNames +
                ", character=" + character +
                '}';
    }

    public void printToString() {

        this.suggestionStatus = SuggestionStatus.MAKING_SUGGESTION;
        String output = toString();
        System.out.println("\n" + output + " is the message to be transmitted.");
    }

    public void mainSugMenu () {

        Menu sugMenu = new Menu();
        sugMenu.setTitle("*** Suggestion Menu ***");
        sugMenu.addItem(new MenuItem("Select Character", this, "selectCharacter"));
        sugMenu.addItem(new MenuItem("Select Weapon", this, "selectWeapon"));
        sugMenu.addItem(new MenuItem("Finalize Suggestion and Check Players", this, "printToString", null));

        sugMenu.execute();
        //return sugMenu;
    }

    public void selectCharacter() {
        Menu sugMenu = new Menu();
        sugMenu.setTitle("*** Select Character ***");

        for (String validCharacter : this.validCharacters) {

            sugMenu.addItem(new MenuItem(validCharacter,this, "setCharacterString", validCharacter));

        }
        sugMenu.execute();

    }

    public void selectWeapon() {
        Menu sugMenu = new Menu();
        sugMenu.setTitle("*** Select Weapon ***");

        for (String validWeapon : this.validWeapons) {

            sugMenu.addItem(new MenuItem(validWeapon,this, "setWeaponString", validWeapon));

        }
        sugMenu.execute();
    }

    public String getResponseValue()
    {
        return responseValue;
    }

    public void setResponseValue(String responseValue)
    {
        this.responseValue = responseValue;
    }
}
