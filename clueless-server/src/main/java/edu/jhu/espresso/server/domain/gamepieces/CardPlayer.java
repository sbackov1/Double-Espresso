package edu.jhu.espresso.server.domain.gamepieces;

import java.util.*;

public class CardPlayer {

    private CardDeck cardDeck;
    private ArrayList<Card> caseFile;

    //Constructor generates a cardDeck and a casefile.
    public CardPlayer() {
        this.cardDeck = new CardDeck();

        //MAKE CASEFILE
        this.caseFile = new ArrayList<Card>();
        //Use index 0-8 to get a room card, 9-14 for a random Character Card, 15-20 for a random Weapon Card, but account for one card being removed each time.
        Card randomRoomCard = this.cardDeck.dealRandomCard(0,8 );
        Card randomCharacterCard = this.cardDeck.dealRandomCard(8,13 );
        Card randomWeaponCard = this.cardDeck.dealRandomCard(13,18 );

        this.caseFile.add(randomCharacterCard);
        this.caseFile.add(randomRoomCard);
        this.caseFile.add(randomWeaponCard);

        for(Card card : caseFile)
        {
            System.out.println(card.getName());
        }
    }

    //DealCards finds the number of shared cards, shows them to all players, and generates a random hand for each player
    public void dealCards(List<Player> playerList){
        int numPlayers = playerList.size();
        int cardsPerPlayer = cardsPerPlayer(numPlayers);
        int commonCardNumber = 18 - cardsPerPlayer * numPlayers;

        //Deal common cards
        for(int i = 0; i < commonCardNumber; i++) {
            Card thisCard = this.cardDeck.dealRandomCard();
            for (Player pl : playerList) {
                pl.notebook.makeKnownCard(thisCard);
            }
        }

        //While cards are remaining, deal hands sequentially.
        while (this.cardDeck.getCardsListCopy().size() > 0){
            for (Player pl : playerList) {
                pl.notebook.makeHandCard(this.cardDeck.dealRandomCard());
            }
       }

    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public ArrayList<Card> getCaseFile() {
        return caseFile;
    }

    public int cardsPerPlayer(int numberOfPlayers)
    {
        int cardsPerPlayer;
        switch (numberOfPlayers)
        {
            case 3:
                cardsPerPlayer = 6;
                break;
            case 4:
                cardsPerPlayer = 4;
                break;
            case 5:
            case 6:
                cardsPerPlayer = 3;
                break;
            default:
                throw new IllegalArgumentException(numberOfPlayers + " is not a valid number of players");
        }
        return cardsPerPlayer;
    }
}






