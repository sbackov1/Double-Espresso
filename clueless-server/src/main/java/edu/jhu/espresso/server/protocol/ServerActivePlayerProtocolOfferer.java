package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.domain.gameEvents.Accusation;
import edu.jhu.espresso.server.domain.gameEvents.MoveOptions;
import edu.jhu.espresso.server.domain.gameEvents.Suggestion;


public class ServerActivePlayerProtocolOfferer {

    private MoveOptions offerMoveOptions;
    private Suggestion offerSuggestion;
    private Accusation offerAccusation;

    public ServerActivePlayerProtocolOfferer(MoveOptions offerMoveOptions, Suggestion offerSuggestion, Accusation offerAccusation){
        this.offerSuggestion = offerSuggestion;
        this.offerMoveOptions = offerMoveOptions;
        this.offerAccusation = offerAccusation;
    }

    /**
     * Public getters allow the object to be sent.
     * */

    public MoveOptions getOfferMoveOptions() {
        return offerMoveOptions;
    }

    public Suggestion getOfferSuggestion() {
        return offerSuggestion;
    }

    public Accusation getOfferAccusation() {
        return offerAccusation;
    }
}
