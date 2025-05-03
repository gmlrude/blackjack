package org.example.domain.hand;

import static org.junit.jupiter.api.Assertions.*;
import org.example.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
    @Test
    @DisplayName("에이스 한 장과 9점 카드 합이 20으로 계산된다.")
    void singleAceCountsAs11WhenPossible() {
        Hand hand = new Hand();
        hand.addCard(new Card("A","스페이드"));
        hand.addCard(new Card("9","하트"));
        assertEquals(20, hand.calculateScore());
    }
    @Test
    @DisplayName("여러 에이스가 있을 때 최적의 21점으로 계산된다.")
    void multipleAcesOptimalChoice() {
        Hand hand = new Hand();
        hand.addCard(new Card("A","스페이드"));
        hand.addCard(new Card("A","하트"));
        hand.addCard(new Card("9","클로버"));
        // 1 + 1 + 9 = 11 → 하나만 11로 올려서 21
        assertEquals(21, hand.calculateScore());
    }
    @Test
    @DisplayName("에이스가 오버카운팅되지 않고 1로 유지된다.")
    void aceNotOverCounting() {
        Hand hand = new Hand();
        hand.addCard(new Card("A","♣"));
        hand.addCard(new Card("9","♦"));
        hand.addCard(new Card("5","하트"));
        // 1 + 9 + 5 = 15 → 1로 유지
        assertEquals(15, hand.calculateScore());
    }
    @Test
    @DisplayName("카드 합이 21을 초과하면 버스트된다.")
    void bustedWhenOver21() {
        Hand hand = new Hand();
        hand.addCard(new Card("K","스페이드"));
        hand.addCard(new Card("Q","하트"));
        hand.addCard(new Card("2","클로버"));
        assertTrue(hand.isBusted());
    }
}
