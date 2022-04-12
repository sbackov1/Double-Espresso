package edu.jhu.espresso.server.domain;
import java.util.*;

public class CardPlayer {

    private CardDeck cardDeck;
    private ArrayList<Card> caseFile;

    //Constructor generates a cardDeck and a casefile.
    public CardPlayer() {
        this.cardDeck = new CardDeck();
    }

    //getCaseFile generates three random cards for the casefile, which are removed from carddeck as part of the dealRandomCard method.
    public ArrayList<Card> getCaseFile(CardDeck cd){
        ArrayList<Card> caseFile = new ArrayList<Card>();
        //Use index 0-9 to get a room card, 9-14 for a random Character Card, 15-20 for a random Weapon Card, but account for one card being removed each time.
        Card randomRoomCard = this.cardDeck.dealRandomCard(0,8 );
        Card randomCharacterCard = this.cardDeck.dealRandomCard(8,13 );
        Card randomWeaponCard = this.cardDeck.dealRandomCard(13,18 );

        caseFile.add(randomCharacterCard);
        caseFile.add(randomRoomCard);
        caseFile.add(randomWeaponCard);

        return caseFile;
    }

    //DealCards finds the number of shared cards, shows them to all players, and generates a random hand for each player
    public void dealCards(ArrayList<Player> playerList){
        int numPlayers = playerList.size();
        int commonCardNumber = 18 % numPlayers;

        //Deal common cards
        for(int i = 0; i < commonCardNumber; i++) {
            Card thisCard = this.cardDeck.dealRandomCard();
            for (Player pl : playerList) {
                pl.notebook.makeHandCard(thisCard);
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
}





