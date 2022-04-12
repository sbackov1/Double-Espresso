package edu.jhu.espresso.server.domain;

import java.util.ArrayList;

public class Notebook {
    private ArrayList<Card> handCards;
    private ArrayList<Card> knownCards;
    private ArrayList<Card> unknownCards;

    public Notebook(CardDeck c) {
        ArrayList<Card> unknownCards = c.getCardsList();
        //Generate blank arrayLists of cards for hand cards and known cards.
        ArrayList<Card> handCards = new ArrayList<Card>();
        ArrayList<Card> knownCards = new ArrayList<Card>();
    }

    public void makeKnownCard(Card c){
        unknownCards.remove(c);
        knownCards.add(c);
        }

    public void makeHandCard(Card c){
        unknownCards.remove(c);
        handCards.add(c);
        }

    //Returns all cards in a notebook which can disprove the current suggestion.  If none are present, it returns null.
    public ArrayList<Card> canDisproveSuggestion (ArrayList<Card> suggestionCards){
        suggestionCards.retainAll(this.handCards);
        return suggestionCards;
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
}
