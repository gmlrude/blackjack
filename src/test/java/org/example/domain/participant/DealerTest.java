package org.example.domain.participant;

import org.example.domain.card.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {
    @Test
    void shouldDrawWhenScore16OrLess() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card("8", "스페이드"));
        dealer.receiveCard(new Card("8", "하트"));
        assertTrue(dealer.shouldDrawMore());
    }
    @Test
    void shouldStandWhenScore17OrMore() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card("K", "스페이드"));
        dealer.receiveCard(new Card("7", "하트"));
        assertFalse(dealer.shouldDrawMore());
    }
}