package org.example.domain.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    // 불변 필드니까 필드에서 초기화
    private final Stack<Card> cards = new Stack<>();

    public Deck() {
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        String[] suits = { "스페이드", "하트", "다이아몬드", "클로버" };
        String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.push(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 카드가 남아있지 않습니다.");
        }
        return cards.pop();
    }
}
