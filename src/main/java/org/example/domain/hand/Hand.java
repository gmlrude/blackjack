package org.example.domain.hand;

import org.example.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private final List<Card> cards = new ArrayList<>();
    public void addCard(Card card) { cards.add(card); }

    public int calculateScore() {
        int total = 0;
        int aceCount = 0;

        for (Card card : cards) {
            total += card.getValue();
            if (card.isAce()) aceCount++;
        }

        // A를 1 또는 11로 계산할지 더 유리한 쪽으로 계산
        while (aceCount > 0 && total + 10 <= 21) {
            total += 10;
            aceCount--;
        }

        return total;
    }

    public int cardCount() {
        return cards.size();
    }

    public boolean isBusted() { return calculateScore() > 21; }

    public String showHand() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }
}
