package org.example.domain.participant;

import org.example.util.Validator;

import java.math.BigDecimal;

public class Player extends Participant {
    private final String name;
    private BigDecimal bet;

    public Player(String name, BigDecimal bet) {
        this.name = name;
        this.bet = bet;
    }

    public static Player of(String name, BigDecimal bet) {
        Validator.validatePlayerName(name);
        Validator.validateBet(bet);
        return new Player(name.trim(), bet);
    }

    public String getName() {
        return name;
    }
    public BigDecimal getBet() {
        return bet;
    }
}
