package org.example.domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import org.example.domain.card.Card;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    static class TestParticipant extends Participant {}

    @Test
    void showHandListsCards() {
        TestParticipant participant = new TestParticipant();
        participant.receiveCard(new Card("3", "스페이드"));
        participant.receiveCard(new Card("7", "클로버"));
        assertEquals("3스페이드, 7클로버", participant.showHand());
    }
}
