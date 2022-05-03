package edu.jhu.espresso.client.domain.GamePieces;

import java.util.ArrayList;

public class CardDeck {

        private ArrayList<Card> cardsList;
        private ArrayList<Card> cardsListCopy;

        public CardDeck() {
                this.cardsList = new ArrayList<Card>();

                //Add room cards
                for (RoomNames rooms : RoomNames.values()) {
                        this.cardsList.add(new RoomCard(rooms.name()));
                }

                //Add character cards
                for (CharacterNames cn : CharacterNames.values()) {
                        this.cardsList.add(new CharacterCard(cn.name()));
                }

                //Add weapon cards
                for (Weapon wp : Weapon.values()) {
                        this.cardsList.add(new WeaponCard(wp.name()));
                }

                //Create a copy to deal from
                this.cardsListCopy = (ArrayList<Card>) cardsList.clone();
        }

        //dealRandomCard returns random card and removes it from the deck.
        public Card dealRandomCard() {
                int number = (int) (Math.random() * cardsList.size());
                Card retCard = cardsListCopy.get(number);
                cardsListCopy.remove(number);
                return retCard;
        }
        //Overload method to allow a range of random cards
        public Card dealRandomCard(int lowIndex, int highIndex){
                int number = (int) (Math.random() * (highIndex - lowIndex) + lowIndex);
                Card retCard = cardsListCopy.get(number);
                cardsListCopy.remove(number);
                return retCard;
                }

        public ArrayList<Card> getCardsList() {
                return cardsList;
        }

        public ArrayList<Card> getCardsListCopy() {
                return cardsListCopy;
        }

        public Card getCard(ArrayList<Card> someCards, String name) {

                for (Card card : someCards) {
                        if (card.getName().equals(name)) { return card; }
                }
                return null;
        }

}
