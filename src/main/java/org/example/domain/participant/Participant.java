package org.example.domain.participant;

import org.example.domain.card.Card;
import org.example.domain.hand.Hand;

public abstract class Participant {
    protected Hand hand = new Hand();

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public boolean hasBlackjack() {
        return hand.cardCount() == 2 && hand.calculateScore() == 21;
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public String showHand() {
        return hand.showHand();
    }
}
