package org.example.domain.card;

public class Card {
    private final String rank;
    private final String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + suit;
    }

    public int getValue() {
        return switch (rank) {
            case "A" -> 1;
            case "K", "Q", "J" -> 10;
            default -> Integer.parseInt(rank);
        };
    }

    public boolean isAce() {
        return rank.equals("A");
    }
}
