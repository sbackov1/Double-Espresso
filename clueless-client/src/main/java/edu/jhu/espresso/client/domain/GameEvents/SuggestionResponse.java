package edu.jhu.espresso.client.domain.GameEvents;

import java.util.ArrayList;
import java.util.List;

import edu.jhu.espresso.client.domain.GamePieces.*;
import edu.jhu.espresso.client.domain.Menus.Menu;
import edu.jhu.espresso.client.domain.Menus.MenuItem;
import edu.jhu.espresso.client.domain.GamePieces.Weapon;
import edu.jhu.espresso.client.domain.GamePieces.WeaponCard;
import org.apache.commons.lang3.EnumUtils;


public class SuggestionResponse {

    private SuggestionStatus suggestionStatus;
    private Card responseCard;
    private List<Card> validCards;
    private boolean selected = false;

    public void setValidCards(List<Card> validCards) {
        this.validCards = validCards;
    }


    public SuggestionStatus getSuggestionAction() {
        return suggestionStatus;
    }

    public void setSuggestionAction(SuggestionStatus suggestionStatus) {
        this.suggestionStatus = suggestionStatus;
    }

    public List<Card> getValidCards() {
        return validCards;
    }

    public void setValidCharacters(ArrayList<Card> validCards) {
        this.validCards = validCards;


    }

    public void setCardString(String cardString) {
        if (EnumUtils.isValidEnum(CharacterNames.class, cardString)) {
            this.responseCard = new CharacterCard(CharacterNames.valueOf(cardString).toString());
            System.out.println("\n" + this.responseCard.getType() + ": " + this.responseCard.getName() + " was selected.");
            this.suggestionStatus = SuggestionStatus.PROVING_SUGGESTION_FALSE;
        } else if (EnumUtils.isValidEnum(RoomNames.class, cardString)) {
            this.responseCard = new RoomCard(RoomNames.valueOf(cardString).toString());
            System.out.println("\n" + this.responseCard.getType() + ": " + this.responseCard.getName() + " was selected.");
            this.suggestionStatus = SuggestionStatus.PROVING_SUGGESTION_FALSE;
        } else if (EnumUtils.isValidEnum(Weapon.class, cardString)) {
            this.responseCard = new WeaponCard(Weapon.valueOf(cardString).toString());
            System.out.println("\n" + this.responseCard.getType() + ": " + this.responseCard.getName() + " was selected.");
            this.suggestionStatus = SuggestionStatus.PROVING_SUGGESTION_FALSE;
        }
    }

    public void setCantDisprove() {
        this.suggestionStatus = SuggestionStatus.CANNOT_DISPROVE;
        this.printToString();
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

                String cardName = validCard.getName();
                sugMenu.addItem(new MenuItem(validCard.getType() + ":" + cardName, this, "setCardString", cardName));
            }

            sugMenu.execute();
            //selected = true;
    }

    public Card getResponseCard()
    {
        return responseCard;
    }

    public String getResponseValue()
    {
        return responseCard == null ? "" : responseCard.getName();
    }
}
