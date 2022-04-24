package edu.jhu.espresso.server.domain.gamepieces;

import edu.jhu.espresso.server.domain.gamepieces.Card;
import edu.jhu.espresso.server.domain.gamepieces.CardDeck;

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

    public void makeKnownCard(Card c){
        this.unknownCards.remove(c);
        this.knownCards.add(c);
        }

    public void makeHandCard(Card c){
        this.unknownCards.remove(c);
        this.handCards.add(c);
        }

    //Returns all cards in a notebook which can disprove the current suggestion.  If none are present, it returns null.
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
}
