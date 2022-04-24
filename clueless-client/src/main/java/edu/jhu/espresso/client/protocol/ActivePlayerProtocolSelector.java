package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.domain.GameEvents.Accusation;
import edu.jhu.espresso.client.domain.GameEvents.MoveChoice;
import edu.jhu.espresso.client.domain.GameEvents.Suggestion;
import edu.jhu.espresso.client.domain.GameEvents.SuggestionResponse;

public class ActivePlayerProtocolSelector {

    private MoveChoice moveChoice;
    private Suggestion suggestion;
    private Accusation accusation;

    private ActivePlayerProtocolSelector(MoveChoice moveChoice, Suggestion suggestion, Accusation accusation) {
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

}


