package org.example.domain.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void numericCardValue() {
        assertEquals(2, new Card("2","스페이드").getValue());
        assertEquals(10, new Card("10","하트").getValue());
    }
    @Test
    void faceCardValue() {
        assertEquals(10, new Card("J","클로버").getValue());
        assertEquals(10, new Card("Q","다이아몬드").getValue());
        assertEquals(10, new Card("K","스페이드").getValue());
    }
    @Test
    void aceIsOneByDefault() {
        assertEquals(1, new Card("A","하트").getValue());
        assertTrue(new Card("A","클로버").isAce());
    }
}
