package edu.jhu.espresso.client.domain;

import java.util.ArrayList;

public class SuggestionResponse {

    private SuggestionStatus suggestionStatus;
    private WeaponCard weaponCard;
    private RoomCard roomCard;
    private CharacterCard characterCard;
    private Card responseCard;

    public void setValidCards(ArrayList<Card> validCards) {
        this.validCards = validCards;
    }

    private ArrayList<Card> validCards;

    public SuggestionStatus getSuggestionAction() {
        return suggestionStatus;
    }

    public void setSuggestionAction(SuggestionStatus suggestionStatus) {
        this.suggestionStatus = suggestionStatus;
    }

    public ArrayList<Card> getValidCards() {
        return validCards;
    }

    public void setValidCharacters(ArrayList<Card> validCards) {
        this.validCards = validCards;

    }

    public void setCardString(String cardString) {
        if (cardString.equals(CharacterNames.valueOf(cardString))) {
            this.responseCard = new CharacterCard(CharacterNames.valueOf(cardString).toString());
            System.out.println("\n" + this.characterCard.getType() + ": " + this.characterCard.getName() + " was selected.");
            this.suggestionStatus = SuggestionStatus.PROVING_SUGGESTION_FALSE;
        } else if (cardString.equals(RoomNames.valueOf(cardString))) {
            this.responseCard = new RoomCard(RoomNames.valueOf(cardString).toString());
            System.out.println("\n" + this.roomCard.getType() + ": " + this.roomCard.getName() + " was selected.");
            this.suggestionStatus = SuggestionStatus.PROVING_SUGGESTION_FALSE;
        } else if (cardString.equals(Weapon.valueOf(cardString))) {
            this.responseCard = new WeaponCard(Weapon.valueOf(cardString).toString());
            System.out.println("\n" + this.weaponCard.getType() + ": " + this.weaponCard.getName() + " was selected.");
            this.suggestionStatus = SuggestionStatus.PROVING_SUGGESTION_FALSE;
        }
    }

    public void setCantDisprove() {
        this.suggestionStatus = SuggestionStatus.CANNOT_DISPROVE;
    }
    public void printToString() {

        //this.suggestionStatus = SuggestionStatus.MAKING_SUGGESTION;
        String output = toString();
        System.out.println("\n" + output + " is the message to be transmitted.");
    }

    public void mainSugMenu () {
        Menu sugMenu = new Menu();
            sugMenu.setTitle("*** Suggestion Response Menu ***");
            sugMenu.addItem(new MenuItem("Select Card" , this, "selectCard"));
            sugMenu.addItem(new MenuItem("Can't Disprove Suggestion", this, "setCantDisprove"));
            sugMenu.addItem(new MenuItem("Submit Response", this, "printToString", null));

            sugMenu.execute();
}

    public void selectCard() {
        Menu sugMenu = new Menu();
        sugMenu.setTitle("*** Select Card ***");

        for (Card validCard : this.validCards) {

            sugMenu.addItem(new MenuItem(validCard.getType() + ":" + validCard.getName() ,this, "setCardString", "validCard.getName()"));
        }
        sugMenu.execute();

    }
}
