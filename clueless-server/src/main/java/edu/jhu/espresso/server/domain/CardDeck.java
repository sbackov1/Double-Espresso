package edu.jhu.espresso.server.domain;
import java.util.*;
import java.math.*;

public class CardDeck {

        private ArrayList<Card> cardsList;
        private ArrayList<Card> cardsListCopy;

        public CardDeck() {
                ArrayList<Card> cardsList = new ArrayList<Card>();

                //Add room cards
                for (RoomNames rooms : RoomNames.values()) {
                        cardsList.add(new RoomCard(rooms.name()));
                }

                //Add character cards
                for (CharacterNames cn : CharacterNames.values()) {
                        cardsList.add(new CharacterCard(cn.name()));
                }

                //Add weapon cards
                for (Weapon wp : Weapon.values()) {
                        cardsList.add(new WeaponCard(wp.name()));
                }

                //Create a copy to deal from
                ArrayList<Card> cardsListCopy = (ArrayList<Card>) cardsList.clone();
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
}
