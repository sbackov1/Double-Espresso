package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.server.domain.gameEvents.Accusation;
import edu.jhu.espresso.server.domain.gameEvents.MoveOptions;
import edu.jhu.espresso.server.domain.gameEvents.Suggestion;

import java.util.Optional;

public class ServerProtocolOfferInterpreter {

    private MoveOptions offerMoveOptions;
    private Suggestion offerSuggestion;
    private Accusation offerAccusation;

    public Optional<MoveOptions> getOfferMoveOptions(){return Optional.ofNullable(offerMoveOptions);}

    public Optional<Suggestion> getOfferSuggestion(){return Optional.ofNullable(offerSuggestion);}

    public Optional<Accusation> getOfferAccusation(){return Optional.ofNullable(offerAccusation);}

}