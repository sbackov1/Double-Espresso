package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.domain.GameEvents.Accusation;
import edu.jhu.espresso.client.domain.GameEvents.MoveOptions;
import edu.jhu.espresso.client.domain.GameEvents.Suggestion;

import java.util.Optional;

public class ServerActivePlayerProtocolOfferer {

    private MoveOptions offerMoveOptions;

    private Suggestion offerSuggestion;
    private Accusation offerAccusation;

    public Optional<MoveOptions> getOfferMoveOptions(){return Optional.ofNullable(offerMoveOptions);}

    public Optional<Suggestion> getOfferSuggestion(){return Optional.ofNullable(offerSuggestion);}

    public Optional<Accusation> getOfferAccusation(){return Optional.ofNullable(offerAccusation);}

    public void setOfferMoveOptions(MoveOptions offerMoveOptions) {
        this.offerMoveOptions = offerMoveOptions;
    }

    public void setOfferSuggestion(Suggestion offerSuggestion) {
        this.offerSuggestion = offerSuggestion;
    }

    public void setOfferAccusation(Accusation offerAccusation) {
        this.offerAccusation = offerAccusation;
    }
}