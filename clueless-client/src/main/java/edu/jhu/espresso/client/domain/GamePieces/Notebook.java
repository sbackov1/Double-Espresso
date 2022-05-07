package edu.jhu.espresso.client.domain.GamePieces;


import edu.jhu.espresso.client.domain.CardNotebookStatus;
import edu.jhu.espresso.client.domain.NotebookStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Notebook {
    private ArrayList<Card> handCards;
    private ArrayList<Card> knownCards;
    private ArrayList<Card> unknownCards;
    private List<Card> commonCards;

    private ObservableMap<Card, CardNotebookStatus> cardNotebookStatusMap = FXCollections.observableMap(new HashMap<>());

    public Notebook(CardDeck cd) {
        this.unknownCards = (ArrayList<Card>) cd.getCardsList().clone();
        //Generate blank arrayLists of cards for hand cards and known cards.
        this.handCards = new ArrayList<Card>();
        this.knownCards = new ArrayList<Card>();

        List<Card> allCards = new ArrayList<>(unknownCards);

        allCards.forEach(card -> cardNotebookStatusMap.put(card, new CardNotebookStatus(NotebookStatus.UNKNOWN)));
        updateCardNotebookStatuses();
    }

    private void updateCardNotebookStatuses()
    {
        for (Card card : handCards)
        {
            cardNotebookStatusMap.get(card).setNotebookStatus(NotebookStatus.HAND);
        }

        for (Card card : knownCards)
        {
            cardNotebookStatusMap.get(card).setNotebookStatus(NotebookStatus.KNOWN);
        }

        for (Card card : unknownCards)
        {
            cardNotebookStatusMap.get(card).setNotebookStatus(NotebookStatus.UNKNOWN);
        }
    }

    public void setCommonCards(List<Card> commonCards)
    {

    }

    public List<Card> getCommonCards()
    {
        return commonCards;
    }

    public void makeKnownCard(String cardName)
    {
        unknownCards.stream()
                .filter(card -> card.getName().equals(cardName))
                .findFirst()
                .ifPresent(this::makeKnownCard);

        updateCardNotebookStatuses();
    }

    public void makeHandCard(String cardName)
    {
        unknownCards.stream()
                .filter(card -> card.getName().equals(cardName))
                .findFirst()
                .ifPresent(this::makeHandCard);

        updateCardNotebookStatuses();
    }

    public void makeKnownCard(Card c){
        this.unknownCards.remove(c);
        this.knownCards.add(c);

        updateCardNotebookStatuses();
    }

    public void makeHandCard(Card c){
        this.unknownCards.remove(c);
        this.handCards.add(c);

        updateCardNotebookStatuses();
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

    public ObservableMap<Card, CardNotebookStatus> getCardNotebookStatusMap()
    {
        return cardNotebookStatusMap;
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