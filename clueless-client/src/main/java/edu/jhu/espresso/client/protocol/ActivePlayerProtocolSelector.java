package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.domain.GameEvents.*;

public class ActivePlayerProtocolSelector {

    private MoveChoice moveChoice;
    private Suggestion suggestion;
    private Accusation accusation;

    public ActivePlayerProtocolSelector(){}

    public ActivePlayerProtocolSelector(MoveChoice moveChoice, Suggestion suggestion, Accusation accusation) {
        this.moveChoice = moveChoice;
        this.accusation = accusation;
        this.suggestion = suggestion;
    }

    public static ActivePlayerProtocolSelector FromMoveChoice(MoveChoice mc) {
        return new ActivePlayerProtocolSelector(mc, null, null);
    }

    public static ActivePlayerProtocolSelector FromAccusation(Accusation ac) {
        return new ActivePlayerProtocolSelector(null, null, ac);
    }

    public static ActivePlayerProtocolSelector FromSuggestion(Suggestion sg) {
        return new ActivePlayerProtocolSelector(null, sg, null);
    }
    public static ActivePlayerProtocolSelector EndTurn() {
        return new ActivePlayerProtocolSelector(null, null, null);
    }

    /**
     *Public getters are necessary for JSON object to transmit properly.
     * */
    public MoveChoice getMoveChoice() {
        return moveChoice;
    }
    public Suggestion getSuggestion() {
        return suggestion;
    }
    public Accusation getAccusation() {
        return accusation;
    }

}


