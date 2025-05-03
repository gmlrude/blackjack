package org.example.service;

import org.example.domain.card.Deck;
import org.example.domain.participant.Dealer;
import org.example.domain.participant.Player;

import java.math.BigDecimal;
import java.util.*;

public class Blackjack {
    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

    public Blackjack(Deck deck) {
        this.deck = Objects.requireNonNull(deck);
        this.dealer = new Dealer();
        this.players = new ArrayList<>();
    }

    /**
     * 플레이어 등록
     */
    public void registerPlayer(String name, BigDecimal bet) {
        players.add(Player.of(name, bet));
    }

    /**
     * 초기 카드 2장씩 지급
     */
    public void dealInitialCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.receiveCard(deck.drawCard());
            }
            dealer.receiveCard(deck.drawCard());
        }
    }

    /**
     * 플레이어에게 한 장 더 지급
     */
    public void playerDrawCard(Player player) {
        player.receiveCard(deck.drawCard());
    }

    /**
     * 딜러가 16 이하일 때 한 장 더 지급
     */
    public boolean dealerTurn() {
        if (dealer.shouldDrawMore()) {
            dealer.receiveCard(deck.drawCard());
            return true;
        }
        return false;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    /**
     * 최종 수익 계산
     */
    public Map<String, BigDecimal> calculateEarnings() {
        Map<String, BigDecimal> earnings = new LinkedHashMap<>();
        int dealerScore = dealer.getScore();
        boolean dealerBlackjack = dealer.hasBlackjack();
        BigDecimal dealerProfit = BigDecimal.ZERO;

        for (Player player : players) {
            BigDecimal bet = player.getBet();
            BigDecimal playerResult = calculatePlayerResult(player, dealerBlackjack, dealerScore, bet);

            earnings.put(player.getName(), playerResult);
            dealerProfit = dealerProfit.add(playerResult.negate());
        }
        earnings.put("딜러", dealerProfit);
        return earnings;
    }

    private BigDecimal calculatePlayerResult(Player player, boolean dealerBlackjack, int dealerScore, BigDecimal bet) {
        if (player.hasBlackjack() && dealerBlackjack) return BigDecimal.ZERO;
        if (player.hasBlackjack()) return bet.multiply(new BigDecimal("1.5"));

        return calculatePlayerResult(player.getScore(), dealerScore, bet, player.isBusted());
    }

    private BigDecimal calculatePlayerResult(int playerScore,
                                             int dealerScore,
                                             BigDecimal bet,
                                             boolean busted) {
        if (busted) return bet.negate();
        if (dealerScore > 21 || playerScore > dealerScore) return bet;
        if (playerScore == dealerScore) return BigDecimal.ZERO;

        return bet.negate();
    }
}
