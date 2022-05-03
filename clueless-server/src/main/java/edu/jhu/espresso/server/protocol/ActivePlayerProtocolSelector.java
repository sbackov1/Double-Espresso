

package edu.jhu.espresso.server.protocol;

import edu.jhu.espresso.server.domain.gameEvents.Accusation;
import edu.jhu.espresso.server.domain.gameEvents.MoveChoice;
import edu.jhu.espresso.server.domain.gameEvents.Suggestion;

import java.util.Optional;

    public class ActivePlayerProtocolSelector {

        private MoveChoice moveChoice;
        private Suggestion suggestion;
        private Accusation accusation;

        public Optional<MoveChoice> getMoveChoice() {
            return Optional.ofNullable(moveChoice);
        }

        public void setMoveChoice(MoveChoice moveChoice) {
            this.moveChoice = moveChoice;
        }

        public Optional<Suggestion> getSuggestion() {
            return Optional.ofNullable(suggestion);
        }

        public void setSuggestion(Suggestion suggestion) {
            this.suggestion = suggestion;
        }

        public Optional<Accusation> getAccusation() {
            return Optional.ofNullable(accusation);
        }

        public void setAccusation(Accusation accusation) {
            this.accusation = accusation;
        }

    }