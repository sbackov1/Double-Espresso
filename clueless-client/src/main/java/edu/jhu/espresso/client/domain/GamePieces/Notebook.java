package edu.jhu.espresso.client.domain.GamePieces;

import edu.jhu.espresso.client.domain.GamePieces.Card;
import edu.jhu.espresso.client.domain.GamePieces.CardDeck;

import java.util.ArrayList;

public class Notebook {
    private ArrayList<Card> handCards;
    private ArrayList<Card> knownCards;
    private ArrayList<Card> unknownCards;

    public Notebook(CardDeck cd) {
        this.unknownCards = (ArrayList<Card>) cd.getCardsList().clone();
        //Generate blank arrayLists of cards for hand cards and known cards.
        this.handCards = new ArrayList<Card>();
        this.knownCards = new ArrayList<Card>();
    }

    public void makeKnownCard(String cardName)
    {
        unknownCards.stream()
                .filter(card -> card.getName().equals(cardName))
                .findFirst()
                .ifPresent(this::makeKnownCard);
    }

    public void makeHandCard(String cardName)
    {
        unknownCards.stream()
                .filter(card -> card.getName().equals(cardName))
                .findFirst()
                .ifPresent(this::makeHandCard);
    }

    public void makeKnownCard(Card c){
        this.unknownCards.remove(c);
        this.knownCards.add(c);
    }

    public void makeHandCard(Card c){
        this.unknownCards.remove(c);
        this.handCards.add(c);
    }

    public ArrayList<Card> canDisproveSuggestion (ArrayList<Card> suggestionCards){

        //Use the following if we can pass in the exact same object - not sure how it will work with getting object references from Server.
        ArrayList<Card> retList = (ArrayList<Card>) suggestionCards.clone();
        retList.retainAll(this.handCards);

        return retList;
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public ArrayList<Card> getKnownCards() {
        return knownCards;
    }

    public ArrayList<Card> getUnknownCards() {
        return unknownCards;
    }

    @Override
    public String toString() {
        return "\n*** Notebook ***\n" +
                "HandCards:" +
                printCards(handCards) +
                "\n" + "Known Cards:" +
                printCards(knownCards);
//                +
//                "\n" + "Unknown Cards:\n" +
//                printCards(unknownCards) +
//                "\n";
    }

    public String printCards(ArrayList<Card> aCard) {

        String returnString = "";

        for (Card cards : aCard) {
            returnString += "\n" + cards.getType() + ": " + cards.getName();
        }
        returnString += "\n";
        return returnString;
    }
}