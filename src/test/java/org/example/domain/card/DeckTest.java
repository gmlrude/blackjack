package org.example.domain.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Test
    void drawAll52ThenEmptyThrows() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            assertNotNull(deck.drawCard());
        }
        assertThrows(IllegalStateException.class, deck::drawCard);
    }
}
