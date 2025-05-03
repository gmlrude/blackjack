package org.example.util;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @Test
    void parseAndValidateNames() {
        List<String> names = Validator.parseAndValidateNames("pobi, jason , john");
        assertEquals(3, names.size());
        assertTrue(names.contains("john"));
    }
    @Test
    void emptyNames() {
        assertThrows(IllegalArgumentException.class,
                () -> Validator.parseAndValidateNames("   "));
    }
    @Test
    void duplicateName() {
        assertThrows(IllegalArgumentException.class,
                () -> Validator.parseAndValidateNames("a,b,a"));
    }

    @Test
    void returnsBigDecimal() {
        BigDecimal b = Validator.validateBet("100.50");
        assertEquals(new BigDecimal("100.50"), b);
    }
    @Test
    void negativeBet() {
        assertThrows(IllegalArgumentException.class,
                () -> Validator.validateBet("-1"));
    }
    @Test
    void nonNumericBet() {
        assertThrows(IllegalArgumentException.class,
                () -> Validator.validateBet("abc"));
    }
    @Test
    void nullBetBigDecimal() {
        assertThrows(IllegalArgumentException.class,
                () -> Validator.validateBet((BigDecimal)null));
    }
}
