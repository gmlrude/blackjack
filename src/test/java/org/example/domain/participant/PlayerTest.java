package org.example.domain.participant;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {

    @Test
    void createPlayer() {
        Player p = Player.of("  pobi  ", new BigDecimal("100000"));
        assertEquals("pobi", p.getName());
        assertEquals(new BigDecimal("100000"), p.getBet());
    }

    @Test
    void throwsOnNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> Player.of(null, new BigDecimal("100000")));
    }

    @Test
    void throwsOnBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> Player.of("   ", new BigDecimal("100000")));
    }

    @Test
    void throwsOnReservedName() {
        assertThrows(IllegalArgumentException.class,
                () -> Player.of("딜러", new BigDecimal("100000")));
    }

    @Test
    void throwsOnNullBet() {
        assertThrows(IllegalArgumentException.class,
                () -> Player.of("jason", null));
    }

    @Test
    void throwsOnZeroOrNegativeBet() {
        assertThrows(IllegalArgumentException.class,
                () -> Player.of("jason", BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class,
                () -> Player.of("jason", new BigDecimal("-10")));
    }
}