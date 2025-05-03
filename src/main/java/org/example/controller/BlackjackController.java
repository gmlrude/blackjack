package org.example.controller;

import org.example.service.Blackjack;
import org.example.domain.participant.Player;
import org.example.util.InputView;
import org.example.util.OutputView;

import java.math.BigDecimal;
import java.util.Map;

public class BlackjackController {

    private final Blackjack blackjack;

    public BlackjackController(Blackjack blackjack) {
        this.blackjack = blackjack;
    }

    public void run() {
        registerPlayers();
        initialDeal();
        playerTurns();
        dealerTurn();
        showResults();
    }

    private void registerPlayers() {
        for (String name : InputView.readPlayerNames()) {
            blackjack.registerPlayer(name, InputView.readBet(name));
        }
    }

    private void initialDeal() {
        blackjack.dealInitialCards();
        OutputView.printInitialDeal(blackjack.getPlayers(), blackjack.getDealer());
    }

    private void playerTurns() {
        for (Player player : blackjack.getPlayers()) {
            if (player.hasBlackjack()) {
                OutputView.printPlayerBlackjack(player);
                continue;
            }
            while (!player.isBusted() && InputView.wantsToDraw(player.getName())) {
                blackjack.playerDrawCard(player);
                OutputView.printPlayerCard(player);
            }
        }
    }

    private void dealerTurn() {
        if (blackjack.dealerTurn()) {
            OutputView.printDealerTurn();
        }
    }

    private void showResults() {
        OutputView.printFinalHands(blackjack.getDealer(), blackjack.getPlayers());
        Map<String, BigDecimal> earnings = blackjack.calculateEarnings();
        OutputView.printFinalEarnings(earnings);
    }
}
