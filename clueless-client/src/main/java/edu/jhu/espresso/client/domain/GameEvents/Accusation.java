package edu.jhu.espresso.client.domain.GameEvents;

import edu.jhu.espresso.client.domain.GamePieces.CaseDetails;
import edu.jhu.espresso.client.domain.GamePieces.Character;
import edu.jhu.espresso.client.domain.GamePieces.RoomNames;
import edu.jhu.espresso.client.domain.GamePieces.Weapon;
import edu.jhu.espresso.client.domain.Menus.Menu;
import edu.jhu.espresso.client.domain.Menus.MenuItem;

import java.util.ArrayList;

public final class Accusation
{
    private AccusationStatus accusationStatus;
    private CaseDetails caseDetails;
    private Weapon weapon;
    private RoomNames roomNames;
    private Character character;

    private ArrayList<String> validCharacters;
    private ArrayList<String> validRooms;
    private ArrayList<String> validWeapons;

    public void setValidCharacters(ArrayList<String> validCharacters) {
        this.validCharacters = validCharacters;
    }

    public void setValidRooms(ArrayList<String> validRooms) {
        this.validRooms = validRooms;
    }

    public void setValidWeapons(ArrayList<String> validWeapons) {
        this.validWeapons = validWeapons;
    }

    public CaseDetails getCaseDetails()
    {
        return caseDetails;
    }

    public void setCaseDetails(CaseDetails caseDetails)
    {
        this.caseDetails = caseDetails;
    }

    public AccusationStatus getAccusationStatus()
    {
        return accusationStatus;
    }

    public void setAccusationStatus(AccusationStatus accusationStatus)
    {
        this.accusationStatus = accusationStatus;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }

    public RoomNames getRoomNames()
    {
        return roomNames;
    }

    public void setRoomNames(RoomNames roomNames)
    {
        this.roomNames = roomNames;
    }

    public Character getCharacter()
    {
        return character;
    }

    public void setCharacter(Character character)
    {
        this.character = character;
    }

    public ArrayList<String> getValidCharacters()
    {
        return validCharacters;
    }

    public ArrayList<String> getValidRooms()
    {
        return validRooms;
    }

    public ArrayList<String> getValidWeapons()
    {
        return validWeapons;
    }

    public void setCharacterString(String characterString) {
        this.character = Character.valueOf(characterString);
        System.out.println("\n" + this.character + " was selected.");
    }

    public void setRoomString(String roomString) {
        this.roomNames = RoomNames.valueOf(roomString);
        System.out.println("\n" + this.roomNames + " was selected.");

    }

    public void setWeaponString(String weaponString) {
        this.weapon = Weapon.valueOf(weaponString);
        System.out.println("\n" + this.weapon + " was selected.");
    }



    @Override
    public String toString()
    {
        return "Accusation{" +
                "accusationStatus=" + accusationStatus +
                ", weapon=" + weapon +
                ", room=" + roomNames +
                ", character=" + character +
                '}';
    }

    public void printToString(){

        this.accusationStatus = AccusationStatus.MAKING_ACCUSATION;
        String output = toString();
        System.out.println("\n" + output + " is the message to be transmitted.");
}



    public void mainAccMenu () {

        Menu accMenu = new Menu();
        accMenu.setTitle("*** Accusation Menu ***");
        accMenu.addItem(new MenuItem("Select Character", this, "selectCharacter"));
        accMenu.addItem(new MenuItem("Select Room", this, "selectRoom"));
        accMenu.addItem(new MenuItem("Select Weapon", this, "selectWeapon"));
        accMenu.addItem(new MenuItem("Finalize Accusation and Check Case File", this, "printToString", null));
        //accMenu.setExitItem(new MenuItem("Cancel Accusation"));
        //cancel.setExitItem(true);
       // accMenu.addItem(cancel);

        //cancel.setExitItem(true);

        accMenu.execute();
    }

    public void selectCharacter() {
        Menu accMenu = new Menu();
        accMenu.setTitle("*** Select Character ***");

        for (String validCharacter : this.validCharacters) {

            accMenu.addItem(new MenuItem(validCharacter,this, "setCharacterString", validCharacter));
        }
        //accMenu.addItem(new MenuItem("Back"));
        accMenu.execute();

    }

    public void selectRoom() {
        Menu accMenu = new Menu();
        accMenu.setTitle("*** Select Room ***");

        for (String validRoom : this.validRooms) {

            accMenu.addItem(new MenuItem(validRoom,this, "setRoomString", validRoom));

        }
        //accMenu.addItem(new MenuItem("Back"));
        accMenu.execute();


    }

    public void selectWeapon() {
        Menu accMenu = new Menu();
        accMenu.setTitle("*** Select Weapon ***");

        for (String validWeapon : this.validWeapons) {

            accMenu.addItem(new MenuItem(validWeapon,this, "setWeaponString", validWeapon));

        }
        //accMenu.addItem(new MenuItem("Back"));
        accMenu.execute();

    }
}
