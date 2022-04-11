package edu.jhu.espresso.client.domain;

import java.util.ArrayList;

public final class Suggestion {
    private SuggestionStatus suggestionStatus;
    private Weapon weapon;
    private Room room;
    private Character character;

    private ArrayList<String> validCharacters;
    private ArrayList<String> validWeapons;

    public SuggestionStatus getSuggestionAction() {
        return suggestionStatus;
    }

    public void setSuggestionAction(SuggestionStatus suggestionStatus) {
        this.suggestionStatus = suggestionStatus;
    }

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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setCharacterString(String characterString) {
        this.character = Character.valueOf(characterString);
        System.out.println("\n" + this.character + " was selected.");
    }

    public void setWeaponString(String weaponString) {
        this.weapon = Weapon.valueOf(weaponString);
        System.out.println("\n" + this.weapon + " was selected.");
    }


    @Override
    public String toString() {
        return "Suggestion{" +
                "suggestionStatus=" + suggestionStatus +
                ", weapon=" + weapon +
                ", room=" + room +
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
        sugMenu.setTitle("*** Accusation Menu ***");
        sugMenu.addItem(new MenuItem("Select Character", this, "selectCharacter"));
        sugMenu.addItem(new MenuItem("Select Weapon", this, "selectWeapon"));
        sugMenu.addItem(new MenuItem("Finalize Suggestion and Check Players", this, "printToString", null));

        sugMenu.execute();
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
}
