package edu.jhu.espresso;

public class Card {

    private String cardType;
    private String cardValue;

    public Card(String cardType, String cardValue) {
        this.cardType = cardType;
        this.cardValue = cardValue;
    }

    public Card() {
        this.cardType = "Weapon";
        this.cardValue = "Candlestick";
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }
}
