package edu.jhu.espresso;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class Driver {

    public static void main(String[] args) throws JsonProcessingException {

        Card aCard = new Card();

        JsonNode nodeCard = Json.toJson(aCard);

        System.out.println(nodeCard.get("cardType").asText());
        System.out.println(nodeCard.get("cardValue").asText());

        String cardString = Json.jsonString(nodeCard, false);
        System.out.println(cardString);

        String indentString = Json.jsonString(nodeCard, true);
        System.out.println(indentString);

        byte[] cardBytes = Json.jsonByte(nodeCard, false);






    }

}
