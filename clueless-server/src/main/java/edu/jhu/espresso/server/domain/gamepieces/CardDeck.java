package edu.jhu.espresso.server.domain.gamepieces;

import java.util.*;

public class CardDeck {

        public ArrayList<Card> cardsList;
        private ArrayList<Card> cardsListCopy;

        public CardDeck() {
                this.cardsList = new ArrayList<Card>();

                //Add room cards
                for (RoomNames rooms : RoomNames.values()) {
                        this.cardsList.add(new RoomCard(rooms));
                }

                //Add character cards
                for (CharacterNames cn : CharacterNames.values()) {
                        this.cardsList.add(new CharacterCard(cn));
                }

                //Add weapon cards
                for (Weapon wp : Weapon.values()) {
                        this.cardsList.add(new WeaponCard(wp));
                }

                //Create a copy to deal from
                this.cardsListCopy = (ArrayList<Card>) cardsList.clone();
        }

        //dealRandomCard returns random card and removes it from the deck.
        public Card dealRandomCard() {
                int number = (int) (Math.random() * cardsListCopy.size());
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
}
