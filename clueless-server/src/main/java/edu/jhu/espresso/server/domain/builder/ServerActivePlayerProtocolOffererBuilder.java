package edu.jhu.espresso.server.domain.builder;

import edu.jhu.espresso.server.domain.gameEvents.Accusation;
import edu.jhu.espresso.server.domain.gameEvents.MoveOptions;
import edu.jhu.espresso.server.domain.gameEvents.Suggestion;
import edu.jhu.espresso.server.protocol.ServerActivePlayerProtocolOfferer;

public final class ServerActivePlayerProtocolOffererBuilder {
    private MoveOptions offerMoveOptions;
    private Suggestion offerSuggestion;
    private Accusation offerAccusation;

    private ServerActivePlayerProtocolOffererBuilder() {
    }

    public static ServerActivePlayerProtocolOffererBuilder aServerActivePlayerProtocolOfferer() {
        return new ServerActivePlayerProtocolOffererBuilder();
    }

    public ServerActivePlayerProtocolOffererBuilder withOfferMoveOptions(MoveOptions offerMoveOptions) {
        this.offerMoveOptions = offerMoveOptions;
        return this;
    }

    public ServerActivePlayerProtocolOffererBuilder withOfferSuggestion(Suggestion offerSuggestion) {
        this.offerSuggestion = offerSuggestion;
        return this;
    }

    public ServerActivePlayerProtocolOffererBuilder withOfferAccusation(Accusation offerAccusation) {
        this.offerAccusation = offerAccusation;
        return this;
    }

    public ServerActivePlayerProtocolOfferer build() {
        return new ServerActivePlayerProtocolOfferer(offerMoveOptions, offerSuggestion, offerAccusation);
    }
}
