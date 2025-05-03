package org.example.service;

import org.example.domain.card.Card;
import org.example.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackTest {

    // 랜덤성 제거한 FakeDeck (큐로 임의의 카드 넣어두고 테스트)
    static class FakeDeck extends Deck {
        private final Queue<Card> cards;
        FakeDeck(Queue<Card> cards) { this.cards = cards; }
        @Override public void shuffle() {}
        @Override public Card drawCard() {
            if (cards.isEmpty()) throw new IllegalStateException("덱 비었음");
            return cards.poll();
        }
    }

    @Test
    @DisplayName("플레이어가 21점에 더 가깝기 때문에 승리한다.")
    void playerBeatsDealer() {
        // P1: 10+9=19, D: 8+9=17 ; P1 WIN
        Queue<Card> seq = new ArrayDeque<>(List.of(
                new Card("10","스페이드"),
                new Card("8","하트"),
                new Card("9","클로버"),
                new Card("9","다이아몬드")
        ));
        Blackjack game = new Blackjack(new FakeDeck(seq));
        game.registerPlayer("player1", BigDecimal.valueOf(10000));
        game.dealInitialCards();
        game.dealerTurn();
        var result = game.calculateEarnings();

        assertEquals(BigDecimal.valueOf(10000), result.get("player1"));
        assertEquals(BigDecimal.valueOf(-10000), result.get("딜러"));
    }

    @Test
    @DisplayName("플레이어가 21점을 초과하여 베팅 금액을 모두 잃는다.")
    void playerBustsLosesAll() {
        // P1: 10+9=19, D: 8+K=18
        // P1: 19+5=24 -> bust
        Queue<Card> seq = new ArrayDeque<>(List.of(
                new Card("10","스페이드"),
                new Card("8","하트"),
                new Card("9","클로버"),
                new Card("K","다이아몬드"),
                new Card("5","하트")      // P1 draw -> bust
        ));
        Blackjack game = new Blackjack(new FakeDeck(seq));
        game.registerPlayer("player1", BigDecimal.valueOf(10000));
        game.dealInitialCards();

        game.playerDrawCard(game.getPlayers().get(0));
        assertTrue(game.getPlayers().get(0).isBusted());

        game.dealerTurn();
        var result = game.calculateEarnings();

        assertEquals(BigDecimal.valueOf(-10000), result.get("player1"));
        assertEquals(BigDecimal.valueOf(10000), result.get("딜러"));
    }

    @Test
    @DisplayName("플레이어와 딜러가 동점이다.")
    void tieResults() {
        // P1: 10+7=17, D: 9+8=17
        Queue<Card> seq = new ArrayDeque<>(List.of(
                new Card("10","스페이드"),
                new Card("9","하트"),
                new Card("7","클로버"),
                new Card("8","다이아몬드")
        ));
        Blackjack game = new Blackjack(new FakeDeck(seq));
        game.registerPlayer("player1", BigDecimal.valueOf(10000));
        game.dealInitialCards();
        game.dealerTurn();
        var result = game.calculateEarnings();

        assertEquals(BigDecimal.ZERO, result.get("player1"));
        assertEquals(BigDecimal.ZERO, result.get("딜러"));
    }

    @Test
    @DisplayName("딜러가 21점을 초과하여 플레이어가 승리한다.")
    void dealerBustsAllPlayersWin() {
        // P1:5+5=10, D:9+6=15 -
        // D: 15+10=25 -> bust
        Queue<Card> seq = new ArrayDeque<>(List.of(
                new Card("5","스페이드"),
                new Card("9","하트"),
                new Card("5","클로버"),
                new Card("6","다이아몬드"),
                new Card("10","스페이드") // D draw -> bust
        ));
        Blackjack game = new Blackjack(new FakeDeck(seq));
        game.registerPlayer("player1", BigDecimal.valueOf(10000));
        game.dealInitialCards();
        game.dealerTurn();
        var result = game.calculateEarnings();

        assertEquals(BigDecimal.valueOf(10000), result.get("player1"));
        assertEquals(BigDecimal.valueOf(-10000), result.get("딜러"));
    }

    @Test
    @DisplayName("딜러와 플레이어가 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.")
    void BothBlackjack() {
        // P1: 8+8=16, P2: A+K=21 -> blackjack, D: A+K=21 -> blackjack
        Queue<Card> seq = new ArrayDeque<>(List.of(
                // initial
                new Card("8","스페이드"),
                new Card("A","하트"),
                new Card("A","클로버"),
                new Card("8","다이아몬드"),
                new Card("K","스페이드"), // P2 -> blackjack
                new Card("K", "클로버")   // D -> blackjack
        ));
        Blackjack game = new Blackjack(new FakeDeck(seq));
        game.registerPlayer("player1", BigDecimal.valueOf(10000));
        game.registerPlayer("player2", BigDecimal.valueOf(20000));
        game.dealInitialCards();
        game.dealerTurn();
        var result = game.calculateEarnings();

        assertEquals(BigDecimal.valueOf(-10000), result.get("player1"));
        assertEquals(BigDecimal.ZERO, result.get("player2"));
        assertEquals(BigDecimal.valueOf(10000), result.get("딜러"));
    }

    @Test
    @DisplayName("플레이어 단독 블랙잭 시 베팅 금액의 1.5배를 획득한다.")
    void singlePlayerBlackjack() {
        // P: A+K=21 -> blackjack,  D: 5+6=11
        // D: 11+7=18
        Queue<Card> seq = new ArrayDeque<>(List.of(
                // initial deal
                new Card("A","스페이드"),
                new Card("5","하트"),
                new Card("K","클로버"),   // P1 -> blackjack
                new Card("6","다이아몬드"),
                new Card("7","하트")      // dealer draw
        ));
        Blackjack game = new Blackjack(new FakeDeck(seq));
        game.registerPlayer("player1", BigDecimal.valueOf(10000));
        game.dealInitialCards();
        game.dealerTurn();
        var earnings = game.calculateEarnings();

        assertEquals(new BigDecimal("15000.0"), earnings.get("player1"));
        assertEquals(new BigDecimal("-15000.0"), earnings.get("딜러"));
    }
}
