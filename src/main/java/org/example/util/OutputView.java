package org.example.util;

import org.example.domain.participant.Dealer;
import org.example.domain.participant.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInitialDeal(List<Player> players, Dealer dealer) {
        System.out.println("딜러와 " + players.stream().map(Player::getName).collect(Collectors.joining(", ")) + "에게 2장을 나누었습니다.");
        System.out.println("딜러: " + dealer.showHand());
        for (Player player : players) {
            System.out.println(player.getName() + "카드: " + player.showHand());
        }
    }

    public static void printPlayerCard(Player player) {
        System.out.println(player.getName() + "카드: " + player.showHand());
    }

    public static void printPlayerBlackjack(Player player) {
        System.out.println(player.getName() + " 블랙잭!");
    }

    public static void printDealerTurn() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalHands(Dealer dealer, List<Player> players) {
        System.out.println("딜러 카드: " + dealer.showHand() + " - 결과: " + dealer.getScore());
        for (Player player : players) {
            System.out.println(player.getName() + "카드: " + player.showHand() + " - 결과: " + player.getScore());
        }
    }

    public static void printFinalEarnings(Map<String, BigDecimal> earnings) {
        System.out.println("\n## 최종 수익");
        for (Map.Entry<String, BigDecimal> entry : earnings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
